package roomescape.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.ReservationReqDto;
import roomescape.model.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/reservations")
public class RoomescapeApiController {
    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(0);
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
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationReqDto reqDto) {
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
