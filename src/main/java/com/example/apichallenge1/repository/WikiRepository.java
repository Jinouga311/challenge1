package com.example.apichallenge1.repository;

import com.example.apichallenge1.entity.WikiEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WikiRepository extends JpaRepository<WikiEntity, Long> {

    List<WikiEntity> findAllByOrderByTitleAsc();

    Page<WikiEntity> findByTitleStartingWithIgnoreCase(Pageable pageable, String prefix);
}
