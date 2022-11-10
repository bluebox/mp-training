import { TestBed } from '@angular/core/testing';

import { AboutGaurdGuard } from './about-gaurd.guard';

describe('AboutGaurdGuard', () => {
  let guard: AboutGaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AboutGaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
