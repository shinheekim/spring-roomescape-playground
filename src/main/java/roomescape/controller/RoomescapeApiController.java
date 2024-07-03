package roomescape.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RoomescapeApiController {
    private List<Reservation> reservations = new ArrayList<>();
    public RoomescapeApiController(){
        reservations.add(new Reservation(1L,"브라운", "2023-01-01","10:00"));
        reservations.add(new Reservation(2L,"브라운", "2023-01-02","11:00"));
        reservations.add(new Reservation(3L,"브라운", "2023-01-03","12:00"));
    }

    @GetMapping("/reservations")
    @ResponseBody
    public ResponseEntity<List<Reservation>> reservations() {
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
