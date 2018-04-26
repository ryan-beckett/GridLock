import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {Room} from "./room";

@Injectable()
export class RoomService {

  private static API = "https://gridlock-backend.herokuapp.com/api/rooms/";

  constructor(private http: HttpClient) {
  }

  getRooms(): Observable<any> {
    return this.http.get(RoomService.API);
  }

  getRoomById(id: string): Observable<any> {
    return this.http.get(RoomService.API + "id/" + id);
  }

  createRoom(room: Room): Observable<any> {
    return this.http.post(RoomService.API, JSON.stringify(room));
  }

  updateRoom(id: string, room: Room): Observable<any> {
    return this.http.put(RoomService.API + "id/" + id, JSON.stringify(room));
  }

  deleteRoom(id: string): Observable<any> {
    return this.http.delete(RoomService.API + "id/" + id);
  }
}
