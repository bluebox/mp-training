import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeadsGridComponent } from './leads-grid.component';

describe('LeadsGridComponent', () => {
  let component: LeadsGridComponent;
  let fixture: ComponentFixture<LeadsGridComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeadsGridComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeadsGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
