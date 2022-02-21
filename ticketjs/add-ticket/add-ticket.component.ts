import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Tickets } from '../tickets';
import { TicketsService } from '../tickets.service';

@Component({
  selector: 'app-add-ticket',
  templateUrl: './add-ticket.component.html',
  styleUrls: ['./add-ticket.component.css']
})
export class AddTicketComponent implements OnInit {

  ticket: Tickets = new Tickets();
  submitted = false;
  dateTime!:Date;

  constructor(private ticketService: TicketsService,
    private router: Router) { }


  ngOnInit(): void {
    this.dateTime=new Date; ;
  }
  newTicket(): void {
    this.submitted = false;
    this.ticket = new Tickets();
  }

  save() {
    this.ticketService
    .createTicket(this.ticket).subscribe(data => {
      console.log(data)
      this.ticket = new Tickets();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/Tickets']);
  }
}

