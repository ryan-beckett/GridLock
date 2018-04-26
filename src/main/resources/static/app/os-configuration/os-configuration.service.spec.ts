import {inject, TestBed} from '@angular/core/testing';

import {OSConfigurationService} from './os-configuration.service';

describe('OSConfigurationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [OSConfigurationService]
    });
  });

  it('should be created', inject([OSConfigurationService], (service: OSConfigurationService) => {
    expect(service).toBeTruthy();
  }));
});
