package com.example.reply_query.api;

import com.example.reply_query.api.model.Reply;
import com.example.reply_query.api.model.ReplyDto;
import com.example.reply_query.api.model.ReplyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReplyService {
    private final ReplyRepository replyRepository;
    
    @KafkaListener(topics = "reply", groupId = "reply-group-3",
            // 내가 받는 Dto의 타입이 어떤 타입인지 지정
            properties = "spring.json.value.default.type:com.example.reply_query.api.model.ReplyEvent.Create")
    public void consume(
            @Header(KafkaHeaders.RECEIVED_KEY) Long key,
            @Payload ReplyEvent.Create event
    ) {
        log.debug("MessageConsumer - consume : {}={}", key, event.toString());

        replyRepository.save(event.toEntity());
    }

    public ReplyDto.PageRes list(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        // 페이징 처리 ⭕, 페이지 번호가 필요하다 => Page 반환
        // 페이징 처리 ⭕, 페이지 번호가 필요없다. => Slice 반환
        Page<Reply> result = replyRepository.findAll(pageRequest);

        return ReplyDto.PageRes.from(result);
    }

    public ReplyDto.ReadRes read(Long idx) {
        Reply reply = replyRepository.findById(idx).orElseThrow();
        return ReplyDto.ReadRes.from(reply);
    }
}
