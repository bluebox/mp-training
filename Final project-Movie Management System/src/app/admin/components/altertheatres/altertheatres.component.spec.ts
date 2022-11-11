import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltertheatresComponent } from './altertheatres.component';

describe('AltertheatresComponent', () => {
  let component: AltertheatresComponent;
  let fixture: ComponentFixture<AltertheatresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AltertheatresComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AltertheatresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
