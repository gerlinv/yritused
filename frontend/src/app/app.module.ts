import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EventRegisterComponent } from './components/event-register/event-register.component';
import { EventFormComponent } from './components/event-form/event-form.component';
import { EventTableComponent } from './components/event-table/event-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './components/login/login.component';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { ModalComponent } from './common/components/components/modal/modal.component';
import { SectionHeaderComponent } from './common/components/components/modal/section-header/section-header.component';

@NgModule({
  declarations: [
    AppComponent,
    EventRegisterComponent,
    EventFormComponent,
    EventTableComponent,
    LoginComponent,
    NavigationBarComponent,
    ModalComponent,
    SectionHeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    HttpClientXsrfModule,
    ReactiveFormsModule
  ],
  providers: [
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
