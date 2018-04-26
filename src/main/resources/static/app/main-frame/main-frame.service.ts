import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {MainFrame} from "./main-frame";

@Injectable()
export class MainFrameService {

  private static API = "https://gridlock-backend.herokuapp.com/api/main-frames/";

  constructor(private http: HttpClient) {
  }

  getMainFrames(): Observable<any> {
    return this.http.get(MainFrameService.API);
  }

  getMainFrameById(id: string): Observable<any> {
    return this.http.get(MainFrameService.API + "id/" + id);
  }

  createMainFrame(mainFrame: MainFrame): Observable<any> {
    return this.http.post(MainFrameService.API, JSON.stringify(mainFrame));
  }

  updateMainFrame(id: string, mainFrame: MainFrame): Observable<any> {
    return this.http.put(MainFrameService.API + "id/" + id, JSON.stringify(mainFrame));
  }

  deleteMainFrame(id: string): Observable<any> {
    return this.http.delete(MainFrameService.API + "id/" + id);
  }
}
