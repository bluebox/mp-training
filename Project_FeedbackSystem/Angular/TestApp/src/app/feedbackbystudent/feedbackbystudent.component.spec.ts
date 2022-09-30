import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackbystudentComponent } from './feedbackbystudent.component';

describe('FeedbackbystudentComponent', () => {
  let component: FeedbackbystudentComponent;
  let fixture: ComponentFixture<FeedbackbystudentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackbystudentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackbystudentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
