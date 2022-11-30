package org.ujjwalhumagain;

import org.ujjwalhumagain.ioc.*;

public class Main {
    public static void main(String[] args) {
        MyInjector myInjector = new MyInjector();
        AnimalType animalType = myInjector.getBean(AnimalType.class);
        animalType.display();
        Animal animal = myInjector.getBean(Animal.class);
        animal.display();
    }
}