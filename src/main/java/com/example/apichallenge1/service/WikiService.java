package com.example.apichallenge1.service;


import com.example.apichallenge1.entity.WikiEntity;
import com.example.apichallenge1.repository.WikiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("api/wiki")
@Service
public class WikiService {

    @Autowired
    private WikiRepository wikiRepository;


    @GetMapping("/all")
    public Page<WikiEntity> findAll(int pageNumber, int pageSize){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return wikiRepository.findAll(page);
    }

    @GetMapping("/research/{title}")
    public Page<WikiEntity> findByTitle(String title, int pageNumber, int pageSize ){
        Pageable page = PageRequest.of(pageNumber, pageSize);
        return   wikiRepository.findByTitleStartingWithIgnoreCase(page, title);

    }

    public Page<WikiEntity> findWikiWithPagination(int pageSize, int offset){
        Page<WikiEntity> wikipages = wikiRepository.findAll(PageRequest.of(offset, pageSize));
        return wikipages;
    }

    public List<WikiEntity> findWikiWithSorting(String field){
        return wikiRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }



}
