import {Asset} from "../asset/asset";
import {NetworkConfiguration} from "../network-configuration/network-configuration";
import {OSConfiguration} from "../os-configuration/os-configuration";
import {HardwareConfiguration} from "../hardware-configuration/hardware-configuration";

export class ConfigurableDevice extends Asset {
  networkConfiguration: NetworkConfiguration;
  osConfiguration: OSConfiguration;
  hardwareConfiguration: HardwareConfiguration;
}
