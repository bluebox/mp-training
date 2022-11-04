import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayCustomerComponent } from './display-customer.component';

describe('DisplayCustomerComponent', () => {
  let component: DisplayCustomerComponent;
  let fixture: ComponentFixture<DisplayCustomerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayCustomerComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayCustomerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
