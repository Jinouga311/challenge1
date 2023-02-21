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



    @GetMapping("/all/{field}")
    private APIResponse<List<WikiEntity>> getWiki(@PathVariable String field){
        List<WikiEntity> allWiki = wikiController.findWikiWithSorting(field);
        return new APIResponse<>(allWiki.size(), allWiki);

    }

    @GetMapping("/all/pagination/{pageSize}/{offset}")
    private APIResponse<Page<WikiEntity>> getWiki(@PathVariable int pageSize, @PathVariable int offset){
        Page<WikiEntity> allWikiWithPagination = wikiController.findWikiWithPagination(pageSize, offset);
        return new APIResponse<>(allWikiWithPagination.getSize(), allWikiWithPagination);

    }


    public static void main(String[] args) {
        SpringApplication.run(ApiChallenge1Application.class, args);
    }

}
