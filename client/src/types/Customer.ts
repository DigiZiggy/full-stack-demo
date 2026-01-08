import {Contract} from "./Contract";

export type Customer = {
    customerId: number;
    firstName: string;
    lastName: string;
    email: string;
    mobile: string;
    address: string;
    picture: string;
    pictureUrl?: string;
    contracts?: Array<Contract>;
};
