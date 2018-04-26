import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OSConfigurationComponent} from './os-configuration.component';

describe('OSConfigurationComponent', () => {
  let component: OSConfigurationComponent;
  let fixture: ComponentFixture<OSConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [OSConfigurationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OSConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
