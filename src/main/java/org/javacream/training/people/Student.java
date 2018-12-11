package org.javacream.training.people;

public class Student extends Person {
    public Student(String lastname, String firstname, Integer height, String university) {
        super(lastname, firstname, height);
        this.university = university;
    }

    private String university;
    public void study(){
        System.out.println("studying...");
    }

    @Override
    public String sayHello() {
        String personGreeting =  super.sayHello();
        personGreeting += ", studying at university " + this.university;
        return personGreeting;
    }
}
