import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBookingComponent } from './edit-booking.component';

describe('EditBookingComponent', () => {
  let component: EditBookingComponent;
  let fixture: ComponentFixture<EditBookingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditBookingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditBookingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
