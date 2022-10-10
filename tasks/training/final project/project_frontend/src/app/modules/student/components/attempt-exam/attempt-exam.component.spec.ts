import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AttemptExamComponent } from './attempt-exam.component';

describe('AttemptExamComponent', () => {
  let component: AttemptExamComponent;
  let fixture: ComponentFixture<AttemptExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AttemptExamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AttemptExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
