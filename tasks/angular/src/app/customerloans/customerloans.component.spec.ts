import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerloansComponent } from './customerloans.component';

describe('CustomerloansComponent', () => {
  let component: CustomerloansComponent;
  let fixture: ComponentFixture<CustomerloansComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerloansComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerloansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
