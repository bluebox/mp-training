import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewfacilitiesComponent } from './viewfacilities.component';

describe('ViewfacilitiesComponent', () => {
  let component: ViewfacilitiesComponent;
  let fixture: ComponentFixture<ViewfacilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewfacilitiesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewfacilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
