// modal.component.ts
import { Component, OnInit } from '@angular/core';
import { ModalService } from '../../services/modal.service';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html'
})
export class ModalComponent implements OnInit {
  modalTitle: string = '';
  modalMessage: string = '';

  constructor(private modalService: ModalService) {}

  ngOnInit(): void {
    const modalElement = document.getElementById('modal');
    if (modalElement) {
      this.modalService.setModal(modalElement);
    }
  }

  get modalTitleText(): string {
    return this.modalService.getTitle();
  }

  get modalMessageText(): string {
    return this.modalService.getMessage();
  }

  hideModal() {
    this.modalService.hideModal();
  }
}
