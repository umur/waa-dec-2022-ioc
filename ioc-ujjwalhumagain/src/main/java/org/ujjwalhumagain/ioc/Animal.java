package org.ujjwalhumagain.ioc;

@MyBean
public class Animal {
    @MyAutowired
    public AnimalType animalType;

    Animal(){
        System.out.println("Initialization of Animal Class");
    }
    public void display(){
        System.out.println("Displaying from Animal Class");
    }
}
