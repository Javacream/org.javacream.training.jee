package org.javacream.training.people;

public class Person {//implicit: extends Object
    public String getLastname() {
        return lastname;
    }

    public Person(String lastname, String firstname, Integer height) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", height=" + height +
                '}';
    }

    @Override
    public int hashCode(){
        return 42;
    }
    @Override
    public boolean equals(Object o){
        return true;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    private String lastname;
    private String firstname;
    private Integer height;


    public String sayHello(){
        String greeting = "Hello, my name is ";
        String exclamation = "!!";
        String helloMessage = greeting + this.firstname + " " + this.lastname + exclamation;
        return helloMessage;
    }
}
