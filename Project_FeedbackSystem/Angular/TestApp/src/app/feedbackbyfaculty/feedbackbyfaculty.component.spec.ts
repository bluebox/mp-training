import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackbyfacultyComponent } from './feedbackbyfaculty.component';

describe('FeedbackbyfacultyComponent', () => {
  let component: FeedbackbyfacultyComponent;
  let fixture: ComponentFixture<FeedbackbyfacultyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackbyfacultyComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackbyfacultyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
