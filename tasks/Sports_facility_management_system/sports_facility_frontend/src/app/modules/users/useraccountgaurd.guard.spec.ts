import { TestBed } from '@angular/core/testing';

import { UseraccountgaurdGuard } from './useraccountgaurd.guard';

describe('UseraccountgaurdGuard', () => {
  let guard: UseraccountgaurdGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(UseraccountgaurdGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
