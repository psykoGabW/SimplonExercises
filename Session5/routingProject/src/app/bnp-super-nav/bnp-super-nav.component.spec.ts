
import { fakeAsync, ComponentFixture, TestBed } from '@angular/core/testing';

import { BnpSuperNavComponent } from './bnp-super-nav.component';

describe('BnpSuperNavComponent', () => {
  let component: BnpSuperNavComponent;
  let fixture: ComponentFixture<BnpSuperNavComponent>;

  beforeEach(fakeAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BnpSuperNavComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BnpSuperNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should compile', () => {
    expect(component).toBeTruthy();
  });
});
