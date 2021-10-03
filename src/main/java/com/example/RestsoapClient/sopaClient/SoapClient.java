package com.example.RestsoapClient.sopaClient;

import com.example.RestsoapClient.user.GetUserRequest;
import com.example.RestsoapClient.user.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class SoapClient extends WebServiceGatewaySupport {


    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    private WebServiceTemplate template;


    public GetUserResponse getUser(GetUserRequest request){
        template = new WebServiceTemplate(jaxb2Marshaller);
        GetUserResponse user =
                (GetUserResponse) template.marshalSendAndReceive("http://localhost:8080/soapWS",request);
        return user;

    }

}
