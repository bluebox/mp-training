import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityhomeComponent } from './facilityhome.component';

describe('FacilityhomeComponent', () => {
  let component: FacilityhomeComponent;
  let fixture: ComponentFixture<FacilityhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityhomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
