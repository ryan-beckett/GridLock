import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {NetworkConfiguration} from "./network-configuration";

@Injectable()
export class NetworkConfigurationService {

  private static API = "https://gridlock-backend.herokuapp.com/api/network-configurations/";

  constructor(private http: HttpClient) {
  }

  getNetworkConfigurations(): Observable<any> {
    return this.http.get(NetworkConfigurationService.API);
  }

  getNetworkConfigurationById(id: string): Observable<any> {
    return this.http.get(NetworkConfigurationService.API + "id/" + id);
  }

  createNetworkConfiguration(networkConfiguration: NetworkConfiguration): Observable<any> {
    return this.http.post(NetworkConfigurationService.API, JSON.stringify(networkConfiguration));
  }

  updateNetworkConfiguration(id: string, networkConfiguration: NetworkConfiguration): Observable<any> {
    return this.http.put(NetworkConfigurationService.API + "id/" + id, JSON.stringify(networkConfiguration));
  }

  deleteNetworkConfiguration(id: string): Observable<any> {
    return this.http.delete(NetworkConfigurationService.API + "id/" + id);
  }
}
