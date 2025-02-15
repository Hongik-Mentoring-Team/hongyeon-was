package com.hongik.mentor.hongik_mentor.controller.swagger;

import com.hongik.mentor.hongik_mentor.controller.dto.ApiResponseEntity;
import com.hongik.mentor.hongik_mentor.controller.dto.CommentCreateDto;
import com.hongik.mentor.hongik_mentor.controller.dto.CreatedCommentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "댓글과 관련된 API", description = "댓글 생성/수정/삭제를 수행하는 API")
public interface CommentControllerDocs {

    @Operation(summary = "게시글에 댓글을 작성하는 API")
    @ApiResponse(responseCode = "201", description = "댓글 생성 성공 시 응답", useReturnTypeSchema = true,
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiResponseEntity.class)))
    ResponseEntity<ApiResponseEntity<CreatedCommentDto>> createComment(@RequestBody CommentCreateDto request);
}
