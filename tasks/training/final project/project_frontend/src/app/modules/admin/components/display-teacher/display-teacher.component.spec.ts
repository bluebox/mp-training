import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayTeacherComponent } from './display-teacher.component';

describe('DisplayTeacherComponent', () => {
  let component: DisplayTeacherComponent;
  let fixture: ComponentFixture<DisplayTeacherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayTeacherComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayTeacherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
