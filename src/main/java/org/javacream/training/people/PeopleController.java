package org.javacream.training.people;

import org.javacream.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PeopleController {

    @Autowired
    private Helper helper;
    public PeopleController(){
        System.out.println("constructing " + this);
    }

    @PostConstruct
    public void init(){
        System.out.println("initializing " + this);
        helper.dump(this);
    }
}
