import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentLoginhomepageComponent } from './student-loginhomepage.component';

describe('StudentLoginhomepageComponent', () => {
  let component: StudentLoginhomepageComponent;
  let fixture: ComponentFixture<StudentLoginhomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentLoginhomepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentLoginhomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
