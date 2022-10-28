import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PopularfoodComponent } from './popularfood.component';

describe('PopularfoodComponent', () => {
  let component: PopularfoodComponent;
  let fixture: ComponentFixture<PopularfoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PopularfoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PopularfoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
