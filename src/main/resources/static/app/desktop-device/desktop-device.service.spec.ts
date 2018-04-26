import {inject, TestBed} from '@angular/core/testing';

import {DesktopDeviceService} from './desktop-device.service';

describe('DesktopDeviceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DesktopDeviceService]
    });
  });

  it('should be created', inject([DesktopDeviceService], (service: DesktopDeviceService) => {
    expect(service).toBeTruthy();
  }));
});
