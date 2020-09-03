package dao;

import java.util.List;

import model.TbDept;
import model.TbRole;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class RoleDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public static TbRole findRoleById(int roleId) {
        String sql = "select * from tb_role where id=?";
        TbRole role = template.queryForObject(sql, new BeanPropertyRowMapper<TbRole>(TbRole.class),roleId);
        return role;

    }

	public static List<TbRole> findAllRole() {
		try {
            String sql = "select * from tb_role";
            List<TbRole> tbRole = template.query(sql,new BeanPropertyRowMapper<TbRole>(TbRole.class));
            return tbRole;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
}
