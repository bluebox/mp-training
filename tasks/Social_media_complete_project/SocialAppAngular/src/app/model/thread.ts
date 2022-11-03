import { User } from "./user";

export class Thread {
    id!: number;
    first_person!: User
    second_person!: User
    updated_at!: string;
    timestamp!: string;
}
