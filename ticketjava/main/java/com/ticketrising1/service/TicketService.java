package com.ticketrising1.service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.ticketrising1.entity.Employee;
import com.ticketrising1.entity.Ticket;

import net.sf.jasperreports.engine.JRException;


public interface TicketService {

	public List<Ticket> findAll();
	@Transactional
	public Map<String,String> deleteById(int theId);
	@Transactional
	public Map<String,String> addTicket(Ticket ticket);
	
	
	
	public Ticket findById(int theid);
	
	
	
	public Map<String, Object> Resolved(int theResolved);
	public Map<String, Object> raisedon(String raisedon);
	Map<String, Object> raisedbyAndRaisedon(int raisedby, String raisedon);
	public Map<String, Object> resolvedondate(int resolvedby, String resolvedon);
	public String Report() throws FileNotFoundException, JRException;

	
	@Query("select * from ticket t where t.raisedon >= :raisedon and t.raisedon <= :rasiedon1")
	Map<String, Object>  findByRaisedonBetween(@Param("raisedon") String raisedon,@Param("raisedon") String raisedon1);
	

	Map<String, Object>  generateMonthlyReport(String raisedon,String raisedon1) throws FileNotFoundException, JRException;
	
	public Map<String, Object> findByDeptNum(int deptNum) throws FileNotFoundException, JRException;
	ResponseEntity<Ticket> updateTicket(Ticket ticket);
	
	
	

	

}
