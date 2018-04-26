import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {User} from "./user";

@Injectable()
export class UserService {

  private static API = "https://gridlock-backend.herokuapp.com/api/users/";

  constructor(private http: HttpClient) {
  }

  getUsers(): Observable<any> {
    return this.http.get(UserService.API);
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(UserService.API + "id/" + id);
  }

  createUser(user: User): Observable<any> {
    return this.http.post(UserService.API, JSON.stringify(user));
  }

  updateUser(id: string, user: User): Observable<any> {
    return this.http.put(UserService.API + "id/" + id, JSON.stringify(user));
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete(UserService.API + "id/" + id);
  }
}
