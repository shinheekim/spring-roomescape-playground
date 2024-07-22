package roomescape.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dto.TimeReqDto;
import roomescape.dto.TimeResDto;
import roomescape.dao.TimeDao;

import java.util.List;

@Service
@Transactional
public class TimeService {
    private final TimeDao timeDao;

    public TimeService(TimeDao timeDao) {
        this.timeDao = timeDao;
    }

    public TimeResDto createTime(TimeReqDto reqDto) {
        return timeDao.createTime(reqDto.getTime());
    }

    public List<TimeResDto> findAllTimes() {
        return timeDao.findAllTimes();
    }

    public void deleteTime(Long id) {
        timeDao.deleteTime(id);
    }

    public TimeResDto findById(Long id) {
        return timeDao.findById(id);
    }
}
