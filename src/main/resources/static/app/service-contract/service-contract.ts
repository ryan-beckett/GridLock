import {Manufacturer} from "../manufacturer/manufacturer";

export class ServiceContract {
  id: number;
  type: string;
  contractor: Manufacturer;
  startDate: string;
  endDate: string;
}
