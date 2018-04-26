import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {NetworkDevice} from "./network-device";

@Injectable()
export class NetworkDeviceService {

  private static API = "https://gridlock-backend.herokuapp.com/api/network-devices/";

  constructor(private http: HttpClient) {
  }

  getNetworkDevices(): Observable<any> {
    return this.http.get(NetworkDeviceService.API);
  }

  getNetworkDeviceById(id: string): Observable<any> {
    return this.http.get(NetworkDeviceService.API + "id/" + id);
  }

  createNetworkDevice(networkDevice: NetworkDevice): Observable<any> {
    return this.http.post(NetworkDeviceService.API, JSON.stringify(networkDevice));
  }

  updateNetworkDevice(id: string, networkDevice: NetworkDevice): Observable<any> {
    return this.http.put(NetworkDeviceService.API + "id/" + id, JSON.stringify(networkDevice));
  }

  deleteNetworkDevice(id: string): Observable<any> {
    return this.http.delete(NetworkDeviceService.API + "id/" + id);
  }
}
