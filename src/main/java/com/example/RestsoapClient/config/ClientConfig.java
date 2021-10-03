package com.example.RestsoapClient.config;

import com.example.RestsoapClient.sopaClient.SoapClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

@Configuration

public class ClientConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.example.RestsoapClient.user");
        return marshaller;
    }
    @Bean(name="client")
    public SoapClient soapClient (Jaxb2Marshaller marshaller){
        SoapClient client = new SoapClient();
        client.setDefaultUri("http://localhost:8080/soapWS");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
