package roomescape.controller;

import jakarta.validation.Valid;
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

@Controller
@RequestMapping("/reservations")
public class RoomescapeApiController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);

    // 조회 Test(임시 데이터 입력)
    public RoomescapeApiController(){
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", "2023-01-01","10:00"));
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", "2023-01-02","11:00"));
        reservations.add(new Reservation(index.incrementAndGet(), "브라운", "2023-01-03","12:00"));
    }

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
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long id) {
        boolean removed = reservations.removeIf(
                reservation -> reservation.getId().equals(id));
        if (removed) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new NotFoundReservationException(ErrorCode.RESERVATION_NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }
}
