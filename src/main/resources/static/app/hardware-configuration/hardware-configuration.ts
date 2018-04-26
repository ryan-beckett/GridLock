import {Configuration} from "../configuration/configuration";

export class HardwareConfiguration extends Configuration {
  cpuManufacturer: string;
  cpuModel: string;
  cpuSpeedInGhz: string;
  numberOfCpus: number;
  ramManufacturer: string;
  ramModel: string;
  ramSizeInGB: number;
}
