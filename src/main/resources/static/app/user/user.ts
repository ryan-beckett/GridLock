import {Contact} from "../contact/contact";
import {BusinessUnit} from "../business-unit/business-unit";
import {UserProfile} from "../user-profile/user-profile";
import {Role} from "../role/role";

export class User {
  id: number;
  contact: Contact;
  username: string;
  password: string;
  businessUnit: BusinessUnit;
  userCreatedDateTime: string;
  lastLoginDateTime: string;
  deleted: boolean;
  userProfile: UserProfile;
  roles: Role[];
}
