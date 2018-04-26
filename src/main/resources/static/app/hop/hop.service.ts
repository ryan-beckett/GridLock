import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Hop} from "./hop";

@Injectable()
export class HopService {

  private static API = "https://gridlock-backend.herokuapp.com/api/hops/";

  constructor(private http: HttpClient) {
  }

  getHops(): Observable<any> {
    return this.http.get(HopService.API);
  }

  getHopById(id: string): Observable<any> {
    return this.http.get(HopService.API + "id/" + id);
  }

  createHop(hop: Hop): Observable<any> {
    return this.http.post(HopService.API, JSON.stringify(hop));
  }

  updateHop(id: string, hop: Hop): Observable<any> {
    return this.http.put(HopService.API + "id/" + id, JSON.stringify(hop));
  }

  deleteHop(id: string): Observable<any> {
    return this.http.delete(HopService.API + "id/" + id);
  }
}
