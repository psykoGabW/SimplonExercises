import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayRealisationDetailsComponent } from './display-realisation-details.component';

describe('DisplayRealisationDetailsComponent', () => {
  let component: DisplayRealisationDetailsComponent;
  let fixture: ComponentFixture<DisplayRealisationDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayRealisationDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayRealisationDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
