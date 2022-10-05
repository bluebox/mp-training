import { TestBed } from '@angular/core/testing';

import { TeacherServiceService } from './teacher-service.service';

describe('TeacherServiceService', () => {
  let service: TeacherServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TeacherServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
