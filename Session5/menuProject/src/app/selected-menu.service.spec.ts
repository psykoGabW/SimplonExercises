import { TestBed, inject } from '@angular/core/testing';

import { SelectedMenuService } from './selected-menu.service';

describe('SelectedMenuService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SelectedMenuService]
    });
  });

  it('should be created', inject([SelectedMenuService], (service: SelectedMenuService) => {
    expect(service).toBeTruthy();
  }));
});
