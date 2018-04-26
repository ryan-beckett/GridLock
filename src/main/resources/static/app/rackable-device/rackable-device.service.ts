import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {RackableDevice} from "./rackable-device";

@Injectable()
export class RackableDeviceService {

  private static API = "https://gridlock-backend.herokuapp.com/api/rackable-devices/";

  constructor(private http: HttpClient) {
  }

  getRackableDevices(): Observable<any> {
    return this.http.get(RackableDeviceService.API);
  }

  getRackableDeviceById(id: string): Observable<any> {
    return this.http.get(RackableDeviceService.API + "id/" + id);
  }

  createRackableDevice(rackableDevice: RackableDevice): Observable<any> {
    return this.http.post(RackableDeviceService.API, JSON.stringify(rackableDevice));
  }

  updateRackableDevice(id: string, rackableDevice: RackableDevice): Observable<any> {
    return this.http.put(RackableDeviceService.API + "id/" + id, JSON.stringify(rackableDevice));
  }

  deleteRackableDevice(id: string): Observable<any> {
    return this.http.delete(RackableDeviceService.API + "id/" + id);
  }
}
