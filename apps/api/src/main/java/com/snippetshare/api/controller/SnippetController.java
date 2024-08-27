package com.snippetshare.api.controller;

import com.snippetshare.api.dto.CreateSnippetDto;
import com.snippetshare.api.entity.Snippet;
import com.snippetshare.api.repository.SnippetRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SnippetController {

    private final SnippetRepository snippetRepository;

    public SnippetController(
            SnippetRepository snippetRepository
    ) {
        this.snippetRepository = snippetRepository;
    }

    @PostMapping("/snippet")
    public ResponseEntity<Snippet> create(
            @RequestBody CreateSnippetDto createSnippetDto
    ) {
        try {
            var snippet = new Snippet();
            snippet.setContent(createSnippetDto.getContent());
            var createdSnippet = snippetRepository.save(snippet);
            return new ResponseEntity<>(createdSnippet, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
