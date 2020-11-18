package com.blog.testproject.blog;

import com.blog.testproject.comments.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "blog")
@SQLDelete(sql = "update blog set is_deleted=true where id=?")
@Where(clause = "is_deleted = false")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String blogContent;
    private long commentscount;
    @CreationTimestamp
    private Timestamp created_at;
    @UpdateTimestamp
    private Timestamp updated_at;
    private boolean isDeleted;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public long getCommentscount() {
        return commentscount;
    }

    public void setCommentscount(long commentscount) {
        this.commentscount = commentscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    @JsonManagedReference
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogContent='" + blogContent + '\'' +
                ", commentscount=" + commentscount +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", isDeleted=" + isDeleted +
                ", comments=" + comments +
                '}';
    }

    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", this.id);
        jsonObject.put("blogcontent", this.blogContent);
        jsonObject.put("commentscount", this.commentscount);
        jsonObject.put("comments", this.comments);

        return jsonObject.toString();
    }
}
