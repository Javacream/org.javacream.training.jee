package org.javacream.training.people;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonTests {

    private Person p;

    @Before
    public void init(){
        //Testdaten definieren
        final String lastname = "Sawitzki";
        final String firstname = "Rainer";
        final Integer height = 183;
        //Erzeugen des Testobjekts
        p = new Person("Sawitzki", "Rainer", 183);
    }
    @Test
    public void sayHelloTest(){
        //Expected Result
        final String expectedResult = "Hello, my name is Rainer Sawitzki!!";

        //Aufruf der zu testenden Funktionalität
        String result = p.sayHello();

        //Prüfen der Assertion
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void toStringTest(){
        //Expected Result
        final String expectedResult = "Person{lastname='Sawitzki', firstname='Rainer', height=183}";

        String result = p.toString();
        Assert.assertEquals(expectedResult, result);
        System.out.println(p.toString());
    }
}
