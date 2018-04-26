import {ConfigurableDevice} from "../configurable-device/configurable-device";

export class Port {
  id: number;
  name: string;
  module: number;
  port: number;
  portType: string;
  sfpType: string;
  inUse: boolean;
  device: ConfigurableDevice;
}
