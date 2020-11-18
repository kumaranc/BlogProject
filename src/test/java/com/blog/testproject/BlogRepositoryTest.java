package com.blog.testproject;

import com.blog.testproject.blog.Blog;

import com.blog.testproject.blog.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class BlogRepositoryTest {

    @Autowired
    private BlogService blogService;

    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Test
    @Transactional
    public void createBlog() {
        Blog blog = new Blog();
        blog.setBlogContent("it is for testing blog123");

        Comment comment = new Comment();
//        comment.setComments("for testing comment");
//        blog.getComments().add(comment);

        String response = blogService.createOrUpdateBlog(blog);
//        comment.setBlog(blog);
//        commentRepository.save(comment);

        System.out.println("Blog info => " + response);
    }

    @Test
    public void getBlogDetails() {
        blogService.getBlogs(10, 0);
    }

}
