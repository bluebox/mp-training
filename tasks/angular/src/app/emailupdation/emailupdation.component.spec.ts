import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailupdationComponent } from './emailupdation.component';

describe('EmailupdationComponent', () => {
  let component: EmailupdationComponent;
  let fixture: ComponentFixture<EmailupdationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmailupdationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmailupdationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
