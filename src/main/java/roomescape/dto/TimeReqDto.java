package roomescape.dto;

import jakarta.validation.constraints.NotEmpty;

public class TimeReqDto {
    @NotEmpty(message = "시간을 선택해주세요")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
