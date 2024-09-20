import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ModalService } from '../../common/components/services/modal.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  user = {
    email: '',
    password: '',
  };

  constructor(private authService: AuthService, private router: Router, private modalService: ModalService) { }

  onSubmit() {
    if (this.user.email && this.user.password) {
      console.log('email: ', this.user.email);
      console.log('password: ', this.user.password);
      this.authService.login(this.user.email, this.user.password).subscribe(
        () => {
          this.router.navigate(['/']);
        },
        (error) => {
          this.modalService.showModal("Sisselogimine ebaõnnestus", "Vale e-posti aadress või parool");
        }
      );
    }
  }

  goBack() {
    this.router.navigate(['/']);
  }
}
