package com.edigest.journalApp.entity;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document(collection = "journal")
@NoArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class JournalEntry {
	@Id
	private ObjectId id;
	private String title;
	private String content;
	private LocalDateTime date;


	/* public ObjectId getId() {
		return id;
	}

	public JournalEntry setId( ObjectId id ) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JournalEntry setTitle( String title ) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public JournalEntry setContent( String content ) {
		this.content = content;
		return this;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public JournalEntry setDate( LocalDateTime date ) {
		this.date = date;
		return this;
	}*/
}
