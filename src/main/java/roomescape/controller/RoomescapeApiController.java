package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.ReservationListResDto;
import roomescape.dto.ReservationReqDto;
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
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
