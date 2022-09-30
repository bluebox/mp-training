import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpLoginComponent } from './emp-login.component';

describe('EmpLoginComponent', () => {
  let component: EmpLoginComponent;
  let fixture: ComponentFixture<EmpLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmpLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmpLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
