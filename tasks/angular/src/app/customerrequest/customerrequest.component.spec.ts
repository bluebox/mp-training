import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerrequestComponent } from './customerrequest.component';

describe('CustomerrequestComponent', () => {
  let component: CustomerrequestComponent;
  let fixture: ComponentFixture<CustomerrequestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerrequestComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerrequestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
