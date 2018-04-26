import {Contact} from "../contact/contact";
import {ConfigurableDevice} from "../configurable-device/configurable-device";

export class DesktopDevice extends ConfigurableDevice {
  user: Contact;
  subType: string;
}
