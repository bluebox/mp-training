import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelledBookingsComponent } from './cancelled-bookings.component';

describe('CancelledBookingsComponent', () => {
  let component: CancelledBookingsComponent;
  let fixture: ComponentFixture<CancelledBookingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancelledBookingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelledBookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
