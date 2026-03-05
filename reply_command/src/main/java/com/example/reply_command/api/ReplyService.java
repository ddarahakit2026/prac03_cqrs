package com.example.reply_command.api;

import com.example.reply_command.api.model.Reply;
import com.example.reply_command.api.model.ReplyDto;
import com.example.reply_command.api.model.ReplyEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReplyService {
    private final KafkaTemplate<Long, ReplyEvent.Create> kafkaTemplate;
    private final ReplyRepository replyRepository;

    public ReplyDto.RegRes reg(Long boardIdx, Long userIdx, String userName, ReplyDto.RegReq dto) {

        Reply entity = replyRepository.save(dto.toEntity(userIdx, userName, boardIdx));


        ReplyEvent.Create event = ReplyEvent.Create.from(entity);
        log.debug("ReplyService - reg : {}", event.toString());
        kafkaTemplate.send("reply", boardIdx, event);

        return ReplyDto.RegRes.from(entity);
    }
}
