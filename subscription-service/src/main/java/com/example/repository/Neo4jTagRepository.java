package com.example.repository;

import com.example.model.Tag;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

import java.util.Optional;


@Singleton
public class Neo4jTagRepository implements TagRepository {
    private final Driver driver;

    Neo4jTagRepository(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Optional<Tag> findByTagName(String tagName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> findByTagName(tx, tagName));
        }
    }

    private Optional<Tag> findByTagName(Transaction tx, String tagName) {
        String query =  """
                        MATCH (t:Tag {name: $tagName})
                        RETURN t
                        """;

        var result = tx.run(query, org.neo4j.driver.Values.parameters("tagName", tagName));

        if (result.hasNext()) {
            var record = result.single();
            return Optional.of(new Tag(tagName));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void addTag(Tag tag) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> addTag(tx, tag));
        }
    }

    private Void addTag(Transaction tx, Tag tag) {
        String query =  """
                        CREATE (t:Tag {name: $tagName})
                        """;
        tx.run(query, org.neo4j.driver.Values.parameters(
                "tagName", tag.getName()));

        return null;
    }

    @Override
    public void associateUserWithTag(String tagName, String userId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> associateUserWithTag(tx, tagName, userId));
        }
    }

    private Void associateUserWithTag(Transaction tx, String tagName, String userId) {
        String query =  """
                        MATCH (u:User {id: $userId}), (t:Tag {name: $tagName})
                        MERGE (u)-[:SUBSCRIBES_TO]->(t)
                        """;

        tx.run(query, org.neo4j.driver.Values.parameters(
                "userId", userId,
                "tagName", tagName));
        return null;
    }

    @Override
    public void disassociateUserFromTag(String tagName, String userId) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> disassociateUserFromTag(tx, tagName, userId));
        }
    }

    private Void disassociateUserFromTag(Transaction tx, String tagName, String userId) {
        String query =  """
                        MATCH (u:User {id: $userId})-[r:SUBSCRIBES_TO]->(t:Tag {name: $tagName})
                        DELETE r
                        """;

        tx.run(query, org.neo4j.driver.Values.parameters(
                "userId", userId,
                "tagName", tagName));
        return null;
    }

    public void deleteAll() {
        try (Session session = driver.session()) {
            session.writeTransaction(this::deleteAllTags);
        }
    }

    private Void deleteAllTags(Transaction tx) {
        String query =  """
                        MATCH (t:Tag)
                        DETACH DELETE t
                        """;
        tx.run(query);
        return null;
    }
}
