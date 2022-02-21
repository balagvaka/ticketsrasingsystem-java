package com.ticketrising1.cotroller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketrising1.entity.Employee;
import com.ticketrising1.entity.Ticket;
import com.ticketrising1.service.TicketService;
import com.ticketrising1.service.TicketServiceImpl;

import net.sf.jasperreports.engine.JRException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class TicketCotroller {

	public TicketCotroller() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private TicketService ticketService;
	@GetMapping("/Ticket")
	public List<Ticket> fetchAllticket()
	{
		return this.ticketService.findAll();
	}
	
	@DeleteMapping("/Ticket/{theId}")
	public Map<String,String> deleteById(@PathVariable int theId)
	{
		return this.ticketService.deleteById(theId);
		
		
	}
	@PostMapping("/Ticket")
	public Map<String,String> addTicket(@RequestBody Ticket ticket)
	{
		return this.ticketService.addTicket(ticket);
		
	}
	  
	
	@PutMapping("/Ticket/{theId}")
	public ResponseEntity<Ticket> updateTickets(@PathVariable int theId,@RequestBody Ticket ticketDetails)  {
		Ticket ticket = ticketService.findById(theId);

		ticket.setTicket_id(ticketDetails.getTicket_id());
		ticket.setRaisedby(ticketDetails.getRaisedby());
		ticket.setTypeOfIssue(ticketDetails.getTypeOfIssue());
		ticket.setRaisedon(ticketDetails.getRaisedon());
		ticket.setResolved(ticketDetails.getResolved());
		ticket.setResolvedby(ticketDetails.getResolvedby());
		ticket.setResolvedon(ticketDetails.getResolvedon());
		ticket.setPriority(ticketDetails.getPriority());
		
		return this.ticketService.updateTicket(ticket);
		
	}
	
	
	
	@GetMapping("/Ticket/{theId}")
	public ResponseEntity<Ticket> findById(@PathVariable int theId)
			 {
	 Ticket ticket = ticketService.findById(theId);
		return ResponseEntity.ok().body(ticket);
	}
	@GetMapping("/resolve/{theResolved}")
	public Map<String, Object> findByResolved(@PathVariable int theResolved)
	{
		return this.ticketService.Resolved(theResolved);
	
		
		
	}
	@GetMapping("/raisedon/{raisedon}")
    public Map<String,Object> raisedon (@PathVariable String raisedon)
    {
    	return this.ticketService.raisedon(raisedon);
    }
	@GetMapping("/raisedbyAndRaisedon/{raisedby}/{raisedon}")
	public Map<String,Object>raisedbyAndRaisedon(@PathVariable int raisedby,@PathVariable String raisedon)
	{
		return this.ticketService.raisedbyAndRaisedon(raisedby, raisedon);
		
	}
	@GetMapping("/resolvedondate/{resolvedby}/{resolvedon}")
	public Map<String,Object> resolvedondate(@PathVariable int resolvedby,@PathVariable String resolvedon)
	{
		return this.ticketService.resolvedondate(resolvedby, resolvedon);
		
	}
	@GetMapping("/report")
	public String generateReport() throws FileNotFoundException, JRException
	{
		return ticketService.Report();
		
	}
	
	@GetMapping("/monthlyRaisedon/{raisedon}/{raisedon1}")
	public Map<String,Object> findByRaisedonBetween(@PathVariable String raisedon,@PathVariable String raisedon1)
	{
		return this.ticketService.findByRaisedonBetween(raisedon, raisedon1);
		
	}
	@GetMapping("/report/{raisedon}/{raisedon1}")
	public Map<String, Object> monthlyReport(@PathVariable String raisedon,@PathVariable String raisedon1) throws FileNotFoundException, JRException
	{
		return this.ticketService.generateMonthlyReport( raisedon, raisedon1);
	}
	
	@GetMapping("/report/{deptNum}")
	public Map<String, Object> findByDeptNum(@PathVariable int deptNum) throws FileNotFoundException, JRException
	{
		return ticketService.findByDeptNum(deptNum);
		
	}
	
	
	
	
	
}
 