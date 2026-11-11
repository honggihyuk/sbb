package com.mysite.sbb.book.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    @NotEmpty(message = "리뷰 내용을 입력하세요.")
    private String content;

    @NotNull(message = "평점을 선택하세요.")
    @Min(value = 1, message = "평점은 1점 이상입니다.")
    @Max(value = 5, message = "평점은 5점 이하입니다.")
    private Integer rating;
}
