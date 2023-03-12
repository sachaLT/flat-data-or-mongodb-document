package com.slu.mongodb;

import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.conversions.Bson;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ClientFactory {
    public static MongoClient getInstance(String uri) {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(uri))
                .build();
        // Create a new client and connect to the server
        MongoClient mongoClient = null;
        try {
            mongoClient = MongoClients.create(settings);
            MongoDatabase database = mongoClient.getDatabase("admin");
            try {
                // Send a ping to confirm a successful connection
                BsonInt64 bi64 = new BsonInt64(1);
                Bson command = new BsonDocument("ping", bi64);
                database.runCommand(command);
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException me) {
                System.err.println(me);
                mongoClient = null;
            }
        } catch (MongoException me) {
            System.err.println(me);
            mongoClient = null;
        }
        return mongoClient;
    }
}
