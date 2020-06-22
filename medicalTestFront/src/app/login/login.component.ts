import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

import { UserService } from '../user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: any;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = {};
    this.user.email = "Jadson@gmail.com";
  }

  signin(frm: FormGroup) {
    alert(this.user.email);
    this.userService.signin(this.user).subscribe(response => {
    });
  }

}
