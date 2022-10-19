import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayStudentComponent } from './display-student.component';

describe('DisplayStudentComponent', () => {
  let component: DisplayStudentComponent;
  let fixture: ComponentFixture<DisplayStudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayStudentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayStudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
