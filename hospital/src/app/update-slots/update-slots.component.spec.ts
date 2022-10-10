import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateSlotsComponent } from './update-slots.component';

describe('UpdateSlotsComponent', () => {
  let component: UpdateSlotsComponent;
  let fixture: ComponentFixture<UpdateSlotsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateSlotsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateSlotsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
