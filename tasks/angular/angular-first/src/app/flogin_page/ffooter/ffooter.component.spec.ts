import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FfooterComponent } from './ffooter.component';

describe('FfooterComponent', () => {
  let component: FfooterComponent;
  let fixture: ComponentFixture<FfooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FfooterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FfooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
