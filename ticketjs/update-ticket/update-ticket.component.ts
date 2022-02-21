import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tickets } from '../tickets';
import { TicketsService } from '../tickets.service';

@Component({
  selector: 'app-update-ticket',
  templateUrl: './update-ticket.component.html',
  styleUrls: ['./update-ticket.component.css']
})
export class UpdateTicketComponent implements OnInit {
    id:any;
    ticket:any;


  constructor(private route:ActivatedRoute,
    private router:Router,
    private ticketService:TicketsService) { }

  ngOnInit(): void {
    this.ticket=new Tickets();
    this.id=this.route.snapshot.params['id'];
    this.ticketService.getTicket(this.id).subscribe(data=>{
      console.log(data);
      this.ticket=data;
    });
  }
  updateTicket()
  {
    this.ticketService.updateTicket(this.id,this.ticket).subscribe(data=>console.log(data),error=>console.log(error));
    this.ticket=new Tickets();
    this.gotoList();
    
  }
  onsubmit()
  {
    this.updateTicket();

  }
  gotoList()
  {
    this.router.navigate(['/Tickets']);

  }

}
