import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {StorageFrame} from "./storage-frame";

@Injectable()
export class StorageFrameService {

  private static API = "https://gridlock-backend.herokuapp.com/api/storage-frames/";

  constructor(private http: HttpClient) {
  }

  getStorageFrames(): Observable<any> {
    return this.http.get(StorageFrameService.API);
  }

  getStorageFrameById(id: string): Observable<any> {
    return this.http.get(StorageFrameService.API + "id/" + id);
  }

  createStorageFrame(storageFrame: StorageFrame): Observable<any> {
    return this.http.post(StorageFrameService.API, JSON.stringify(storageFrame));
  }

  updateStorageFrame(id: string, storageFrame: StorageFrame): Observable<any> {
    return this.http.put(StorageFrameService.API + "id/" + id, JSON.stringify(storageFrame));
  }

  deleteStorageFrame(id: string): Observable<any> {
    return this.http.delete(StorageFrameService.API + "id/" + id);
  }
}
