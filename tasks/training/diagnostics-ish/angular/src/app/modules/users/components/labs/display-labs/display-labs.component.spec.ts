import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayLabsComponent } from './display-labs.component';

describe('DisplayLabsComponent', () => {
  let component: DisplayLabsComponent;
  let fixture: ComponentFixture<DisplayLabsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayLabsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayLabsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
