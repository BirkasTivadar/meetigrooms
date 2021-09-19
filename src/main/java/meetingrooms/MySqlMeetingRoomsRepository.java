package meetingrooms;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

//import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class MySqlMeetingRoomsRepository implements MeetingRoomsRepository {

    public static final String ID = "id";
    public static final String NAME = "mr_name";
    public static final String WIDTH = "mr_width";
    public static final String LENGTH = "mr_length";

    private JdbcTemplate jdbcTemplate;

    public MySqlMeetingRoomsRepository() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.migrate();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(String name, int width, int length) {
        jdbcTemplate.update("INSERT INTO meetingrooms(mr_name, mr_width, mr_length) VALUES (?,?,?)", name, width, length);
    }

    @Override
    public List<String> getOrderedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM meetingrooms ORDER BY mr_name;",
                (rs, i) -> rs.getString(NAME));
    }

    @Override
    public List<String> getReversedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM meetingrooms ORDER BY mr_name DESC;",
                (rs, i) -> rs.getString(NAME));
    }

    @Override
    public List<String> getEvenOrderedNames() {
        return jdbcTemplate.query("SELECT mr_name FROM (SELECT mr_name, row_number() over (ORDER BY mr_name) as `rn` FROM `meetingrooms`) as `w_rownum` WHERE w_rownum.rn % 2 = 0;",
                (rs, i) -> rs.getString(NAME));
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc() {
        return jdbcTemplate.query("SELECT * FROM meetingrooms ORDER BY mr_width*mr_length DESC;",
                (rs, i) -> createMeetingRoom(rs.getString(NAME), rs.getInt(WIDTH), rs.getInt(LENGTH)));
//                (rs, i) -> new MeetingRoom(rs.getString(NAME), rs.getInt(WIDTH), rs.getInt(LENGTH)));
    }


    @Override
    public Optional<MeetingRoom> getMeetingRoomsWithName(String name) {
        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE mr_name= ?;",
                new Object[]{name}, (rs, i) -> createMeetingRoom(rs.getString(NAME), rs.getInt(WIDTH), rs.getInt(LENGTH))
        ).stream().findAny();
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsContains(String part) {
        String partSql = "%" + part + "%";

        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE mr_name LIKE ? ORDER BY mr_name;",
                new Object[]{partSql},  (rs, i) -> createMeetingRoom(rs.getString(NAME), rs.getInt(WIDTH), rs.getInt(LENGTH))
        );
    }

    @Override
    public List<MeetingRoom> getAreasLargerThan(int area) {
        return jdbcTemplate.query("SELECT * FROM meetingrooms WHERE mr_width*mr_length > ? ;",
                new Object[]{area},  (rs, i) -> createMeetingRoom(rs.getString(NAME), rs.getInt(WIDTH), rs.getInt(LENGTH))
        );
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from meetingrooms");
    }

    private MeetingRoom createMeetingRoom(String name, int width, int length) {
        return new MeetingRoom(name, width, length);
    }
}
