package com.example.RestsoapClient.AsyncConfig;


import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;



@org.springframework.stereotype.Service
public class Service {
    Logger logger =  LoggerFactory.getLogger(Service.class);
    private  static List<UserEx> users = new ArrayList<>();
    @Autowired
    private final RestTemplate restTemplate;

    public Service(RestTemplateBuilder restTemplateBuilder) {

        this.restTemplate = restTemplateBuilder.build();
    }
    @Async
    public CompletableFuture<UserEx> serach(String uName) throws InterruptedException{


        String url = String.format("https://api.github.com/users/%s", uName);
        UserEx result = restTemplate.getForObject(url, UserEx.class);
        // save it into list ==> convert to DB
        logger.info("the id "+ result.getId() +"is for "+uName+" and the thread is "+
               Thread.currentThread().getName());
        users.add(result);
        Thread.sleep(1000);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<List<UserEx>> extract(){
           //extract from list ==> convert to DB
       logger.info("the extraction done by "+Thread.currentThread().getName());
        return CompletableFuture.completedFuture(users);


    }
}
