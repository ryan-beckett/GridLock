import {RackableDevice} from "../rackable-device/rackable-device";
import {NetworkConnection} from "../network-connection/network-connection";
import {Port} from "../port/port";

export class Hop {
  id: number;
  device: RackableDevice;
  port: Port;
  networkConnection: NetworkConnection;
}
