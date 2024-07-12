package roomescape.exception;


import org.springframework.http.HttpStatus;

public enum ErrorCode {

    //404 NOT_FOUND
    RESERVATION_NOT_FOUND(HttpStatus.NO_CONTENT, "존재하지 않는 예약입니다.");
    private final HttpStatus httpStatus;
    private final String message;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }
}
