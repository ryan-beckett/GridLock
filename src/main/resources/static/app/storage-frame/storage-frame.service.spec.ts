import {inject, TestBed} from '@angular/core/testing';

import {StorageFrameService} from './storage-frame.service';

describe('StorageFrameService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StorageFrameService]
    });
  });

  it('should be created', inject([StorageFrameService], (service: StorageFrameService) => {
    expect(service).toBeTruthy();
  }));
});
