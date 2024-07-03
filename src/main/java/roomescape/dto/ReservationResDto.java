package roomescape.dto;

import roomescape.model.Reservation;

public record ReservationResDto(
        Long id,
        String name,
        String date,
        String time){}