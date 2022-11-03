import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SnakebarComponent } from './snakebar.component';

describe('SnakebarComponent', () => {
  let component: SnakebarComponent;
  let fixture: ComponentFixture<SnakebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SnakebarComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SnakebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
