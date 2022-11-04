import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayticketComponent } from './displayticket.component';

describe('DisplayticketComponent', () => {
  let component: DisplayticketComponent;
  let fixture: ComponentFixture<DisplayticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DisplayticketComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DisplayticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
