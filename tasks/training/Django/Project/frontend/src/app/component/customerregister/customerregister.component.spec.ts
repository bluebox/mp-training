import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerregisterComponent } from './customerregister.component';

describe('CustomerregisterComponent', () => {
  let component: CustomerregisterComponent;
  let fixture: ComponentFixture<CustomerregisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerregisterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerregisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
