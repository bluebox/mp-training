import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeemaindashboardComponent } from './employeemaindashboard.component';

describe('EmployeemaindashboardComponent', () => {
  let component: EmployeemaindashboardComponent;
  let fixture: ComponentFixture<EmployeemaindashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeemaindashboardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeemaindashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
