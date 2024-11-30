package com.project.journalApp.service;

import com.project.journalApp.entity.JournalEntry;
import com.project.journalApp.entity.UserEntry;
import com.project.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserEntryService userEntryService;

    @Transactional
    public void saveEntry(JournalEntry entry, String userName){
        UserEntry userEntry = userEntryService.findByName(userName);
        entry.setDate(new Date());
        JournalEntry savedEntry = journalEntryRepo.save(entry);
        userEntry.getJournalEntryList().add(savedEntry);
        userEntryService.saveEntry(userEntry);
    }

    public void saveEntrywithID(JournalEntry entry){
        journalEntryRepo.save(entry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findByID(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteEntry(ObjectId id, String userName){
        UserEntry userEntry = userEntryService.findByName(userName);
        userEntry.getJournalEntryList().removeIf(x->x.getId().equals(id));
        userEntryService.saveEntry(userEntry);
        journalEntryRepo.deleteById(id);
    }

}
