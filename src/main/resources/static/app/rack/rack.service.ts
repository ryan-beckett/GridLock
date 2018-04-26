import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Rack} from "./rack";

@Injectable()
export class RackService {

  private static API = "https://gridlock-backend.herokuapp.com/api/racks/";

  constructor(private http: HttpClient) {
  }

  getRacks(): Observable<any> {
    return this.http.get(RackService.API);
  }

  getRackById(id: string): Observable<any> {
    return this.http.get(RackService.API + "id/" + id);
  }

  createRack(rack: Rack): Observable<any> {
    return this.http.post(RackService.API, JSON.stringify(rack));
  }

  updateRack(id: string, rack: Rack): Observable<any> {
    return this.http.put(RackService.API + "id/" + id, JSON.stringify(rack));
  }

  deleteRack(id: string): Observable<any> {
    return this.http.delete(RackService.API + "id/" + id);
  }
}
