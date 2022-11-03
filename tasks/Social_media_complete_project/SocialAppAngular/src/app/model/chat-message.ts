import { Thread } from "./thread";
import { User } from "./user";

export class ChatMessage {
    id!: number;
    thread!: Thread;
    timestamp!: string;
    seen!: boolean;
    message!: string;
    user!: User;
}
