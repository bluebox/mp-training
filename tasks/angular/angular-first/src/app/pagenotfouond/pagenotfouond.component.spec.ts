import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagenotfouondComponent } from './pagenotfouond.component';

describe('PagenotfouondComponent', () => {
  let component: PagenotfouondComponent;
  let fixture: ComponentFixture<PagenotfouondComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PagenotfouondComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PagenotfouondComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
