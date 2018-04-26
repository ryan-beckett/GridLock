import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {DesktopDevice} from "./desktop-device";

@Injectable()
export class DesktopDeviceService {

  private static API = "https://gridlock-backend.herokuapp.com/api/desktop-devices/";

  constructor(private http: HttpClient) {
  }

  getDesktopDevices(): Observable<any> {
    return this.http.get(DesktopDeviceService.API);
  }

  getDesktopDeviceById(id: string): Observable<any> {
    return this.http.get(DesktopDeviceService.API + "id/" + id);
  }

  createDesktopDevice(desktopDevice: DesktopDevice): Observable<any> {
    return this.http.post(DesktopDeviceService.API, JSON.stringify(desktopDevice));
  }

  updateDesktopDevice(id: string, desktopDevice: DesktopDevice): Observable<any> {
    return this.http.put(DesktopDeviceService.API + "id/" + id, JSON.stringify(desktopDevice));
  }

  deleteDesktopDevice(id: string): Observable<any> {
    return this.http.delete(DesktopDeviceService.API + "id/" + id);
  }
}
