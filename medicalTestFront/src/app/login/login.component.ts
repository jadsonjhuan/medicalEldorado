import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from '../user.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: any;
  router: Router;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.user = {};
  }

  signin(formUser: FormGroup) {
    this.userService.signin(this.user).subscribe(response => {
      console.log(response);
    });
  }

}
