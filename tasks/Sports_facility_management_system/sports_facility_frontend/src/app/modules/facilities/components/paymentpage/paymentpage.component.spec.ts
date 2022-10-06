import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentpageComponent } from './paymentpage.component';

describe('PaymentpageComponent', () => {
  let component: PaymentpageComponent;
  let fixture: ComponentFixture<PaymentpageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentpageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentpageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
