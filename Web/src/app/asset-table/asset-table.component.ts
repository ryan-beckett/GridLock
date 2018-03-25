import {Component, OnInit} from '@angular/core';
import {AssetService} from "../asset/asset.service";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-asset-table',
  templateUrl: './asset-table.component.html',
  styleUrls: ['./asset-table.component.css']
})
export class AssetTableComponent implements OnInit {

  assets: Array<any>;
  searchField: string;
  searchValue: string;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private assetService: AssetService) {
    route.params.subscribe(params => {
      this.searchField = params["field"];
      this.searchValue = params["value"];
      switch (this.searchField) {
        case "name":
          this.assetService.getAssetsByName(this.searchValue).subscribe(data => {
            this.assets = data;
          });
          break;
        default:
          break;
      }
    });
  }

  ngOnInit() {

  }
}
