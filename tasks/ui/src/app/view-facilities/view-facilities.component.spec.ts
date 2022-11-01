import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFacilitiesComponent } from './view-facilities.component';

describe('ViewFacilitiesComponent', () => {
  let component: ViewFacilitiesComponent;
  let fixture: ComponentFixture<ViewFacilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFacilitiesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewFacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
