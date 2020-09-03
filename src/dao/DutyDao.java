package dao;

import java.util.List;

import model.TbDept;
import model.TbDuty;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class DutyDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public static TbDuty findDutyById(int DutyId) {
        String sql = "select * from tb_duty where id=?";
        TbDuty duty = template.queryForObject(sql, new BeanPropertyRowMapper<TbDuty>(TbDuty.class),DutyId);
        return duty;

    }

	public static List<TbDuty> findAllDuty() {
		try {
            String sql = "select * from tb_duty";
            List<TbDuty> tbDuty = template.query(sql,new BeanPropertyRowMapper<TbDuty>(TbDuty.class));
            return tbDuty;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
}