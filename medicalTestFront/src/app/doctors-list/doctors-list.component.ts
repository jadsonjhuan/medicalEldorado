import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../doctor.service';

@Component({
  selector: 'app-doctors-list',
  templateUrl: './doctors-list.component.html',
  styleUrls: ['./doctors-list.component.css']
})
export class DoctorsListComponent implements OnInit {

  doctors: Array<any>;

  constructor(private doctorSerive: DoctorService) { }

  ngOnInit() {
    this.list();
  }

  list() {
    this.doctorSerive.list().subscribe(data => this.doctors = data);
  }

}
