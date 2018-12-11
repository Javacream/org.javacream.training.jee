package org.javacream.training.people;

public class Worker extends Person {
    public Worker(String lastname, String firstname, Integer height, String company) {
        super(lastname, firstname, height);
        this.company = company;
    }

    private String company;
    public void work(){
        System.out.println("working...");
    }

    @Override
    public String sayHello() {
        String personGreeting =  super.sayHello();
        personGreeting += ", working at company " + this.company;
        return personGreeting;
    }

}
