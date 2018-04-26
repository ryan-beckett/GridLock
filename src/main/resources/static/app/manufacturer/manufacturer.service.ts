import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Manufacturer} from "./manufacturer";

@Injectable()
export class ManufacturerService {

  private static API = "https://gridlock-backend.herokuapp.com/api/manufacturers/";

  constructor(private http: HttpClient) {
  }

  getManufacturers(): Observable<any> {
    return this.http.get(ManufacturerService.API);
  }

  getManufacturerById(id: string): Observable<any> {
    return this.http.get(ManufacturerService.API + "id/" + id);
  }

  createManufacturer(manufacturer: Manufacturer): Observable<any> {
    return this.http.post(ManufacturerService.API, JSON.stringify(manufacturer));
  }

  updateManufacturer(id: string, manufacturer: Manufacturer): Observable<any> {
    return this.http.put(ManufacturerService.API + "id/" + id, JSON.stringify(manufacturer));
  }

  deleteManufacturer(id: string): Observable<any> {
    return this.http.delete(ManufacturerService.API + "id/" + id);
  }
}
