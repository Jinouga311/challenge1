package com.example.apichallenge1.controller;

import com.example.apichallenge1.entity.WikiEntity;
import com.example.apichallenge1.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("api/wiki")
@Controller
public class wikiController {

    @Autowired
    WikiService wikiService;

    @GetMapping("/all/bypage")
    Page<WikiEntity> getWikis(@RequestParam int pageSize, @RequestParam int pageNumber){
        return wikiService.findAll(pageNumber,pageSize);
    }

    @GetMapping("/all/search/{title}")
    Page<WikiEntity> getWikisbySearch(@RequestParam int pageSize,  @RequestParam  int pageNumber, @PathVariable(value="title") String title){
        return  wikiService.findByTitle(title, pageNumber, pageSize);
    }


    }



