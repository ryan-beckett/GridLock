import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {BusinessUnit} from "./business-unit";

@Injectable()
export class BusinessUnitService {

  private static API = "https://gridlock-backend.herokuapp.com/api/business-units/";

  constructor(private http: HttpClient) {
  }

  getBusinessUnits(): Observable<any> {
    return this.http.get(BusinessUnitService.API);
  }

  getBusinessUnitById(id: string): Observable<any> {
    return this.http.get(BusinessUnitService.API + "id/" + id);
  }

  createBusinessUnit(businessUnit: BusinessUnit): Observable<any> {
    return this.http.post(BusinessUnitService.API, JSON.stringify(businessUnit));
  }

  updateBusinessUnit(id: string, businessUnit: BusinessUnit): Observable<any> {
    return this.http.put(BusinessUnitService.API + "id/" + id, JSON.stringify(businessUnit));
  }

  deleteBusinessUnit(id: string): Observable<any> {
    return this.http.delete(BusinessUnitService.API + "id/" + id);
  }
}
