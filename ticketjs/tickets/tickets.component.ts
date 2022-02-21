import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Tickets } from '../tickets';
import { TicketsService } from '../tickets.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-tickets',
  templateUrl: './tickets.component.html',
  styleUrls: ['./tickets.component.css']
})
export class TicketsComponent implements OnInit {
    Tickets!:Observable<Tickets[]>;
    ticket:any;
    raisedon2:any;
    raisedon1:any;
    depnum:any;

  constructor(private ticketsService:TicketsService,private route:ActivatedRoute,private router:Router) { }

  ngOnInit(): void {
    this.ticket=new Tickets();
    this.reloadData();
  }
  reloadData(){
    this.Tickets=this.ticketsService.getTickets();
  
    
  }
   getTickets(){

      this.reloadData();
    this.ticketsService.getTickets().subscribe((data: Tickets[]) => {
      console.log(data);
      this.reloadData();
    })
  }
    deleteTicket(id:number)  { 
      this.ticketsService.deleteTicket(id).subscribe(data=> {
        console.log(data);  
            this.reloadData();
             
            },
            error=>console.log(error));    
       }
       Ticketdetails(id:number)
       {
         this.router.navigate(['details',id]);
       }
       UpdateTicket(id:number){
         this.router.navigate(['updateTicket',id]);
         
       }

       getTicketreport()
       {
         this.ticketsService.getTicketreport().subscribe(data=>{
           console.log(data);
           this.Tickets=data;

         })
       }
       getreportbetween(raisedon2:any,raisedon1:any)
       {
         this.ticketsService.getreportbetween(raisedon2,raisedon1).subscribe(data=>{
        console.log(data);
        this.ticket=new Tickets();

      })
  }
  itnonitreport(depnum:number)
  {
    this.ticketsService.itandnonitreport(depnum).subscribe(data=>{
    console.log(data);
    });
    
  }
}