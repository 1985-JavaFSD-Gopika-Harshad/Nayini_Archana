package com.demo.controller;

import com.demo.dto.BlogPostRequest;
import com.demo.model.BlogPost;
import com.demo.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogposts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @PostMapping
    public BlogPost createBlogPost(@RequestBody BlogPostRequest blogPostRequest) {
        return blogPostService.createBlogPost(blogPostRequest);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable Long id, @RequestBody BlogPostRequest blogPostRequest) {
        return blogPostService.updateBlogPost(id, blogPostRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
    }

    @GetMapping("/search")
    public List<BlogPost> searchBlogPosts(@RequestParam String keyword) {
        return blogPostService.searchPosts(keyword);
    }
}