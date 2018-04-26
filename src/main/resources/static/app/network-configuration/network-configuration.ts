import {Configuration} from "../configuration/configuration";
import {NetworkConnection} from "../network-connection/network-connection";

export class NetworkConfiguration extends Configuration {
  hostname: string;
  workGroup: string;
  networkConnections: NetworkConnection[];
}
