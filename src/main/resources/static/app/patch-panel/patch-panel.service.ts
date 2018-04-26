import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {PatchPanel} from "./patch-panel";

@Injectable()
export class PatchPanelService {

  private static API = "https://gridlock-backend.herokuapp.com/api/patch-panels/";

  constructor(private http: HttpClient) {
  }

  getPatchPanels(): Observable<any> {
    return this.http.get(PatchPanelService.API);
  }

  getPatchPanelById(id: string): Observable<any> {
    return this.http.get(PatchPanelService.API + "id/" + id);
  }

  createPatchPanel(patchPanel: PatchPanel): Observable<any> {
    return this.http.post(PatchPanelService.API, JSON.stringify(patchPanel));
  }

  updatePatchPanel(id: string, patchPanel: PatchPanel): Observable<any> {
    return this.http.put(PatchPanelService.API + "id/" + id, JSON.stringify(patchPanel));
  }

  deletePatchPanel(id: string): Observable<any> {
    return this.http.delete(PatchPanelService.API + "id/" + id);
  }
}
