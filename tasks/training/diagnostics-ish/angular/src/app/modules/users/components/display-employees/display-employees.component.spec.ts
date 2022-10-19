import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayEmployeesComponent } from './display-employees.component';

describe('DisplayEmployeesComponent', () => {
  let component: DisplayEmployeesComponent;
  let fixture: ComponentFixture<DisplayEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayEmployeesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
