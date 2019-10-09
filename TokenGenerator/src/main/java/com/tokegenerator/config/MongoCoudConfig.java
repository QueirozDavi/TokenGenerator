package com.tokegenerator.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoCoudConfig {

    @Bean
    public void setDatabase() {
        MongoClientURI uri = new MongoClientURI(
                "mongodb+srv://admin:<password>@cluster0-yaekv.mongodb.net/test?retryWrites=true&w=majority");

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("test");
    }

}
