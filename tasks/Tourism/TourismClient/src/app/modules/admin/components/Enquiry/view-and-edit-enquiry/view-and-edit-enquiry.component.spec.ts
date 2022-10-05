import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAndEditEnquiryComponent } from './view-and-edit-enquiry.component';

describe('ViewAndEditEnquiryComponent', () => {
  let component: ViewAndEditEnquiryComponent;
  let fixture: ComponentFixture<ViewAndEditEnquiryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAndEditEnquiryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewAndEditEnquiryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
