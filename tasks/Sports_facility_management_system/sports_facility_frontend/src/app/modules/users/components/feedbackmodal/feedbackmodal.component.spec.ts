import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackmodalComponent } from './feedbackmodal.component';

describe('FeedbackmodalComponent', () => {
  let component: FeedbackmodalComponent;
  let fixture: ComponentFixture<FeedbackmodalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedbackmodalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FeedbackmodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
