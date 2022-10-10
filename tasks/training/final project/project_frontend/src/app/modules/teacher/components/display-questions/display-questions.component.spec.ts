import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayQuestionsComponent } from './display-questions.component';

describe('DisplayQuestionsComponent', () => {
  let component: DisplayQuestionsComponent;
  let fixture: ComponentFixture<DisplayQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
