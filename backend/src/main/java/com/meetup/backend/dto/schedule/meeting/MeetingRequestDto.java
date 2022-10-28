package com.meetup.backend.dto.schedule.meeting;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * created by myeongseok on 2022/10/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRequestDto {
    @NotBlank(message = "미팅의 시작 시간은 필수입니다.")
    private String start;

    @NotBlank(message = "미팅의 종료 시간은 필수입니다.")
    private String end;

    @NotBlank(message = "미팅의 제목은 필수입니다.")
    private String title;

    private String content;

    @NotBlank(message = "미팅의 managerId는 필수 입니다.")
    private String managerId;

    @NotBlank(message = "미팅의 meetupId는 필수 입니다.")
    private Long meetupId;


}