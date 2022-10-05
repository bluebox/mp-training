import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCouponComponent } from './add-coupon.component';

describe('AddCouponComponent', () => {
  let component: AddCouponComponent;
  let fixture: ComponentFixture<AddCouponComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCouponComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCouponComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
