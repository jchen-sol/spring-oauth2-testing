package com.example.demo;

import net.minidev.json.JSONObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloWorldController {
    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String CLIENT_ID;
    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String CLIENT_SECRET;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/")
    public String getHome()
    {
        return "index.html";
    }

    @GetMapping("/helloworld")
    @ResponseBody
    public String getHelloWorld() {
        System.out.println("stop.");
        return "Hello world!";
    }

    @GetMapping("/receive-code")
    @ResponseBody
    public String getReceiveCode(@RequestParam String code)
    {
        String accessToken = "empty";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://accounts.google.com/o/oauth2/token");
        builder.build();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("client_id", CLIENT_ID);
        jsonObject.put("client_secret", CLIENT_SECRET);
        jsonObject.put("code", code);
        jsonObject.put("grant_type", "authorization_code");
        jsonObject.put("redirect_uri", "http://localhost:8080/receive-code");


        HttpEntity<AccessTokenResponse> response = restTemplate.postForEntity(builder.toUriString(), jsonObject, AccessTokenResponse.class);

        accessToken = response.getBody().getAccess_token();

        return "Code is: " + code + " Access token is: " + accessToken;
    }

    @GetMapping("/sheets")
    @ResponseBody
    public String getSheets()
    {
        return "Authenticated sheets...";
    }

}
