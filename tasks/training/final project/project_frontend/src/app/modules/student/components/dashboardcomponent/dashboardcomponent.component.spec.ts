import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardcomponentComponent } from './dashboardcomponent.component';

describe('DashboardcomponentComponent', () => {
  let component: DashboardcomponentComponent;
  let fixture: ComponentFixture<DashboardcomponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardcomponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardcomponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
