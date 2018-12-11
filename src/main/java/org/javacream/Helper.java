package org.javacream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Helper {

    public Helper(){
        System.out.println("constructing helper " + this);
    }
    public void dump(Object o){
        System.out.println("DUMPING: " + o.toString() + " using " + this);
    }


}
