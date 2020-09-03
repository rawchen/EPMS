package dao;

import model.TbPerson;
import model.TbTimecard;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class TimecardDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public static void addTimecard(TbTimecard tc) {
		try {
            String sql = "insert into tb_timecard(timecard_id,person_id,timecard_date,ratifier_id,explains) values(?,?,?,?,?)";
            template.update(sql,tc.getTimecardId(),tc.getPersonId(),tc.getTimecardDate(),tc.getRatifierId(),tc.getExplains());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
		
	}

	public static int findTimecardCountByPersonId(int tcid,String recordNumber) {
		try {
			String sql = "select count(*) from tb_timecard where timecard_id = ? and person_id = ?";
			int a = template.queryForObject(sql, Integer.class,tcid,recordNumber);
			return a;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
