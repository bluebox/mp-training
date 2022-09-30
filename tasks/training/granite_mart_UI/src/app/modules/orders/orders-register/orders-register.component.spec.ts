import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersRegisterComponent } from './orders-register.component';

describe('OrdersRegisterComponent', () => {
  let component: OrdersRegisterComponent;
  let fixture: ComponentFixture<OrdersRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrdersRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
