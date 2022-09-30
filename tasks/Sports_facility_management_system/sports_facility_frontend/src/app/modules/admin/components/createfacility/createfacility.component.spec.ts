import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatefacilityComponent } from './createfacility.component';

describe('CreatefacilityComponent', () => {
  let component: CreatefacilityComponent;
  let fixture: ComponentFixture<CreatefacilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreatefacilityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreatefacilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
