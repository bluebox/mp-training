import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAppointmentsComponent } from './display-appointments.component';

describe('DisplayAppointmentsComponent', () => {
  let component: DisplayAppointmentsComponent;
  let fixture: ComponentFixture<DisplayAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAppointmentsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
