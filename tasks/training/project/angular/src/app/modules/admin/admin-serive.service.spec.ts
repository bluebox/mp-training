import { TestBed } from '@angular/core/testing';

import { AdminSeriveService } from './admin-serive.service';

describe('AdminSeriveService', () => {
  let service: AdminSeriveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminSeriveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
