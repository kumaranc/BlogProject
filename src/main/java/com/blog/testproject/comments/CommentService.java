package com.blog.testproject.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public String saveCommentForBlog(Comment comment) {
        comment = commentRepository.save(comment);
        return comment.getComments();
    }

    public long getCommentsCount(long blogId) {
        long getCommentCount = 5;
        return getCommentCount;
    }
}
