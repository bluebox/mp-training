import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProblemdiscussionComponent } from './problemdiscussion.component';

describe('ProblemdiscussionComponent', () => {
  let component: ProblemdiscussionComponent;
  let fixture: ComponentFixture<ProblemdiscussionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProblemdiscussionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProblemdiscussionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
