package com;

class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
        System.out.println("Animal constructor called");
    }
}

class Dog extends Animal {
    String breed;

    public Dog(String name, String breed) {
        super(name); // Calls the Animal class constructor
        this.breed = breed;
        System.out.println("Dog constructor called");
    }

    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy", "Golden Retriever");
        System.out.println("Name: " + myDog.name);
        System.out.println("Breed: " + myDog.breed);
    }
}