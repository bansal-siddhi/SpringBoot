package com.project.journalApp.service;

import com.project.journalApp.entity.UserEntry;
import com.project.journalApp.repository.UserEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserEntryService {

    @Autowired
    private UserEntryRepo userEntryRepo;

    public void saveEntry(UserEntry entry){
        userEntryRepo.save(entry);
    }

    public List<UserEntry> getAll(){
        return userEntryRepo.findAll();
    }

    public Optional<UserEntry> findByID(ObjectId id) {
        return userEntryRepo.findById(id);
    }

    public boolean deleteEntry(ObjectId id){
        userEntryRepo.deleteById(id);
        return true;
    }

    public UserEntry findByName(String userName){
        return userEntryRepo.findByUserName(userName);
    }
}
