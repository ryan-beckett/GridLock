import {inject, TestBed} from '@angular/core/testing';

import {ServerDeviceService} from './server-device.service';

describe('ServerDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ServerDeviceService]
    });
  });

  it('should be created', inject([ServerDeviceService], (service: ServerDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
