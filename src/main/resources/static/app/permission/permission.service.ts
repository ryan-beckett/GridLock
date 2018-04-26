import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {Permission} from "./permission";

@Injectable()
export class PermissionService {

  private static API = "https://gridlock-backend.herokuapp.com/api/permissions/";

  constructor(private http: HttpClient) {
  }

  getPermissions(): Observable<any> {
    return this.http.get(PermissionService.API);
  }

  getPermissionById(id: string): Observable<any> {
    return this.http.get(PermissionService.API + "id/" + id);
  }

  createPermission(permission: Permission): Observable<any> {
    return this.http.post(PermissionService.API, JSON.stringify(permission));
  }

  updatePermission(id: string, permission: Permission): Observable<any> {
    return this.http.put(PermissionService.API + "id/" + id, JSON.stringify(permission));
  }

  deletePermission(id: string): Observable<any> {
    return this.http.delete(PermissionService.API + "id/" + id);
  }
}
