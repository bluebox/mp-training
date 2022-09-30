import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeetasksComponent } from './employeetasks.component';

describe('EmployeetasksComponent', () => {
  let component: EmployeetasksComponent;
  let fixture: ComponentFixture<EmployeetasksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeetasksComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeetasksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
