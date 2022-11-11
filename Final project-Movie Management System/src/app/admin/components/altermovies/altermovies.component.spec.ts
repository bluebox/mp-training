import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AltermoviesComponent } from './altermovies.component';

describe('AltermoviesComponent', () => {
  let component: AltermoviesComponent;
  let fixture: ComponentFixture<AltermoviesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AltermoviesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AltermoviesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
