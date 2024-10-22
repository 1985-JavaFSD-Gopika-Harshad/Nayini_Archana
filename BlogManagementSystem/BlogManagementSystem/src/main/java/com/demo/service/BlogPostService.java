package com.demo.service;

import com.demo.dto.BlogPostRequest;
import com.demo.model.BlogPost;
import com.demo.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogPostService {
    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public BlogPost createBlogPost(BlogPostRequest blogPostRequest) {
        BlogPost blogPost = BlogPost.builder()
                .title(blogPostRequest.getTitle())
                .content(blogPostRequest.getContent())
                .build();
        return blogPostRepository.save(blogPost);
    }

    public BlogPost updateBlogPost(Long id, BlogPostRequest blogPostRequest) {
        BlogPost blogPost = blogPostRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog post not found"));

        blogPost.setTitle(blogPostRequest.getTitle());
        blogPost.setContent(blogPostRequest.getContent());

        return blogPostRepository.save(blogPost);
    }

    public void deleteBlogPost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public List<BlogPost> searchPosts(String keyword) {
        return blogPostRepository.findByTitleContaining(keyword);
    }
}