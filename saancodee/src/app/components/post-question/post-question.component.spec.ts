import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostQuestionComponent } from './post-question.component';

describe('PostQuestionComponent', () => {
  let component: PostQuestionComponent;
  let fixture: ComponentFixture<PostQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostQuestionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
