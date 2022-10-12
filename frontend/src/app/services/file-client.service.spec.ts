import {TestBed} from '@angular/core/testing';

import {FileClientService} from './file-client.service';

describe('FileClientService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FileClientService = TestBed.get(FileClientService);
    expect(service).toBeTruthy();
  });
});
