import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TourPageComponent } from './tour-page.component';

describe('TourPageComponent', () => {
  let component: TourPageComponent;
  let fixture: ComponentFixture<TourPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TourPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TourPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
