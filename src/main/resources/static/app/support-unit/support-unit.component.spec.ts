import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SupportUnitComponent} from './support-unit.component';

describe('SupportUnitComponent', () => {
  let component: SupportUnitComponent;
  let fixture: ComponentFixture<SupportUnitComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SupportUnitComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SupportUnitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
