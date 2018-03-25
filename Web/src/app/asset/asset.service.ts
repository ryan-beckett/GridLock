import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AssetService {

  public ASSET_API = "//localhost:8080/api/assets";

  constructor(private http: HttpClient) {
  }

  getAssets(): Observable<any> {
    return this.http.get(this.ASSET_API)
  }

  getAssetsByName(name: string): Observable<any> {
    return this.http.get(this.ASSET_API + "/name/" + name);
  }

  getAssetById(id: string) {
    return this.http.get(this.ASSET_API + "/id/" + id);
  }
}
