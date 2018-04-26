import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Location} from "./location";

@Injectable()
export class LocationService {

  private static API = "https://gridlock-backend.herokuapp.com/api/locations/";

  constructor(private http: HttpClient) {
  }

  getLocations(): Observable<any> {
    return this.http.get(LocationService.API);
  }

  getLocationById(id: string): Observable<any> {
    return this.http.get(LocationService.API + "id/" + id);
  }

  createLocation(location: Location): Observable<any> {
    return this.http.post(LocationService.API, JSON.stringify(location));
  }

  updateLocation(id: string, location: Location): Observable<any> {
    return this.http.put(LocationService.API + "id/" + id, JSON.stringify(location));
  }

  deleteLocation(id: string): Observable<any> {
    return this.http.delete(LocationService.API + "id/" + id);
  }
}
