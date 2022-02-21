import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tickets } from '../tickets';
import { TicketsService } from '../tickets.service';

@Component({
  selector: 'app-ticket-details',
  templateUrl: './ticket-details.component.html',
  styleUrls: ['./ticket-details.component.css']
})
export class TicketDetailsComponent implements OnInit {
  id:any;
  ticket:any;

  constructor(private route:ActivatedRoute,
    private router:Router,
    private ticketService:TicketsService) { }

  ngOnInit():void {
    this.ticket=new Tickets();
    this.id=this.route.snapshot.params['id'];
    this.ticketService.getTicket(this.id).subscribe(data=>{
      console.log(data);
      this.ticket=data;
    });
  }
  list()
  {
    this.router.navigate(['/Tickets']);
  }

}
