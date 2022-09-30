import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSignUpComponent } from './employee-sign-up.component';

describe('EmployeeSignUpComponent', () => {
  let component: EmployeeSignUpComponent;
  let fixture: ComponentFixture<EmployeeSignUpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeSignUpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeSignUpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
