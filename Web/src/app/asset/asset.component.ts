import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Asset} from "./asset";

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.css']
})

export class AssetComponent implements OnInit {

  asset: Asset;

  constructor(private http: HttpClient) {

  }

  ngOnInit() {
    //this.http.get("http://localhost:8080/api/assets").subscribe(res => this.assets$ = res);
  }

}
