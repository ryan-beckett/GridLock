import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {GridLocation} from "./grid-location";

@Injectable()
export class GridLocationService {

  private static API = "https://gridlock-backend.herokuapp.com/api/grid-locations/";

  constructor(private http: HttpClient) {
  }

  getGridLocations(): Observable<any> {
    return this.http.get(GridLocationService.API);
  }

  getGridLocationById(id: string): Observable<any> {
    return this.http.get(GridLocationService.API + "id/" + id);
  }

  createGridLocation(gridLocation: GridLocation): Observable<any> {
    return this.http.post(GridLocationService.API, JSON.stringify(gridLocation));
  }

  updateGridLocation(id: string, gridLocation: GridLocation): Observable<any> {
    return this.http.put(GridLocationService.API + "id/" + id, JSON.stringify(gridLocation));
  }

  deleteGridLocation(id: string): Observable<any> {
    return this.http.delete(GridLocationService.API + "id/" + id);
  }
}
