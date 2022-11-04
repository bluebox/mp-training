import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTerminalComponent } from './show-terminal.component';

describe('ShowTerminalComponent', () => {
  let component: ShowTerminalComponent;
  let fixture: ComponentFixture<ShowTerminalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowTerminalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowTerminalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
