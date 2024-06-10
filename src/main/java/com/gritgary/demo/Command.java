package com.gritgary.demo;

import org.springframework.http.ResponseEntity;

public interface Command <E,T>{
    //E is Entity
    //T is Generic in Java

    ResponseEntity<T> execute(E entity);

}
