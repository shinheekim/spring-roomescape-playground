package roomescape.dto;

import jakarta.validation.constraints.NotNull;

import java.sql.Time;

public record ReservationReqDto(
        @NotNull(message = "이름을 작성해주세요")
        String name,
        @NotNull(message = "날짜를 작성해주세요")
        String date,
        @NotNull(message = "시간을 작성해주세요")
        Long timeId
){

}
