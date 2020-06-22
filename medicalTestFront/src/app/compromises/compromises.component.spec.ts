import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompromisesComponent } from './compromises.component';

describe('CompromisesComponent', () => {
  let component: CompromisesComponent;
  let fixture: ComponentFixture<CompromisesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompromisesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompromisesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
