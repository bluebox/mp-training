import { TestBed } from '@angular/core/testing';

import { AuthgaurdGuard } from './authgaurd.guard';

describe('AuthgaurdGuard', () => {
  let guard: AuthgaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(AuthgaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
