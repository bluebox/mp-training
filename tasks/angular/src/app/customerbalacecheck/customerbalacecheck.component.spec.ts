import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerbalacecheckComponent } from './customerbalacecheck.component';

describe('CustomerbalacecheckComponent', () => {
  let component: CustomerbalacecheckComponent;
  let fixture: ComponentFixture<CustomerbalacecheckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerbalacecheckComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerbalacecheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
