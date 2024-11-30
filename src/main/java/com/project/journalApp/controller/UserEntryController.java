package com.project.journalApp.controller;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntry;
import com.project.journalApp.service.JournalEntryService;
import com.project.journalApp.service.UserEntryService;
import org.apache.catalina.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping
    public List<UserEntry> getAll(){
        return userEntryService.getAll();
    }

    @PostMapping
    public ResponseEntity<UserEntry> createUser(@RequestBody UserEntry entry){
       try{
           userEntryService.saveEntry(entry);
           return new ResponseEntity<>(entry, HttpStatus.OK);
       }catch(Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{userName}")
    public ResponseEntity<UserEntry> updateUser(@RequestBody UserEntry user, @PathVariable String userName){
        UserEntry userINDB = userEntryService.findByName(userName);
        if(userINDB!=null){
            userINDB.setUserName(user.getUserName());
            userINDB.setUserPass(user.getUserPass());
            userEntryService.saveEntry(userINDB);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//
//    @DeleteMapping("/delete/{userName}")
//    public ResponseEntity<UserEntry> deleteUser(@PathVariable String userName){
//        UserEntry userINDB = userEntryService.findByName(userName);
//        if(userINDB!=null){
//
//        }
//    }
}
