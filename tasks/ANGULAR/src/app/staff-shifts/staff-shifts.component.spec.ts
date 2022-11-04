import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffShiftsComponent } from './staff-shifts.component';

describe('StaffShiftsComponent', () => {
  let component: StaffShiftsComponent;
  let fixture: ComponentFixture<StaffShiftsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffShiftsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffShiftsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
