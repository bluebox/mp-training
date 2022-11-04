import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeliveryRegisterComponent } from './delivery-register.component';

describe('DeliveryRegisterComponent', () => {
  let component: DeliveryRegisterComponent;
  let fixture: ComponentFixture<DeliveryRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeliveryRegisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeliveryRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
