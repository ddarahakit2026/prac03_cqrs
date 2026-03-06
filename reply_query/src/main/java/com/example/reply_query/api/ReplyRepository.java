package com.example.reply_query.api;

import com.example.reply_query.api.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

//                                                     엔티티 클래스, 엔티티 클래스의 @Id 변수의 타입
public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
