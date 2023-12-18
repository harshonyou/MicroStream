package com.example.repository;

import com.example.model.User;
import jakarta.inject.Singleton;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;

@Singleton
public class UserRepository {
    private final Driver driver;

    UserRepository(Driver driver) {
        this.driver = driver;
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
