package com.example.RestsoapClient;



import com.example.RestsoapClient.AsyncConfig.Service;
import com.example.RestsoapClient.AsyncConfig.UserEx;
import com.example.RestsoapClient.sopaClient.SoapClient;
import com.example.RestsoapClient.user.GetUserRequest;
import com.example.RestsoapClient.user.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
public class Controller {

    @Autowired
    private SoapClient client;
    @Autowired
    private final Service asyncService;

    public Controller(SoapClient client, Service asyncService) {
        this.client = client;
        this.asyncService = asyncService;
    }

    @PostMapping("/users")
    public GetUserResponse get(@RequestBody GetUserRequest request){
        return client.getUser(request);
    }

    @GetMapping
    @RequestMapping("/search/{name}")
    public CompletableFuture<UserEx> searchU(@PathVariable("name")String name)
            throws InterruptedException {
        return asyncService.serach(name);
    }
    @GetMapping
    @RequestMapping("/allUsers")
    public CompletableFuture<List<UserEx>> getAll(){
        return asyncService.extract();
    }

    @GetMapping("/multi")
    public void multiTest()  {
        CompletableFuture<List<UserEx>> u1 = asyncService.extract();
        CompletableFuture<List<UserEx>> u2 = asyncService.extract();
        CompletableFuture<List<UserEx>> u3 = asyncService.extract();
        CompletableFuture<List<UserEx>> u4 = asyncService.extract();
        CompletableFuture.allOf(u1,u2,u3,u4).join();
    }

}
