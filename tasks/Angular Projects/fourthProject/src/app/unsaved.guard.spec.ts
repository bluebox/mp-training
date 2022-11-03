import { TestBed } from '@angular/core/testing';

import { UnsavedGuard } from './unsaved.guard';

describe('UnsavedGuard', () => {
  let guard: UnsavedGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(UnsavedGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
