package com.blog.testproject.blog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value = "SELECT * from blog limit ?1 offset ?2", nativeQuery = true)
    public List<Blog> getBlogs(int limit, int offset);

    @Query(value = "SELECT * from blog",
            countQuery = "SELECT count(*) FROM blog",
            nativeQuery = true)
    Page<Blog> findByLastname(Pageable pageable);
}
