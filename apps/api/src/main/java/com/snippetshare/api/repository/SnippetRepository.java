package com.snippetshare.api.repository;

import com.snippetshare.api.entity.Snippet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SnippetRepository extends JpaRepository<Snippet, Long> {

    List<Snippet> searchSnippetsByTagsContainsIgnoreCaseOrTitleContainsIgnoreCase(String tags, String title);

}
