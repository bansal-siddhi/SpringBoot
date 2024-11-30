package com.project.journalApp.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "users")
public class UserEntry {
    @Id
    private ObjectId userID;
    @Indexed(unique = true)
    @NonNull
    private String userName;
    @NonNull
    private String userPass;
    @DBRef
    private List<JournalEntry> journalEntryList = new ArrayList<>();
}
