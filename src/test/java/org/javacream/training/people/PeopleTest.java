package org.javacream.training.people;

import org.junit.Test;

public class PeopleTest {

    @Test public void testPeople(){
        Person p = new Person("Sawitzki", "Rainer", 183);
        Student s = new Student("Einstein", "Albert", 168, "LMU");
        Worker w = new Worker("Schufter", "Hannes", 198, "Integrata");
//        System.out.println(p.sayHello());
//        System.out.println(s.sayHello());
//        System.out.println(w.sayHello());
        greet(p);
        greet(s);
        greet(w);
    }


    private void greet(Person p){
        System.out.println("Greeting: " + p.sayHello());
    }
}
