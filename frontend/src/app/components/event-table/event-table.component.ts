import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event } from '../../models/event';

@Component({
  selector: 'app-event-table',
  templateUrl: './event-table.component.html'
})
export class EventTableComponent {
  events: Event[] = [];

  constructor(private router: Router, private service: EventService) {
  }

  ngOnInit(): void {
    this.getData();
  }

  register(event: Event): void {
    if (this.getAvailableSpots(event) > 0) {
      this.router.navigate(['/register', event.id], { state: { event: event } });
    }
  }

  getAvailableSpots(event: Event): number {
    if (event.participants) {
      return event.maxParticipants - event.participants.length;
    } return event.maxParticipants;
  }

  getData(): void {
    this.service.getAllEvents().subscribe(eventList => {
        this.events = eventList != null ? eventList : [];
      },
      error => {
        console.error('Error fetching events:', error);
      }
    );
  }

}
