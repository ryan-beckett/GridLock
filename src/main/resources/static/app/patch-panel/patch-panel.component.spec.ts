import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PatchPanelComponent} from './patch-panel.component';

describe('PatchPanelComponent', () => {
  let component: PatchPanelComponent;
  let fixture: ComponentFixture<PatchPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PatchPanelComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PatchPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
