import {Component, Input, OnInit} from '@angular/core';
import {AssetService} from "../../../../asset/asset.service";

@Component({
  selector: 'vr-state-widget',
  templateUrl: './state-widget.component.html',
  styleUrls: ['./state-widget.component.scss']
})
export class StateWidgetComponent implements OnInit {

  backgroundColor = '#FFF';
  textColor = '#111';
  value: number;
  @Input() valuePrefix = '';
  property: string = "Total Assets";
  @Input() icon: string;
  @Input() changePercent: string;
  @Input() changeIcon: string;

  constructor(private assetService: AssetService) {
    this.assetService.count().subscribe(data => {
      this.value = data as number;
    });
  }

  ngOnInit() {
  }

}
