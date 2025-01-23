package com.example.autowired.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Manager {

    @Autowired
    private Employee employee;


    // @Autowired
    // public Manager(Employee employee){
    //     this.employee = employee;
    // }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Manager{");
        sb.append("employee=").append(employee);
        sb.append('}');
        return sb.toString();
    }
    
}
