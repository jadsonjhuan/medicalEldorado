import { Component, OnInit } from '@angular/core';
import { CompromiseService } from '../compromise.service';

@Component({
  selector: 'app-compromises',
  templateUrl: './compromises.component.html',
  styleUrls: ['./compromises.component.css']
})
export class CompromisesComponent implements OnInit {

  compromises: Array<any>;

  constructor(private compromiseService: CompromiseService) { }

  ngOnInit() {
    this.list();
  }

  list() {
    this.compromiseService.list().subscribe(data => this.compromises = data);
  }

}
