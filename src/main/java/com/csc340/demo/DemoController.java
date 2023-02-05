package com.csc340.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.logging.Logger;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController 

public class DemoController {

	public static void main(String[] args) {
		SpringApplication.run(DemoController.class, args);
	}

	@GetMapping("/states")
	public Object states() {
		try {
            String url = "https://names.drycodes.com/10?nameOptions=states";
            
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonQuote = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonQuote);

            System.out.println("Here are some states of the USA:");
            for(int i=0; i<10; i++) {
                 String state = root.get(i).asText();
                 System.out.println(state);
            }
        
            return root;
        } catch (JsonProcessingException ex) {
			Logger.getLogger(DemoController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /univ";
        }
	}

}
