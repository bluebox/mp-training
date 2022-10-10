import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsershomepageComponent } from './usershomepage.component';

describe('UsershomepageComponent', () => {
  let component: UsershomepageComponent;
  let fixture: ComponentFixture<UsershomepageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsershomepageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsershomepageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
