import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Port} from "./port";

@Injectable()
export class PortService {

  private static API = "https://gridlock-backend.herokuapp.com/api/ports/";

  constructor(private http: HttpClient) {
  }

  getPorts(): Observable<any> {
    return this.http.get(PortService.API);
  }

  getPortById(id: string): Observable<any> {
    return this.http.get(PortService.API + "id/" + id);
  }

  createPort(port: Port): Observable<any> {
    return this.http.post(PortService.API, JSON.stringify(port));
  }

  updatePort(id: string, port: Port): Observable<any> {
    return this.http.put(PortService.API + "id/" + id, JSON.stringify(port));
  }

  deletePort(id: string): Observable<any> {
    return this.http.delete(PortService.API + "id/" + id);
  }
}
