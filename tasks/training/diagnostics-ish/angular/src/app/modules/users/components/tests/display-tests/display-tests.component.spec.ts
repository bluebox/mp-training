import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayTestsComponent } from './display-tests.component';

describe('DisplayTestsComponent', () => {
  let component: DisplayTestsComponent;
  let fixture: ComponentFixture<DisplayTestsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayTestsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayTestsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
