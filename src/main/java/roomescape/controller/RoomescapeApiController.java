package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.ReservationReqDto;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundReservationException;
import roomescape.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
@RequestMapping("/reservations")
public class RoomescapeApiController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Reservation>> reservations() {
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationReqDto reqDto) {
        Reservation reservation = new Reservation(
                index.incrementAndGet(),
                reqDto.name(),
                reqDto.date(),
                reqDto.time());
        reservations.add(reservation);
        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, "/reservations/" + reservation.getId())
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(reservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        boolean removed = reservations.removeIf(
                reservation -> reservation.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 200 -> 204 No Content
        }
        throw new NotFoundReservationException(ErrorCode.RESERVATION_NOT_FOUND, HttpStatus.NOT_FOUND);    // else삭제
        // return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
    }
}
