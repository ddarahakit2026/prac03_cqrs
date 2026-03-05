package com.example.board_command.api;

import com.example.board_command.api.model.Board;
import com.example.board_command.api.model.BoardDto;
import com.example.board_command.api.model.BoardEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final KafkaTemplate<Long, BoardEvent.Create> kafkaTemplate;

    public BoardDto.RegRes register(Long userIdx, String userName, BoardDto.RegReq dto) {
        Board entity = boardRepository.save(dto.toEntity(userIdx, userName));

        // 카프카에 게시글이 생성됐다는 메시지(게시글 내용)를 전송
        kafkaTemplate.send("board", entity.getIdx(), BoardEvent.Create.from(entity));

        return BoardDto.RegRes.from(entity);
    }

    public BoardDto.RegRes update(Long idx, BoardDto.RegReq dto) {
        Board board = boardRepository.findById(idx).orElseThrow();
        board.update(dto);

        boardRepository.save(board);

        return BoardDto.RegRes.from(board);
    }

    public void delete(Long idx) {
        boardRepository.deleteById(idx);
    }
}
