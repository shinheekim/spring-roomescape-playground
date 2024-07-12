package roomescape.dto;

import roomescape.domain.Reservation;

public record ReservationListResDto (
        Long id,
        String name,
        String date,
        String time
){
    public static ReservationListResDto from(Reservation reservation) {
        return new ReservationListResDto(
                reservation.getId(),
                reservation.getName(),
                reservation.getDate(),
                reservation.getTime()
        );
    }
}
