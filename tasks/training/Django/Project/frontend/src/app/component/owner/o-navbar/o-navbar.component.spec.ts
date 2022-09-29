import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ONavbarComponent } from './o-navbar.component';

describe('ONavbarComponent', () => {
  let component: ONavbarComponent;
  let fixture: ComponentFixture<ONavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ONavbarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ONavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
