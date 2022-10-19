import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WelcomeAdminComponent } from './welcome-admin.component';

describe('WelcomeAdminComponent', () => {
  let component: WelcomeAdminComponent;
  let fixture: ComponentFixture<WelcomeAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WelcomeAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WelcomeAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
