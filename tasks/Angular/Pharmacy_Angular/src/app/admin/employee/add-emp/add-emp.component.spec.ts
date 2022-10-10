import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEmpComponent } from './add-emp.component';

describe('AddEmpComponent', () => {
  let component: AddEmpComponent;
  let fixture: ComponentFixture<AddEmpComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEmpComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEmpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
