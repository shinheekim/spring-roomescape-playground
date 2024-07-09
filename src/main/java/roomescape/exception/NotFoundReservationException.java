package roomescape.exception;

import org.springframework.http.HttpStatus;

public class NotFoundReservationException extends RuntimeException {
    private final HttpStatus httpStatus;

    public NotFoundReservationException(ErrorCode message, HttpStatus httpStatus){
        super(message.getMessage());
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
