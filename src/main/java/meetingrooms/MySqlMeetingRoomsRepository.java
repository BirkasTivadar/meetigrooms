package meetingrooms;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlMeetingRoomsRepository implements MeetingRoomsRepository {

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
    public void printNames() {

    }

    @Override
    public void printNamesReverse() {

    }

    @Override
    public void printEvenNames() {

    }

    @Override
    public void printAreas() {

    }

    @Override
    public void printMeetingRoomsWithName(String name) {

    }

    @Override
    public void printMeetingRoomsContains(String part) {

    }

    @Override
    public void printAreasLargerThan(int area) {

    }
}
