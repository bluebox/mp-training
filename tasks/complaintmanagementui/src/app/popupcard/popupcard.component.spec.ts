import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopupcardComponent } from './popupcard.component';

describe('PopupcardComponent', () => {
  let component: PopupcardComponent;
  let fixture: ComponentFixture<PopupcardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopupcardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopupcardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
