import { TestBed, inject } from '@angular/core/testing';

import { RealisationService } from './realisation.service';

describe('RealisationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [RealisationService]
    });
  });

  it('should be created', inject([RealisationService], (service: RealisationService) => {
    expect(service).toBeTruthy();
  }));
});
