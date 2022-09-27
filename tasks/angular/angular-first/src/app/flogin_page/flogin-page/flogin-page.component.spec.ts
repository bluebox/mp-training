import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FloginPageComponent } from './flogin-page.component';

describe('FloginPageComponent', () => {
  let component: FloginPageComponent;
  let fixture: ComponentFixture<FloginPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FloginPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FloginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
