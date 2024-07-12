package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.ReservationListResDto;
import roomescape.dto.ReservationReqDto;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundReservationException;
import roomescape.domain.Reservation;
import roomescape.service.ReservationService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/reservations")
public class RoomescapeApiController {
    private final ReservationService reservationService;
    public RoomescapeApiController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationListResDto>> reservations() {
        List<ReservationListResDto> reservations = reservationService.findAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationReqDto reqDto) {
        Reservation reservation = reservationService.createReservation(reqDto);
        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, "/reservations/" + reservation.getId())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundReservationException e) {
            throw new NotFoundReservationException(ErrorCode.RESERVATION_NOT_FOUND, HttpStatus.NOT_FOUND);
            // return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);

        }
    }
}
