import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HardwareConfigurationComponent} from './hardware-configuration.component';

describe('HardwareConfigurationComponent', () => {
  let component: HardwareConfigurationComponent;
  let fixture: ComponentFixture<HardwareConfigurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [HardwareConfigurationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HardwareConfigurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
