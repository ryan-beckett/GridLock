import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {ServiceContract} from "./service-contract";

@Injectable()
export class ServiceContractService {

  private static API = "https://gridlock-backend.herokuapp.com/api/service-contracts/";

  constructor(private http: HttpClient) {
  }

  getServiceContracts(): Observable<any> {
    return this.http.get(ServiceContractService.API);
  }

  getServiceContractById(id: string): Observable<any> {
    return this.http.get(ServiceContractService.API + "id/" + id);
  }

  createServiceContract(serviceContract: ServiceContract): Observable<any> {
    return this.http.post(ServiceContractService.API, JSON.stringify(serviceContract));
  }

  updateServiceContract(id: string, serviceContract: ServiceContract): Observable<any> {
    return this.http.put(ServiceContractService.API + "id/" + id, JSON.stringify(serviceContract));
  }

  deleteServiceContract(id: string): Observable<any> {
    return this.http.delete(ServiceContractService.API + "id/" + id);
  }
}
