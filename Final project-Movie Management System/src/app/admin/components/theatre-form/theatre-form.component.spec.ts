import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TheatreFormComponent } from './theatre-form.component';

describe('TheatreFormComponent', () => {
  let component: TheatreFormComponent;
  let fixture: ComponentFixture<TheatreFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TheatreFormComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TheatreFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
