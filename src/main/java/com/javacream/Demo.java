package com.javacream;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Demo {
@PostConstruct public void init(){
    System.out.println("constructing "+ this);
}
}
