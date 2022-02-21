import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Tickets } from './tickets';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {
  private baseUrl = "http://localhost:8500/api/Ticket"
  private baseUrl1 = "http://localhost:8500/api/report"

  constructor(private http: HttpClient) { }
  
  getTickets(): Observable<any>{
    return this.http.get(`${this.baseUrl}`);

}
  getTicket(id:number):Observable<any>{
  return this.http.get(`${this.baseUrl}/${id}`);
}
deleteTicket(id:number): Observable<any> {
  return this.http.delete(`${this.baseUrl}/${id}`,{ responseType: 'text' });
}
createTicket(ticket :object):Observable<object>{
  return this.http.post(`${this.baseUrl}`,ticket);
}
updateTicket(id:number,value:any):Observable<object>{
return this.http.put(`${this.baseUrl}/${id}`,value)
}


getTicketreport():Observable<any>{
  return this.http.get(`${this.baseUrl1}`);
}
getreportbetween(raisedon2:any,raisedon1:any)
{
  return this.http.get(`${this.baseUrl1}/${raisedon2}/${raisedon1}`)
}
itandnonitreport(depnum:number)
{
  return this.http.get(`${this.baseUrl1}/${depnum}`);
}


}
