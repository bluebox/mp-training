import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowDemoComponent } from './show-demo.component';

describe('ShowDemoComponent', () => {
  let component: ShowDemoComponent;
  let fixture: ComponentFixture<ShowDemoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowDemoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowDemoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
