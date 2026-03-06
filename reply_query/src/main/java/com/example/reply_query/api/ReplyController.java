package com.example.reply_query.api;

import com.example.board_core.common.model.BaseResponse;
import com.example.reply_query.api.model.ReplyDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
@RequestMapping("/reply")
@RestController
@RequiredArgsConstructor
@Tag(name="게시판 기능")
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/list")
    public ResponseEntity list(
            @RequestParam(required = true, defaultValue = "0") int page,
            @RequestParam(required = true, defaultValue = "5") int size) {
        ReplyDto.PageRes dto = replyService.list(page, size);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

    @GetMapping("/read/{idx}")
    public ResponseEntity read(@PathVariable Long idx) {
        ReplyDto.ReadRes dto = replyService.read(idx);
        return ResponseEntity.ok(BaseResponse.success(dto));
    }

}

