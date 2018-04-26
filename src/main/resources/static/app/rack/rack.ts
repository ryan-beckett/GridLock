import {RackableDevice} from "../rackable-device/rackable-device";
import {GridLocation} from "../grid-location/grid-location";

export class Rack {
  uHeight: number;
  devices: RackableDevice[];
  gridLocation: GridLocation;
}
