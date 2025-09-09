package com.day3_;
public class Smart_Kitchen_Main {
    public static void main(String[] args) {
        Smart_Kitchen kitchen = new Smart_Kitchen();
        kitchen.setKitchenState(true, true, true);
        kitchen.doKitchenWork();
    }
}