import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EventTableComponent } from './components/event-table/event-table.component';
import { EventFormComponent } from './components/event-form/event-form.component';
import { EventRegisterComponent } from './components/event-register/event-register.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  { path: '', component: EventTableComponent },
  { path: 'form', component: EventFormComponent, canActivate: [AuthGuard] },
  { path: 'register/:id', component: EventRegisterComponent },
  { path: 'login', component: LoginComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
