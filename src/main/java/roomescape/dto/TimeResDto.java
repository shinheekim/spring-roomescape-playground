package roomescape.dto;

public class TimeResDto {
    private Long id;
    private String time;

    public TimeResDto(Long id, String time) {
        this.id = id;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}
