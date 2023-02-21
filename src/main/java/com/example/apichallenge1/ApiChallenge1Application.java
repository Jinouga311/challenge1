package com.example.apichallenge1;

import com.example.apichallenge1.service.WikiService;
import com.example.apichallenge1.dto.APIResponse;
import com.example.apichallenge1.entity.WikiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SpringBootApplication
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/wiki")
public class ApiChallenge1Application  {

    @Autowired
    WikiService wikiController;








    public static void main(String[] args) {
        SpringApplication.run(ApiChallenge1Application.class, args);
    }

}
