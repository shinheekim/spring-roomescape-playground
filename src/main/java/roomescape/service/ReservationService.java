package roomescape.service;

import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationListResDto;
import roomescape.dto.ReservationReqDto;
import roomescape.exception.ErrorCode;
import roomescape.exception.NotFoundReservationException;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDao reservationDao;

    public ReservationService(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public List<ReservationListResDto> findAllReservations(){
        List<Reservation> reservations = reservationDao.findAll();
        return reservations.stream()
                .map(ReservationListResDto::from)
                .toList();
    }

    public Reservation createReservation(ReservationReqDto reservationReqDto) {
        Reservation reservation = new Reservation(null, reservationReqDto.name(), reservationReqDto.date(), reservationReqDto.time());
        Long id = reservationDao.insertWithKeyHolder(reservation);
        return new Reservation(id, reservationReqDto.name(), reservationReqDto.date(), reservationReqDto.time());
    }

    public void deleteReservation(Long id) {
        Reservation reservation = reservationDao.findById(id);
        if (reservation != null) {
            reservationDao.delete(id);
        }
        throw new NotFoundReservationException(ErrorCode.RESERVATION_NOT_FOUND);
    }

}
