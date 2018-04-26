import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {StorageFrameComponent} from './storage-frame.component';

describe('StorageFrameComponent', () => {
  let component: StorageFrameComponent;
  let fixture: ComponentFixture<StorageFrameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [StorageFrameComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StorageFrameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
