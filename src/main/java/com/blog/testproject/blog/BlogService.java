package com.blog.testproject.blog;

import com.blog.testproject.blog.Blog;
import com.blog.testproject.blog.BlogRepository;
import com.blog.testproject.comments.Comment;
import com.blog.testproject.comments.CommentRepository;
import com.blog.testproject.comments.CommentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private BlogRepository blogRepository;
    private CommentRepository commentRepository;
    private CommentService commentService = new CommentService();

    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public String createOrUpdateBlog(Blog blog) {
        try {
            blog.setDeleted(false);
            blog = blogRepository.save(blog);
            return "{\"id\": "+blog.getId()+", \"status\":\"success\",\"code\":\"200\",\"message\":\"\"}";
        } catch(Exception ex) {
            return "{\"id\": "+blog.getId()+", \"status\":\"failed\",\"code\":\"500\",\"message\":\""+ex.getMessage()+"\"}";
        }
    }

    public String deleteBlog(Long id) {
        try {
            blogRepository.deleteById(id);
            return "{\"status\":\"success\",\"code\":\"200\",\"message\":\"\"}";
        } catch (Exception ex) {
            return "{ \"status\":\"success\",\"code\":\"200\",\"message\":\""+ex.getMessage()+"\"}";
        }
    }

    public String getBlogs(int limit, int offset) {
        List<Blog> blogList = blogRepository.getBlogs(limit, offset);
        System.out.println(Arrays.toString(blogList.toArray()));
        return Arrays.toString(blogList.toArray());
    }

    public String getBlogFromId(Long id) {
        JSONObject jsonObject = new JSONObject();
        Optional<Blog> blog = blogRepository.findById(id);

        jsonObject.put("status", "success");
        jsonObject.put("code", "200");
        jsonObject.put("data", blog.isPresent() ? blog.get() : "");
        jsonObject.put("message", "");

        return jsonObject.toString();
    }

    public String getBlogswithPageable(int offset, int limit) {
        JSONObject jsonObject = new JSONObject();
        Pageable pageable = PageRequest.of( offset, limit == 0 ? 10 : limit);
        List<Blog> blogs = null;

        try {
            Page<Blog> blogList = blogRepository.findAll(pageable);
            blogs = blogList.getContent();
            jsonObject.put("status", "success");
            jsonObject.put("code", "200");
            jsonObject.put("data", blogs.toArray());
            jsonObject.put("message", "");
        } catch (Exception ex){
            jsonObject.put("status", "failed");
            jsonObject.put("code", "500");
            jsonObject.put("data", "");
            jsonObject.put("message", ex.getMessage());
        }

        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }

    @Transactional
    public Blog getBlogFromIdNative(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if(blog.isPresent()) {
            Blog bblog = blog.get();
            List<Comment> comments = bblog.getComments();
            bblog.setCommentscount(comments.size());
            return blog.get();
        }
        else return new Blog();
    }
}
