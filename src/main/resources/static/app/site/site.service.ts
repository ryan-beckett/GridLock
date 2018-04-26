import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Site} from "../site/site";

@Injectable()
export class SiteService {

  private static API = "https://gridlock-backend.herokuapp.com/api/sites/";

  constructor(private http: HttpClient) {
  }

  getSites(): Observable<any> {
    return this.http.get(SiteService.API);
  }

  getSiteById(id: string): Observable<any> {
    return this.http.get(SiteService.API + "id/" + id);
  }

  createSite(site: Site): Observable<any> {
    return this.http.post(SiteService.API, JSON.stringify(site));
  }

  updateSite(id: string, site: Site): Observable<any> {
    return this.http.put(SiteService.API + "id/" + id, JSON.stringify(site));
  }

  deleteSite(id: string): Observable<any> {
    return this.http.delete(SiteService.API + "id/" + id);
  }
}
