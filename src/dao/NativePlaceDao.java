package dao;

import java.util.List;

import model.TbDuty;
import model.TbNativePlace;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class NativePlaceDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public static TbNativePlace findNativePlaceById(int nativeId) {
        String sql = "select * from tb_native_place where id=?";
        TbNativePlace nativePlace = template.queryForObject(sql, new BeanPropertyRowMapper<TbNativePlace>(TbNativePlace.class),nativeId);
        return nativePlace;

    }

	public static List<TbNativePlace> findAllNativePlace() {
		try {
            String sql = "select * from tb_native_place";
            List<TbNativePlace> tbNativePlace = template.query(sql,new BeanPropertyRowMapper<TbNativePlace>(TbNativePlace.class));
            return tbNativePlace;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
}
