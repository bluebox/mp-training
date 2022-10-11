import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllFeedbacksComponent } from './all-feedbacks.component';

describe('AllFeedbacksComponent', () => {
  let component: AllFeedbacksComponent;
  let fixture: ComponentFixture<AllFeedbacksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllFeedbacksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllFeedbacksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
