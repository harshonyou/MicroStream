package com.example.repository;

import com.example.dto.RecommendedVideoDTO;
import com.example.model.User;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Singleton
public class UserRepository {
    private final Driver driver;

    UserRepository(Driver driver) {
        this.driver = driver;
    }

    public Optional<User> findById(String userId) {
        try (Session session = driver.session()) {
            return session.readTransaction(tx -> findById(tx, userId));
        }
    }

    private Optional<User> findById(Transaction tx, String userId) {
        String query = "MATCH (u:User {id: $userId}) RETURN u";

        var result = tx.run(query, org.neo4j.driver.Values.parameters("userId", userId));

        if (result.hasNext()) {
            var record = result.single();
            String userName = record.get("u").get("name").asString();
            return Optional.of(new User(userId, userName));
        } else {
            return Optional.empty();
        }
    }

    public void addUser(User user) {
        try (Session session = driver.session()) {
            session.writeTransaction(tx -> createUser(tx, user));
        }
    }

    private Void createUser(Transaction tx, User user) {
        String query = "CREATE (u:User {id: $id, name: $name})";
        tx.run(query, org.neo4j.driver.Values.parameters(
                "id", user.getId(),
                "name", user.getName()));

        return null;
    }

}
