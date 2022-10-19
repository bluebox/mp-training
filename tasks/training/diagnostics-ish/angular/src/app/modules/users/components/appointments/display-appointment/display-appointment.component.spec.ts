import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayAppointmentComponent } from './display-appointment.component';

describe('DisplayAppointmentComponent', () => {
  let component: DisplayAppointmentComponent;
  let fixture: ComponentFixture<DisplayAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
