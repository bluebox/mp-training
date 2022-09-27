import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloginPageComponent } from './clogin-page.component';

describe('CloginPageComponent', () => {
  let component: CloginPageComponent;
  let fixture: ComponentFixture<CloginPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CloginPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CloginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
