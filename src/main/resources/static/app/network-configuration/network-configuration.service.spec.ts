import {inject, TestBed} from '@angular/core/testing';

import {NetworkConfigurationService} from './network-configuration.service';

describe('NetworkConfigurationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NetworkConfigurationService]
    });
  });

  it('should be created', inject([NetworkConfigurationService], (service: NetworkConfigurationService) => {
    expect(service).toBeTruthy();
  }));
});
