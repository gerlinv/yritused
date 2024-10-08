import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { catchError, first, Observable, throwError } from 'rxjs';
import { Event } from '../models/event';
import { Person } from '../models/person';
import { environment } from '../environment';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  private eventUrl = environment.eventApiUrl;

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getAuthHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`,
    });
  }

  getAllEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(this.eventUrl)
      .pipe(
        catchError(this.handleError)
      );
  }

  createEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.eventUrl, event, { headers: this.getAuthHeaders() })
      .pipe(
        catchError(this.handleError)
      );
  }

  register(eventId: number, person: Person) {
    const obj = { eventId: eventId, 
            firstName: person.firstName, 
            lastName: person.lastName, 
            identificationCode: person.identificationCode };
    return this.http.put<Event>(this.eventUrl, obj)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      errorMessage = `Error: ${error.error.message}`;
    } else {
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(() => new Error(errorMessage));
  }
  
}
