import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {HardwareConfiguration} from "./hardware-configuration";

@Injectable()
export class HardwareConfigurationService {

  private static API = "https://gridlock-backend.herokuapp.com/api/hardware-configurations/";

  constructor(private http: HttpClient) {
  }

  getHardwareConfigurations(): Observable<any> {
    return this.http.get(HardwareConfigurationService.API);
  }

  getHardwareConfigurationById(id: string): Observable<any> {
    return this.http.get(HardwareConfigurationService.API + "id/" + id);
  }

  createHardwareConfiguration(hardwareConfiguration: HardwareConfiguration): Observable<any> {
    return this.http.post(HardwareConfigurationService.API, JSON.stringify(hardwareConfiguration));
  }

  updateHardwareConfiguration(id: string, hardwareConfiguration: HardwareConfiguration): Observable<any> {
    return this.http.put(HardwareConfigurationService.API + "id/" + id, JSON.stringify(hardwareConfiguration));
  }

  deleteHardwareConfiguration(id: string): Observable<any> {
    return this.http.delete(HardwareConfigurationService.API + "id/" + id);
  }
}
