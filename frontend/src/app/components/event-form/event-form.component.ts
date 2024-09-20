import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EventService } from '../../services/event.service';
import { Event } from '../../models/event';
import { ModalService } from '../../common/components/services/modal.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html'
})
export class EventFormComponent {
  eventForm = new FormGroup({
    name: new FormControl('', [
      Validators.required
    ]),
    startTime: new FormControl(new Date(), [
      Validators.required
    ]),
    endTime: new FormControl(new Date(), [
      Validators.required
    ]),
    maxParticipants: new FormControl(0, [
      Validators.required,
      Validators.minLength(1),
      Validators.maxLength(15),
      Validators.pattern(/^[0-9]+$/)
    ])
  });
  event = {
    name: '',
    startTime: new Date(),
    endTime: new Date(),
    maxParticipants: ''
  };

  constructor(private router: Router, private service: EventService, private modalService: ModalService) { }

  onSubmit() {
    if (this.eventForm.valid) {
      const startTime = new Date(this.event.startTime);
      const endTime = new Date(this.event.endTime);

      if (startTime > endTime) {
        this.modalService.showModal("Ebaõnnestumine", "Ürituse algusaeg peab olema enne lõppaega");
        return;
      }

      const event: Event = {
        id: 0,
        name: this.event.name,
        startTime: this.event.startTime,
        endTime: this.event.endTime,
        maxParticipants: parseInt(this.event.maxParticipants),
        participants: []
      };

      this.service.createEvent(event).subscribe({
        next: (response) => {
          this.modalService.showModal("Õnnestumine", "Üritus on edukalt salvestatud");
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error('Error creating event:', err);
          this.modalService.showModal("Ebaõnnestumine", "Ürituse loomine ebaõnnestus");
        }
      });
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }

}
