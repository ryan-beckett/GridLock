import {inject, TestBed} from '@angular/core/testing';

import {StorageDeviceService} from './storage-device.service';

describe('StorageDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StorageDeviceService]
    });
  });

  it('should be created', inject([StorageDeviceService], (service: StorageDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
