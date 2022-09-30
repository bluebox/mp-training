import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeadsToolsComponent } from './leads-tools.component';

describe('LeadsToolsComponent', () => {
  let component: LeadsToolsComponent;
  let fixture: ComponentFixture<LeadsToolsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LeadsToolsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LeadsToolsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
