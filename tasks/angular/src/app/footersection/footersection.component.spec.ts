import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FootersectionComponent } from './footersection.component';

describe('FootersectionComponent', () => {
  let component: FootersectionComponent;
  let fixture: ComponentFixture<FootersectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FootersectionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FootersectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
