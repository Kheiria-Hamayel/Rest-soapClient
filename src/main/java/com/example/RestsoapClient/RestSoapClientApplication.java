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

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
public class RestSoapClientApplication {



	public static void main(String[] args) {
		SpringApplication.run(RestSoapClientApplication.class, args);
	}

}
