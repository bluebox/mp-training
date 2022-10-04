import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustCartComponent } from './cust-cart.component';

describe('CustCartComponent', () => {
  let component: CustCartComponent;
  let fixture: ComponentFixture<CustCartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustCartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustCartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
