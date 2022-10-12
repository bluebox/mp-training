import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyMarksComponent } from './my-marks.component';

describe('MyMarksComponent', () => {
  let component: MyMarksComponent;
  let fixture: ComponentFixture<MyMarksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyMarksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MyMarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
