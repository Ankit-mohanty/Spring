package com.edigest.journalApp.repository;


import com.edigest.journalApp.entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<Users, ObjectId> {
	Users findByUserName( String userName );

}
