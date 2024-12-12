package com.edigest.journalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class Users {
	     @Id
	private ObjectId id;
//	 This indexing is not working automatically. We have to define the automatic property in application properties for this indexing.
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	// This annotation means we create the reference of journalentry class inside the user class.
	@DBRef
	private List<JournalEntry> journalEntries=new ArrayList<>();
	private  List<String> role;
}
