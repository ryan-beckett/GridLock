import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {environment} from '../environments/environment';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CoreModule} from './core/core.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PagesModule} from './pages/pages.module';
import {StoreModule} from '@ngrx/store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {reducers} from './reducers';
import {EffectsModule} from '@ngrx/effects';
import {AgmCoreModule} from '@agm/core';
import {RouteHandlerModule} from './core/route-handler/route-handler.module';
import {HttpClientModule} from '@angular/common/http';
import {AssetService} from "./asset/asset.service";
import {BusinessService} from "./business/business.service";
import {LocationService} from "./location/location.service";
import {ContactService} from "./contact/contact.service";
import {BusinessUnitService} from "./business-unit/business-unit.service";
import {ManufacturerService} from "./manufacturer/manufacturer.service";
import {RoomService} from "./room/room.service";

@NgModule({
  imports: [
    BrowserModule.withServerTransition({appId: 'elastic-ui'}),
    BrowserAnimationsModule,
    HttpClientModule,
    StoreModule.forRoot(reducers),
    !environment.production ? StoreDevtoolsModule.instrument({maxAge: 50}) : [],
    EffectsModule.forRoot([]),
    AgmCoreModule.forRoot({
      apiKey: environment.googleMapsApiKey
    }),
    AppRoutingModule,
    CoreModule,
    PagesModule,
    RouteHandlerModule
  ],
  providers: [HttpClientModule, AssetService, BusinessService,
    BusinessUnitService, ContactService, LocationService,
    ManufacturerService, RoomService],
  declarations: [AppComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
}
