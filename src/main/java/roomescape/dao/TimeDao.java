package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.dto.TimeResDto;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class TimeDao {
    private final JdbcTemplate jdbcTemplate;

    public TimeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TimeResDto> timeRowMapper = (resultSet, rowNum) ->
            new TimeResDto(
                    resultSet.getLong("id"),
                    resultSet.getString("time")
            );

    public TimeResDto createTime(String time) {
        String sql = "INSERT INTO time (time) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, time);
            return ps;
        }, keyHolder);

        Long id = keyHolder.getKey().longValue();
        return new TimeResDto(id, time);
    }

    public List<TimeResDto> findAllTimes() {
        String sql = "SELECT id, time FROM time";
        return jdbcTemplate.query(sql, timeRowMapper);
    }

    public TimeResDto findById(Long id) {
        String sql = "SELECT id, time FROM time WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, timeRowMapper, id);
    }

    public void deleteTime(Long id) {
        String sql = "DELETE FROM time WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
