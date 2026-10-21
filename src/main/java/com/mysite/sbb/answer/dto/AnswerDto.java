package com.mysite.sbb.answer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {

    @NotEmpty(message = "내용이 입력되지 않았습니다.")
    private String content;
}
