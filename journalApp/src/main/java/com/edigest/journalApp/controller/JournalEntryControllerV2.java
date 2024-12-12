package com.edigest.journalApp.controller;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")

public class JournalEntryControllerV2 {
	@Autowired
	private JournalEntryService journalEntryService;

	@PostMapping("/{userName}")
	public ResponseEntity<JournalEntry> createEntry(
			@RequestBody JournalEntry journalEntry,
			@PathVariable String userName
			) {
//		journalEntry.setDate(LocalDateTime.now());
//		journalEntryService.createContent(journalEntry);
//		return true;
		return journalEntryService.createContent(journalEntry,userName);

	}

	@GetMapping ("/{userName}")
	public ResponseEntity<List<JournalEntry>> allData(@PathVariable String userName) {
		return journalEntryService.getAllJournalEntriesOfUser(userName);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<JournalEntry>> getDataByid(
			@PathVariable ObjectId id
	                                                         ) {
//		Optional<JournalEntry> journalEntry = journalEntryService.getDataById(id);
//		return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
		//		Optional<JournalEntry> journalEntry = journalEntryService.getDataById(id);
//		if( journalEntry.isPresent() ) {
//			return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
//		}
//		return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);

		return journalEntryService.getDataById(id);
	}

	@DeleteMapping("/id/{userName}/{id}")

//	"?" means we are can return any class wrap inside the  responsentity.
	public ResponseEntity<?> deleteDataById(
			@PathVariable ObjectId id,
			@PathVariable String userName
	                                       ) {
		return journalEntryService.deleteDataById(id,userName);
	}

	@PutMapping("/id/{userName}/{id}")
	public ResponseEntity<JournalEntry> updateDataById(
			@PathVariable ObjectId id,
			@PathVariable String userName,
			@RequestBody JournalEntry journalEntry
	                                  ) {

		return journalEntryService.updateById(id, journalEntry);
	}
}
/*
 * Methods inside the class should be public so that they can access and invoked ny spring framework or external http request.
 *
 * Response entity is class part of springframework and is commonly used in spring applications to customize the HTTP response.
 * It provides methods for setting response status, headers, and body.You can use in different types of data in your controller methods such as JSON, XML or HTML.
 * */