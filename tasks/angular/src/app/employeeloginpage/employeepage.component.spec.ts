import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeepageComponent } from './employeepage.component';

describe('EmployeepageComponent', () => {
  let component: EmployeepageComponent;
  let fixture: ComponentFixture<EmployeepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
