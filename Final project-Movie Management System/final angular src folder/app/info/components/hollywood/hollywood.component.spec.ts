import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HollywoodComponent } from './hollywood.component';

describe('HollywoodComponent', () => {
  let component: HollywoodComponent;
  let fixture: ComponentFixture<HollywoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HollywoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HollywoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
