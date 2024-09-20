import { Injectable } from '@angular/core';
import { Modal } from 'bootstrap';

@Injectable({
  providedIn: 'root',
})
export class ModalService {
  private modalElement: HTMLElement | null = null;
  private modalInstance: Modal | null = null;
  private modalTitle: string = '';
  private modalMessage: string = '';

  setModal(modalElement: HTMLElement) {
    this.modalElement = modalElement;
    this.modalInstance = new Modal(this.modalElement);
  }

  showModal(title: string, message: string) {
    if (this.modalElement && this.modalInstance) {
      this.modalTitle = title;
      this.modalMessage = message;
      
      this.modalElement.querySelector('.modal-title')!.textContent = title;
      this.modalElement.querySelector('.modal-body')!.textContent = message;

      this.modalInstance.show();
    }
  }

  hideModal() {
    if (this.modalInstance) {
      this.modalInstance.hide();
    }
  }

  getTitle(): string {
    return this.modalTitle;
  }

  getMessage(): string {
    return this.modalMessage;
  }
}
