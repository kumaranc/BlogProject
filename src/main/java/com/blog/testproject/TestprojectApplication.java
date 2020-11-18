package com.blog.testproject;

import com.blog.testproject.blog.Blog;
import com.blog.testproject.comments.Comment;
import com.blog.testproject.blog.BlogService;
import com.blog.testproject.comments.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestprojectApplication implements CommandLineRunner {

	@Autowired
	private BlogService blogService;

	@Autowired
	private CommentService commentService;

	public static void main(String[] args) {
		SpringApplication.run(TestprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Blog blog = new Blog();
//		blog.setBlogContent("it is for testing 2342135345");
//		String response = blogService.createOrUpdateBlog(blog);
//		System.out.println(response);
		blogService.getBlogswithPageable(0, 10);

//		blogService.deleteBlog(24L);

//        Blog blog = blogService.getBlogFromIdNative(1L);
//		System.out.println(blog);

//		Comment comment1 = new Comment();
//		comment1.setBlog(blog);
//		comment1.setComments("it is awesome");
//
//		Comment comment2 = new Comment();
//		comment2.setBlog(blog);
//		comment2.setComments("it is totally useless");
//
//		commentService.saveCommentForBlog(comment1);
//		commentService.saveCommentForBlog(comment2);
	}

}
