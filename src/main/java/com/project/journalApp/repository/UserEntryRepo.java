package com.project.journalApp.repository;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<UserEntry, ObjectId> {
    UserEntry findByUserName(String userName);
}
