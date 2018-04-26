import {ConfigurableDevice} from "../configurable-device/configurable-device";
import {GridLocation} from "../grid-location/grid-location";

export class StorageFrame extends ConfigurableDevice {
  subType: string;
  gridLocation: GridLocation;
}
