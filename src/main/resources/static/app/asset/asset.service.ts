import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Asset} from "./asset";

declare var $: any;

@Injectable()
export class AssetService {

  private static API = "https://gridlock-backend.herokuapp.com/api/assets/";

  constructor(private http: HttpClient) {
  }

  getAssets(): Observable<any> {
    return this.http.get(AssetService.API);
  }

  getAssetById(id: string): Observable<any> {
    return this.http.get(AssetService.API + "id/" + id);
  }

  createAsset(asset: Asset): Observable<any> {
    return this.http.post(AssetService.API, JSON.stringify(asset));
  }

  updateAsset(id: string, asset: Asset): Observable<any> {
    return this.http.put(AssetService.API + "id/" + id, JSON.stringify(asset));
  }

  deleteAsset(id: string): Observable<any> {
    return this.http.delete(AssetService.API + "id/" + id);
  }

  getAssetsByName(name: string): Observable<any> {
    return this.http.get(AssetService.API + "name/" + name);
  }

  getAssetByQueryParams(params: {}): Observable<any> {
    return this.http.get(AssetService.API + "query/?" + $.param(params));
  }

  count(): Observable<any> {
    return this.http.get(AssetService.API + "count");
  }
}
