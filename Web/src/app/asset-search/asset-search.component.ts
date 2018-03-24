import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-asset-search',
  templateUrl: './asset-search.component.html',
  styleUrls: ['./asset-search.component.css']
})

export class AssetSearchComponent implements OnInit {

  searchField: string;
  searchValue: string;

  constructor() {
  }

  ngOnInit() {
  }

  search() : void {
    alert(this.searchField+" - "+this.searchValue);
  }
}
