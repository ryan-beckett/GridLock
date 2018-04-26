import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {NetworkConnection} from "./network-connection";

@Injectable()
export class NetworkConnectionService {

  private static API = "https://gridlock-backend.herokuapp.com/api/network-connections/";

  constructor(private http: HttpClient) {
  }

  getNetworkConnections(): Observable<any> {
    return this.http.get(NetworkConnectionService.API);
  }

  getNetworkConnectionById(id: string): Observable<any> {
    return this.http.get(NetworkConnectionService.API + "id/" + id);
  }

  createNetworkConnection(networkConnection: NetworkConnection): Observable<any> {
    return this.http.post(NetworkConnectionService.API, JSON.stringify(networkConnection));
  }

  updateNetworkConnection(id: string, networkConnection: NetworkConnection): Observable<any> {
    return this.http.put(NetworkConnectionService.API + "id/" + id, JSON.stringify(networkConnection));
  }

  deleteNetworkConnection(id: string): Observable<any> {
    return this.http.delete(NetworkConnectionService.API + "id/" + id);
  }
}
