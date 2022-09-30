import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityindexComponent } from './facilityindex.component';

describe('FacilityindexComponent', () => {
  let component: FacilityindexComponent;
  let fixture: ComponentFixture<FacilityindexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityindexComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityindexComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
