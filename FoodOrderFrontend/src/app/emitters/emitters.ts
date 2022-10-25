import { EventEmitter } from "@angular/core";

export class Emitters{
    static authEvent=new EventEmitter<boolean>()
}

export class LoginEmitterCustomer{
    static authEvent=new EventEmitter<boolean>()
}

export class LoginEmitterRestaurant{
    static authEvent=new EventEmitter<boolean>()
}