package com.example.repository;

import com.example.model.Tag;
import com.example.model.User;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

import java.util.Optional;


@Singleton
public class TagRepository {
    private final Driver driver;

    TagRepository(Driver driver) {
        this.driver = driver;
    }

    public Optional<Tag> findByName(String tagName) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> findByName(tx, tagName));
        }
    }

    private Optional<Tag> findByName(Transaction tx, String tagName) {
        String query = "MATCH (t:Tag {name: $tagName}) RETURN t";

        var result = tx.run(query, org.neo4j.driver.Values.parameters("tagName", tagName));

        if (result.hasNext()) {
            var record = result.single();
            return Optional.of(new Tag(tagName));
        } else {
            return Optional.empty();
        }
    }

    public void addTag(Tag tag) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> createTag(tx, tag));
        }
    }

    private Void createTag(Transaction tx, Tag tag) {
        String query = "CREATE (t:Tag {name: $tagName})";
        tx.run(query, org.neo4j.driver.Values.parameters(
                "tagName", tag.getName()));

        return null;
    }
}
