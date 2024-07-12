package roomescape.exception;

public class NotFoundReservationException extends RuntimeException {
    private final ErrorCode errorCode;

    public NotFoundReservationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
