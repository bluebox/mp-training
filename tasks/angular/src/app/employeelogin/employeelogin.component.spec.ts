import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeloginComponent } from './employeelogin.component';

describe('EmployeeloginComponent', () => {
  let component: EmployeeloginComponent;
  let fixture: ComponentFixture<EmployeeloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeloginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
