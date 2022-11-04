import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PhonenumberupdateComponent } from './phonenumberupdate.component';

describe('PhonenumberupdateComponent', () => {
  let component: PhonenumberupdateComponent;
  let fixture: ComponentFixture<PhonenumberupdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PhonenumberupdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PhonenumberupdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
