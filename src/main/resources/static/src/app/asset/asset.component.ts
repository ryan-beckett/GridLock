import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import * as _ from 'lodash';

interface Asset {
  id: number;
  purchaseOrderNumber: string;
  name: string;
  serial: string;
  manufacturer: object;
  model: string;
  partNumber: string;
  description: string;
  location: object;
  room: object;
  type: string;
  status: string;
  owner: object;
  serviceContract: object;
  cost: number
}

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.css']
})

export class AssetComponent implements OnInit {

  assets$ : object;

  constructor(private http:HttpClient) { }

  ngOnInit() {
    /*
    this.assets$ = this.http
      .get<Asset[]>("/api/assets")
      .map(data => _.values(data));
      */
    this.http.get("http://localhost:8080/api/assets").subscribe(res => this.assets$ = res);
  }

}
