import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AlertModule} from 'ngx-bootstrap';
import {HttpClientModule} from '@angular/common/http';
import {AppComponent} from './app.component';
import {AssetComponent} from './asset/asset.component';
import {ServerDeviceComponent} from './server-device/server-device.component';
import {AssetTableComponent} from './asset-table/asset-table.component';
import {AssetSearchComponent} from './asset-search/asset-search.component';
import {AdvanacedAssetSearchComponent} from './advanaced-asset-search/advanaced-asset-search.component';
import {FormsModule} from "@angular/forms";
import {AssetService} from "./asset/asset.service";
import {RouterModule, Routes} from "@angular/router";

const appRoutes: Routes = [
  {
    path: 'asset-table/:field/:value',
    component: AssetTableComponent
  },
  {
    path: 'asset/:id',
    component: AssetComponent
  }
];

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
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    AlertModule.forRoot()
  ],
  providers: [HttpClientModule, AssetService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
