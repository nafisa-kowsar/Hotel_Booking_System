import { TestBed } from '@angular/core/testing';

import { JwtClientAdminService } from './jwt-client-admin.service';

describe('JwtClientAdminService', () => {
  let service: JwtClientAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JwtClientAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
