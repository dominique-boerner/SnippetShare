package com.snippetshare.api.repository;

import com.snippetshare.api.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

}
