import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvanacedAssetSearchComponent } from './advanaced-asset-search.component';

describe('AdvanacedAssetSearchComponent', () => {
  let component: AdvanacedAssetSearchComponent;
  let fixture: ComponentFixture<AdvanacedAssetSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvanacedAssetSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvanacedAssetSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
