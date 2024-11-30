package com.project.journalApp.controller;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntry;
import com.project.journalApp.service.JournalEntryService;
import com.project.journalApp.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService userEntryService;

    @GetMapping("{userName}")
    public ResponseEntity<List<JournalEntry>> getAllEntriesofUser(@PathVariable String userName){
        UserEntry user = userEntryService.findByName(userName);
        List<JournalEntry> list = user.getJournalEntryList();
        if(list!=null && !list.isEmpty())
            return new ResponseEntity<>(list, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getByID(@PathVariable ObjectId id){
        Optional<JournalEntry> journalEntry= journalEntryService.findByID(id);
        if(journalEntry.isPresent())
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry, @PathVariable String userName){
        try {
            journalEntryService.saveEntry(entry, userName);
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{userName}/{id}")
    public ResponseEntity<JournalEntry> deleteMapping(@PathVariable ObjectId id, @PathVariable String userName) {
        journalEntryService.deleteEntry(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}/{userName}")
    public ResponseEntity<JournalEntry>  updateMapping(@PathVariable ObjectId id,
                                                       @RequestBody JournalEntry entry,
                                                       @PathVariable String userName){
        JournalEntry existingEntry = journalEntryService.findByID(id).orElse(null);
        if(existingEntry!=null){
            existingEntry.setTitle(entry.getTitle()!=null && !entry.getTitle().isEmpty() ? entry.getTitle() : existingEntry.getTitle());
            existingEntry.setContent(entry.getContent()!=null && !entry.getContent().isEmpty()?entry.getContent():existingEntry.getContent());
            journalEntryService.saveEntrywithID(entry);
            return new ResponseEntity<>(existingEntry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
