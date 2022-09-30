import { TestBed } from '@angular/core/testing';

import { MovieserviceService } from './movieservice.service';

describe('MovieserviceService', () => {
  let service: MovieserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MovieserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
