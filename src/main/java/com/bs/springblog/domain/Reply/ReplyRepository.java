package com.bs.springblog.domain.Reply;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long> {

    @Query("SELECT r from Reply r join fetch r.post join fetch r.member")
    List<Reply> findAll();

    @Query("SELECT r from Reply r join fetch r.post join fetch r.member where r.post.id =:id ")
    List<Reply> findByPostId(Long id);
}
