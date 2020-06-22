import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DoctorsListComponent } from './doctors-list/doctors-list.component';
import { DoctorService } from './doctor.service';
import { CompromiseService } from './compromise.service';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CompromisesComponent } from './compromises/compromises.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';


@NgModule({
  declarations: [
    AppComponent,
    DoctorsListComponent,
    CompromisesComponent,
    HomeComponent,
    LoginComponent,
    UserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatInputModule,
    MatButtonModule,
    FormsModule
  ],
  providers: [ DoctorService, CompromiseService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
