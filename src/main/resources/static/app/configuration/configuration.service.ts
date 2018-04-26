import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Configuration} from "./configuration";

@Injectable()
export class ConfigurationService {

  private static API = "https://gridlock-backend.herokuapp.com/api/configurations/";

  constructor(private http: HttpClient) {
  }

  getConfigurations(): Observable<any> {
    return this.http.get(ConfigurationService.API);
  }

  getConfigurationById(id: string): Observable<any> {
    return this.http.get(ConfigurationService.API + "id/" + id);
  }

  createConfiguration(configuration: Configuration): Observable<any> {
    return this.http.post(ConfigurationService.API, JSON.stringify(configuration));
  }

  updateConfiguration(id: string, configuration: Configuration): Observable<any> {
    return this.http.put(ConfigurationService.API + "id/" + id, JSON.stringify(configuration));
  }

  deleteConfiguration(id: string): Observable<any> {
    return this.http.delete(ConfigurationService.API + "id/" + id);
  }
}
