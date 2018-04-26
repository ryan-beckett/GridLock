import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {ServerDevice} from "./server-device";

@Injectable()
export class ServerDeviceService {

  private static API = "https://gridlock-backend.herokuapp.com/api/server-devices/";

  constructor(private http: HttpClient) {
  }

  getServerDevices(): Observable<any> {
    return this.http.get(ServerDeviceService.API);
  }

  getServerDeviceById(id: string): Observable<any> {
    return this.http.get(ServerDeviceService.API + "id/" + id);
  }

  createServerDevice(serverDevice: ServerDevice): Observable<any> {
    return this.http.post(ServerDeviceService.API, JSON.stringify(serverDevice));
  }

  updateServerDevice(id: string, serverDevice: ServerDevice): Observable<any> {
    return this.http.put(ServerDeviceService.API + "id/" + id, JSON.stringify(serverDevice));
  }

  deleteServerDevice(id: string): Observable<any> {
    return this.http.delete(ServerDeviceService.API + "id/" + id);
  }
}
