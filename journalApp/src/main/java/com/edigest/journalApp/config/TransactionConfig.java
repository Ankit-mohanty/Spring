package com.edigest.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionConfig {

@Bean
	public PlatformTransactionManager add( MongoDatabaseFactory databaseFactory ){
	return new MongoTransactionManager(databaseFactory);
}
}

