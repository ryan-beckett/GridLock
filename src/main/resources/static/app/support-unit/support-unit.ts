import {BusinessUnit} from "../business-unit/business-unit";
import {Contact} from "../contact/contact";
import {Role} from "../role/role";

export class SupportUnit {
  id: number;
  name: string;
  businessUnit: BusinessUnit;
  members: Contact[];
  roles: Role[];
}
