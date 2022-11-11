import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TheatrelistComponent } from './theatrelist.component';

describe('TheatrelistComponent', () => {
  let component: TheatrelistComponent;
  let fixture: ComponentFixture<TheatrelistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TheatrelistComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TheatrelistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
