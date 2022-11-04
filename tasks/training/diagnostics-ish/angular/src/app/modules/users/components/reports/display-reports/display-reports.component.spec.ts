import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayReportsComponent } from './display-reports.component';

describe('DisplayReportsComponent', () => {
  let component: DisplayReportsComponent;
  let fixture: ComponentFixture<DisplayReportsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayReportsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
