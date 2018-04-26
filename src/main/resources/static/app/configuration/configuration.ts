import {ConfigurableDevice} from "../configurable-device/configurable-device";
import {SupportUnit} from "../support-unit/support-unit";
import {ServiceContract} from "../service-contract/service-contract";

export class Configuration {
  id: number;
  configurableDevice: ConfigurableDevice;
  configurationType: string;
  supportUnit: SupportUnit;
  description: string;
  notes: string;
  serviceContract: ServiceContract;
}
