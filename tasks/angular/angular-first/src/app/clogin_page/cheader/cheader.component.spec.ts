import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheaderComponent } from './cheader.component';

describe('CheaderComponent', () => {
  let component: CheaderComponent;
  let fixture: ComponentFixture<CheaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheaderComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
