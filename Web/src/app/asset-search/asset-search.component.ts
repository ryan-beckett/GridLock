import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-asset-search',
  templateUrl: './asset-search.component.html',
  styleUrls: ['./asset-search.component.css']
})

export class AssetSearchComponent implements OnInit {

  searchName: string;

  constructor(private router: Router) {
  }

  ngOnInit() {
    this.searchName = "";
  }

  search() : void {
    if (this.searchName == "")
      alert("Please enter some search text.");
    else {
      this.router.navigate(['/asset-table', "name", this.searchName]);
    }
  }
}
