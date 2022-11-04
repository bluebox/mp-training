import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalloaneligibilitycehckComponent } from './personalloaneligibilitycehck.component';

describe('PersonalloaneligibilitycehckComponent', () => {
  let component: PersonalloaneligibilitycehckComponent;
  let fixture: ComponentFixture<PersonalloaneligibilitycehckComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalloaneligibilitycehckComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalloaneligibilitycehckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
