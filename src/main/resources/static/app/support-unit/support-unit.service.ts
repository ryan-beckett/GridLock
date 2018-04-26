import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {SupportUnit} from "./support-unit";

@Injectable()
export class SupportUnitService {

  private static API = "https://gridlock-backend.herokuapp.com/api/support-units/";

  constructor(private http: HttpClient) {
  }

  getSupportUnits(): Observable<any> {
    return this.http.get(SupportUnitService.API);
  }

  getSupportUnitById(id: string): Observable<any> {
    return this.http.get(SupportUnitService.API + "id/" + id);
  }

  createSupportUnit(supportUnit: SupportUnit): Observable<any> {
    return this.http.post(SupportUnitService.API, JSON.stringify(supportUnit));
  }

  updateSupportUnit(id: string, supportUnit: SupportUnit): Observable<any> {
    return this.http.put(SupportUnitService.API + "id/" + id, JSON.stringify(supportUnit));
  }

  deleteSupportUnit(id: string): Observable<any> {
    return this.http.delete(SupportUnitService.API + "id/" + id);
  }
}
