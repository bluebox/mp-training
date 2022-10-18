import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BgVideoComponentComponent } from './bg-video-component.component';

describe('BgVideoComponentComponent', () => {
  let component: BgVideoComponentComponent;
  let fixture: ComponentFixture<BgVideoComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BgVideoComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BgVideoComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
