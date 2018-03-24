import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AssetComponent} from './asset/asset.component';
import { ServerDeviceComponent } from './server-device/server-device.component';
import { AssetTableComponent } from './asset-table/asset-table.component';
import { AssetSearchComponent } from './asset-search/asset-search.component';
import { AdvanacedAssetSearchComponent } from './advanaced-asset-search/advanaced-asset-search.component';


@NgModule({
  declarations: [
    AppComponent,
    AssetComponent,
    ServerDeviceComponent,
    AssetTableComponent,
    AssetSearchComponent,
    AdvanacedAssetSearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AlertModule.forRoot()
  ],
  providers: [HttpClientModule],
  bootstrap: [AppComponent]
})
export class AppModule { }
