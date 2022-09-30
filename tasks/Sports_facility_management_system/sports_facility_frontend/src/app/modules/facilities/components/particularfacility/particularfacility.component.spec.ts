import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticularfacilityComponent } from './particularfacility.component';

describe('ParticularfacilityComponent', () => {
  let component: ParticularfacilityComponent;
  let fixture: ComponentFixture<ParticularfacilityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ParticularfacilityComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ParticularfacilityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
