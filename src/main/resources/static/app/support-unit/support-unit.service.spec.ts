import {inject, TestBed} from '@angular/core/testing';

import {SupportUnitService} from './support-unit.service';

describe('SupportUnitService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SupportUnitService]
    });
  });

  it('should be created', inject([SupportUnitService], (service: SupportUnitService) => {
    expect(service).toBeTruthy();
  }));
});
