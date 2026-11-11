package com.mysite.sbb.book.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotEmpty(message = "도서 제목은 필수 항목입니다.")
    @Size(max = 100, message = "제목은 100자 이내로 입력하세요.")
    private String title;

    @NotEmpty(message = "저자는 필수 항목입니다.")
    private String author;

    @NotEmpty(message = "감상평은 필수 항목입니다.")
    @Size(min = 10, message = "감상평은 10자 이상 입력하세요.")
    private String content;


}
