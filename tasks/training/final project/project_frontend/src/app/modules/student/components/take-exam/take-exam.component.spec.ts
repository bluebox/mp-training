import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TakeExamComponent } from './take-exam.component';

describe('TakeExamComponent', () => {
  let component: TakeExamComponent;
  let fixture: ComponentFixture<TakeExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TakeExamComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TakeExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
