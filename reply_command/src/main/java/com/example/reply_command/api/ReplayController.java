package com.example.reply_command.api;

import com.example.board_core.common.model.BaseResponse;
import com.example.reply_command.api.model.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reply")
public class ReplayController {
    private final ReplyService replyService;

    @PostMapping("/reg/{boardIdx}")
    public ResponseEntity reg(
            @RequestHeader(name="X-User-Idx") Long userIdx,
            @RequestHeader(name="X-User-Name") String userName,
            @PathVariable Long boardIdx,
            @RequestBody ReplyDto.RegReq dto) {
        ReplyDto.RegRes result = replyService.reg(boardIdx, userIdx, userName, dto);

        return ResponseEntity.ok(BaseResponse.success(result));
    }
}
