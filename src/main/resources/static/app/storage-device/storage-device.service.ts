import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {StorageDevice} from "./storage-device";

@Injectable()
export class StorageDeviceService {

  private static API = "https://gridlock-backend.herokuapp.com/api/storage-devices/";

  constructor(private http: HttpClient) {
  }

  getStorageDevices(): Observable<any> {
    return this.http.get(StorageDeviceService.API);
  }

  getStorageDeviceById(id: string): Observable<any> {
    return this.http.get(StorageDeviceService.API + "id/" + id);
  }

  createStorageDevice(storageDevice: StorageDevice): Observable<any> {
    return this.http.post(StorageDeviceService.API, JSON.stringify(storageDevice));
  }

  updateStorageDevice(id: string, storageDevice: StorageDevice): Observable<any> {
    return this.http.put(StorageDeviceService.API + "id/" + id, JSON.stringify(storageDevice));
  }

  deleteStorageDevice(id: string): Observable<any> {
    return this.http.delete(StorageDeviceService.API + "id/" + id);
  }
}
