import {Component, OnInit} from '@angular/core';
import {Asset} from "./asset";
import {AssetService} from "./asset.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.css']
})

export class AssetComponent implements OnInit {

  asset: Asset;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private assetService: AssetService) {
    this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.assetService.getAssetById(id).subscribe(data => {
          this.asset = data as Asset;
        });
      }
    });
  }

  ngOnInit() {
  }
}
