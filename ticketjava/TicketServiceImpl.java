package com.ticketrising1.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.ticketrising1.entity.Employee;
import com.ticketrising1.entity.Ticket;
import com.ticketrising1.repo.EmployeeRepository;
import com.ticketrising1.repo.TicketRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
@Service 
public class TicketServiceImpl implements TicketService{
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private EmployeeRepository employeeRepository;



	public TicketServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Ticket> findAll() {
		return this.ticketRepository.findAll();

	}

	@Override
	public Map<String, String> deleteById(int theId) {
		this.ticketRepository.deleteById(theId);


		HashMap<String,String> response= new HashMap<String,String>();

		response.put("status", "0");
		response.put("message","Deleted ticket Successfully");
		return response;

	}

	@Override
	public Map<String, String> addTicket(Ticket ticket) {
		ticket.setTicket_id(0);
		Ticket t=this.ticketRepository.save(ticket);
		HashMap<String,String> response= new HashMap<String,String>();
		if(t!=null)
		{
			response.put("status","0");
			response.put("message", "added new ticket sucessfull");
		}
		else
		{
			response.put("status","1");
			response.put("message", "failed to added new ticket");
		}
		return response;
	}
	@Override
	public ResponseEntity<Ticket> updateTicket(Ticket ticket) {
		
		
		return ResponseEntity.ok(this.ticketRepository.save(ticket));
	}
	

	@Override
	public Ticket findById(int ticket_id) {
	 
		
		return this.ticketRepository.findById(ticket_id);
	
	}

	@Override
	public Map<String, Object> Resolved(int theResolved) {
		List<Ticket> ticket  = this.ticketRepository.findByResolved(theResolved);
		List<Ticket> ticket1  = this.ticketRepository.findByResolved(theResolved);

		Map<String, Object> response = new HashMap<String,Object>();
		if(theResolved==1) {
			response.put("resolved tickets",ticket);
		}
		else if(theResolved==0)
		{
			response.put("not resolved tickets",ticket1);
		}
		return response;
	}

	@Override
	public Map<String, Object> raisedon(String raisedon) {
		List<Ticket> ticket  = this.ticketRepository.raisedon(raisedon);

		Map<String, Object> response = new HashMap<String,Object>();
		if(raisedon!=null) {
			response.put("tickets",ticket);
		}
		return response;
	}
	@Override
	public Map<String, Object> raisedbyAndRaisedon(int raisedby ,String raisedon) 
	{
		// TODO Auto-generated method stub
		List<Ticket> ticket=this.ticketRepository.findByRaisedbyAndRaisedon(raisedby,raisedon);
		Map<String, Object> response = new HashMap<String,Object>();
		if(raisedby>0&& raisedon!=null)
		{
			response.put("ticket", ticket);
		}
		else
		{
			response.put("status","1");
			response.put("message", "ticket not found on that date");
		}
		return response;
	}
	@Override
	public Map<String, Object> resolvedondate(int resolvedby, String resolvedon) {
		// TODO Auto-generated method stub
		List<Ticket> ticket =this.ticketRepository.findByResolvedbyAndResolvedon(resolvedby,resolvedon);
		Map<String,Object> response=new HashMap<String,Object>();
		if(resolvedby>0&& resolvedon!=null)
		{
			response.put("ticket", ticket);
		}
		else
		{
			response.put("status","1");
			response.put("message", "ticket not found on that date");
		}

		return response;
	}

	@Override
	public String Report() throws FileNotFoundException, JRException {
		{     
			List<Ticket> ticket=this.ticketRepository.findAll();
			File file=ResourceUtils.getFile("classpath:ticket2.jrxml");
			JasperReport jasperreport=JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(ticket);
			Map<String,Object> parameters=new HashMap<>();
			parameters.put("createdBy","ticketrasingsystem");
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperreport, parameters,dataSource);
				JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\jasper\\reports"+"\\ticket.html");
				JasperExportManager.exportReportToPdfFile(jasperPrint,"E:\\jasper\\reports"+"\\ticket.pdf");
			return "report generated";
		}
	}


	@Override
	public Map<String,Object> findByRaisedonBetween(String raisedon, String raisedon1) {
		List<Ticket> ticket  = this.ticketRepository.findByRaisedonBetween(raisedon,raisedon1);

		Map<String, Object> response = new HashMap<String,Object>();
		if(raisedon!=null&&raisedon1!=null) {
			response.put("tickets",ticket);
		}
		return response;
	}

	@Override
	public Map<String, Object> generateMonthlyReport(String raisedon, String raisedon1) throws FileNotFoundException, JRException {
		{
			List<Ticket> ticket=this.ticketRepository.findByRaisedonBetween(raisedon,raisedon1);
			File file=ResourceUtils.getFile("classpath:ticket2.jrxml");
			JasperReport jasperreport=JasperCompileManager.compileReport(file.getAbsolutePath());
			JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(ticket);
			Map<String, Object> response = new HashMap<String,Object>();
			Map<String,Object> parameters=new HashMap<>();
			parameters.put("createdBy","ticketrasingsystem");
			JasperPrint jasperPrint=JasperFillManager.fillReport(jasperreport, parameters,dataSource);
			
				JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\jasper\\reports"+"\\monthlyreport.html");
			
				JasperExportManager.exportReportToPdfFile(jasperPrint,"E:\\jasper\\reports"+"\\monthlyreport.pdf");
				response.put("status", "report Generated");
			
			return response;
		}
	}

	@Override
	public Map<String, Object> findByDeptNum(int deptNum) throws FileNotFoundException, JRException {

		List<Employee> employee  = this.employeeRepository.findByDeptNum(deptNum);
		Map<String, Object> response = new HashMap<String,Object>();
		File file=ResourceUtils.getFile("classpath:Employee.jrxml");
		JasperReport jasperreport=JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employee);
		Map<String, Object> parameters = new HashMap<String,Object>();
		parameters.put("employees",employee);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperreport, parameters,jrBeanCollectionDataSource);
		if(deptNum==1)
		{
		JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\jasper\\reports" + "ITEmployees.pdf");
		JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\jasper\\reports" + "ITEmployees.html");
		response.put("status", "suceessfully generated it employees");
		}
		else if(deptNum==0)
		{
		JasperExportManager.exportReportToPdfFile(jasperPrint, "E:\\jasper\\reports" + "NONITEmployees.pdf");
		JasperExportManager.exportReportToHtmlFile(jasperPrint,"E:\\jasper\\reports" + "NONITEmployees.html");
		response.put("status", "suceessfully generated non-it employees");
		}
		else
		{
			response.put("status", "not avilable");
		}

		return response;

	}
}
