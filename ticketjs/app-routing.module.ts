import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { TicketDetailsComponent } from './ticket-details/ticket-details.component';
import { TicketsComponent } from './tickets/tickets.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';

const routes: Routes = [
  {path:'Tickets',component:TicketsComponent},
  {path:'updateTicket/:id',component:UpdateTicketComponent},
  {path:'addTicket',component:AddTicketComponent},
  {path:'details/:id',component:TicketDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
