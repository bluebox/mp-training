import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeePaymentDetailsComponent } from './fee-payment-details.component';

describe('FeePaymentDetailsComponent', () => {
  let component: FeePaymentDetailsComponent;
  let fixture: ComponentFixture<FeePaymentDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeePaymentDetailsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeePaymentDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
