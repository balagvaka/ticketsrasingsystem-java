import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClient, HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TicketsComponent } from './tickets/tickets.component';
import { UpdateTicketComponent } from './update-ticket/update-ticket.component';
import { FormsModule } from '@angular/forms';
import { AddTicketComponent } from './add-ticket/add-ticket.component';
import { TicketDetailsComponent } from './ticket-details/ticket-details.component';



@NgModule({
  declarations: [
    AppComponent,
    TicketsComponent,
    UpdateTicketComponent,
    AddTicketComponent,
    TicketDetailsComponent,
    
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
