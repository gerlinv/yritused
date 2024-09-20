import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Event } from '../../models/event';
import { EventService } from '../../services/event.service';
import { Person } from '../../models/person';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ModalService } from '../../common/components/services/modal.service';

@Component({
  selector: 'app-event-register',
  templateUrl: './event-register.component.html'
})
export class EventRegisterComponent {
  event?: Event;
  personForm = new FormGroup({
    firstName: new FormControl('', [
      Validators.required
    ]),
    lastName: new FormControl('', [
      Validators.required
    ]),
    identificationCode: new FormControl('', [
      Validators.required,
      Validators.minLength(11),
      Validators.maxLength(11),
      Validators.pattern(/^[0-9]+$/)
    ])
  });
  person = {
    firstName: '',
    lastName: '',
    identificationCode: ''
  };

  constructor(private route: ActivatedRoute, private router: Router, private service: EventService, private modalService: ModalService) {
    const navigation = this.router.getCurrentNavigation();
    if (navigation?.extras?.state?.['event']) {
      this.event = navigation.extras.state['event'];
    } else {
      console.error('Event data is missing');
      this.modalService.showModal("Süsteemi viga", "Ürituse andmed puuduvad");
    }
  }

  onSubmit() {
    if (this.event?.id && this.personForm.valid) {

      const currentIdCode = parseInt(this.person.identificationCode)

      const exists = this.event.participants?.some((participant: Person) =>
        participant.identificationCode === currentIdCode
      );

      if (exists) {
        this.modalService.showModal("Ebaõnnestumine", "Antud isikukoodiga isik on juba registreeritud.");
        return;
      }

      const person = new Person(0, this.person.firstName, this.person.lastName, currentIdCode);
      this.service.register(this.event.id, person)
        .subscribe(
          event => {
            if (event) {
              this.modalService.showModal("Õnnestumine", "Registreerumine õnnestus");
              this.router.navigate(['/']);
            } else {
              this.modalService.showModal("Ebaõnnestumine", "Registreerumine ebaõnnestus");
            }
          },
          error => {
            console.error('Error saving event:', error);
            this.modalService.showModal("Ebaõnnestumine", "Registreerumine ebaõnnestus");
          }
        );
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
