import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeacherLoginhomepageComponent } from './teacher-loginhomepage.component';

describe('TeacherLoginhomepageComponent', () => {
  let component: TeacherLoginhomepageComponent;
  let fixture: ComponentFixture<TeacherLoginhomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeacherLoginhomepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeacherLoginhomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
