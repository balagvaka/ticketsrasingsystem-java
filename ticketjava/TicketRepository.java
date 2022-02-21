package com.ticketrising1.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.http.ResponseEntity;

import com.ticketrising1.entity.Ticket;


public interface TicketRepository extends JpaRepository<Ticket,Integer>{
	Ticket findById(int theid);

	List<Ticket> findByResolved(int theResolved);

	List<Ticket> raisedby(int raisedby);

	List<Ticket> raisedon(String raisedon);

	List<Ticket> findByRaisedbyAndRaisedon(int raisedby, String raisedon);
	List<Ticket> findByResolvedbyAndResolvedon(int resolvedby, String resolvedon);

	List<Ticket> findByRaisedonBetween(String raisedon, String raisedon1);



	
	

	

	

	



	

}
