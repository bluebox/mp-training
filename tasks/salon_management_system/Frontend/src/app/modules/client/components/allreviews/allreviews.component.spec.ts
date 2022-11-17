import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllreviewsComponent } from './allreviews.component';

describe('AllreviewsComponent', () => {
  let component: AllreviewsComponent;
  let fixture: ComponentFixture<AllreviewsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllreviewsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AllreviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
