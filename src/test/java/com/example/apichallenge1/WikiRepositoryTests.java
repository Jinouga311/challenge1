package com.example.apichallenge1;

import com.example.apichallenge1.entity.WikiEntity;
import com.example.apichallenge1.repository.WikiRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class WikiRepositoryTests {
    @Autowired
    private WikiRepository wikiRepository;

    @BeforeEach
    public void setup() {
        WikiEntity wiki1 = WikiEntity.builder().id(1).title("Title 1").description("Description 1").build();
        WikiEntity wiki2 = WikiEntity.builder().id(2).title("Title 2").description("Description 2").build();
        WikiEntity wiki3 = WikiEntity.builder().id(3).title("Another Title").description("Another description").build();
        wikiRepository.saveAll(Arrays.asList(wiki1, wiki2, wiki3));
    }

    @AfterEach
    public void tearDown() {
        wikiRepository.deleteAll();
    }

    @Test
    public void findByTitleStartingWithIgnoreCase_shouldReturnMatchingTitles() {

        String title = "title";
        Pageable pageable = PageRequest.of(0, 10);


        Page<WikiEntity> page = wikiRepository.findByTitleStartingWithIgnoreCase(pageable, title);


        List<WikiEntity> wikiList = page.getContent();
        assertEquals(2, wikiList.size());
        assertEquals("Title 1", wikiList.get(0).getTitle());
        assertEquals("Title 2", wikiList.get(1).getTitle());
    }

    @Test
    public void findByTitleStartingWithIgnoreCase_shouldNotReturnNonMatchingTitles() {

        String title = "another";
        Pageable pageable = PageRequest.of(0, 10);


        Page<WikiEntity> page = wikiRepository.findByTitleStartingWithIgnoreCase(pageable, title);


        List<WikiEntity> wikiList = page.getContent();
        assertEquals(1, wikiList.size());
        assertEquals("Another Title", wikiList.get(0).getTitle());
    }

    @Test
    public void findByTitleStartingWithIgnoreCase_shouldReturnEmptyPageWhenTitleNotFound() {

        String title = "invalid";
        Pageable pageable = PageRequest.of(0, 10);


        Page<WikiEntity> page = wikiRepository.findByTitleStartingWithIgnoreCase(pageable, title);


        List<WikiEntity> wikiList = page.getContent();
        assertTrue(wikiList.isEmpty());
    }

    @Test
    public void findAll_shouldReturnAllWikisInPage() {

        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(0, 10, sort);


        Page<WikiEntity> page = wikiRepository.findAll(pageable);


        List<WikiEntity> wikiList = page.getContent();
        assertEquals(3, wikiList.size());
        assertEquals("Title 1", wikiList.get(0).getTitle());
        assertEquals("Title 2", wikiList.get(1).getTitle());
        assertEquals("Another Title", wikiList.get(2).getTitle());
    }

    @Test
    public void findAll_shouldReturnEmptyPageWhenNoWikiFound() {

        wikiRepository.deleteAll();
        Pageable pageable = PageRequest.of(0, 10);


        Page<WikiEntity> page = wikiRepository.findAll(pageable);


        List<WikiEntity> wikiList = page.getContent();
        assertTrue(wikiList.isEmpty());
    }

}

