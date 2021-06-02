package employees;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MySqlEmployeesRepository implements EmployeesRepository {

    private JdbcTemplate jdbcTemplate;

    public MySqlEmployeesRepository() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/employees?useUnicode=true");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();

        flyway.migrate();

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(String name) {
        jdbcTemplate.update("insert into employees(emp_name) values(?)", name);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees order by emp_name",
                (rs, i) -> new Employee(rs.getLong("id"), rs.getString("emp_name")));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from employees");
    }
}
