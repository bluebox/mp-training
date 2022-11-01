import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeecardComponent } from './employeecard.component';

describe('EmployeecardComponent', () => {
  let component: EmployeecardComponent;
  let fixture: ComponentFixture<EmployeecardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeecardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeecardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
