import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddslotsComponent } from './addslots.component';

describe('AddslotsComponent', () => {
  let component: AddslotsComponent;
  let fixture: ComponentFixture<AddslotsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddslotsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddslotsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
