package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class JournalEntryService {
	@Autowired
	private JournalEntryRepo journalEntryRepo;
	@Autowired
	private UserService userService;


	//	Create Entry
	@Transactional
//	This annotation treate  the whole method as a single opartion. If a sigle line showing the whole mathod should be roobback...

//	 Too use this annotyation we are bound to enable transaction management in main class..
	public ResponseEntity<JournalEntry> createContent( JournalEntry journalEntry, String name ) {

		try {
			Users user = userService.getDataByName(name);
			journalEntry.setDate(LocalDateTime.now());
			JournalEntry save = journalEntryRepo.save(journalEntry);
			user.getJournalEntries().add(save);
			userService.createUser(user);
			return new ResponseEntity<JournalEntry>(HttpStatus.CREATED);
		} catch( Exception e ) {

			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<JournalEntry>> getAllJournalEntriesOfUser( String userName ) {
		Users user = userService.getDataByName(userName);
		List<JournalEntry> all = user.getJournalEntries();
		if( all != null && all.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	//	Retrieve all data
	/* public ResponseEntity<List<JournalEntry>> getAll() {
		List<JournalEntry> all = journalEntryRepo.findAll();
		if( all != null && all.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(all, HttpStatus.OK);

	}*/

	//	Retrieve the data by id
	public ResponseEntity<Optional<JournalEntry>> getDataById( ObjectId id ) {
		Optional<JournalEntry> dataById = journalEntryRepo.findById(id);
		if( dataById.isEmpty() ) {
			return new ResponseEntity<>(dataById, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> deleteDataById( ObjectId id, String userName ) {

		Users user = userService.getDataByName(userName);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		userService.createUser(user);
		journalEntryRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

//	Delete By Id

	//	Update the data by id.
	public ResponseEntity<JournalEntry> updateById( ObjectId id, JournalEntry newEntry ) {
		JournalEntry old = journalEntryRepo.findById(id).orElse(null);

		if( old != null ) {
			old.setTitle(newEntry.getTitle() != null &&
					             !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent() != null &&
					               !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
			createEntry(old);
			return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

	public void createEntry( JournalEntry journalEntry ) {
		journalEntryRepo.save(journalEntry);
	}


}
