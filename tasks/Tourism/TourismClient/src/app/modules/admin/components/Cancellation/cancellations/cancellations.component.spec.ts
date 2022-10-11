import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CancellationsComponent } from './cancellations.component';

describe('CancellationsComponent', () => {
  let component: CancellationsComponent;
  let fixture: ComponentFixture<CancellationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CancellationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CancellationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
