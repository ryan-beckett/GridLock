import { Component, OnInit } from '@angular/core';
import {Asset} from "../asset/asset";
import {HttpClient} from "@angular/common/http";


@Component({
  selector: 'app-asset-table',
  templateUrl: './asset-table.component.html',
  styleUrls: ['./asset-table.component.css']
})
export class AssetTableComponent implements OnInit {

  assets: Asset[]

  constructor(private http: HttpClient) {

  }

  ngOnInit() {

  }

}
