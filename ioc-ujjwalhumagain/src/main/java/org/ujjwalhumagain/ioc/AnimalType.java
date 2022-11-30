package org.ujjwalhumagain.ioc;

@MyBean
public class AnimalType {
    AnimalType(){
        System.out.println("Initialization of AnimalType class");
    }
    public void display(){
        System.out.println("Displaying from AnimalType class");
    }
}
