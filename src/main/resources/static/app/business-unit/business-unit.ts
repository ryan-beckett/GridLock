import {Contact} from "../contact/contact";
import {Business} from "../business/business";

export class BusinessUnit {
  id: number;
  name: string;
  business: Business;
  contact: Contact;
}
