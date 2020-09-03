package dao;

import java.util.List;

import model.TbDept;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class DeptDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public static TbDept findDeptById(int deptId) {
        String sql = "select * from tb_dept where id=?";
        TbDept dept = template.queryForObject(sql, new BeanPropertyRowMapper<TbDept>(TbDept.class),deptId);
        return dept;

    }

	public static List<TbDept> findAllDept() {
		try {
            String sql = "select * from tb_dept";
            List<TbDept> tbDept = template.query(sql,new BeanPropertyRowMapper<TbDept>(TbDept.class));
            return tbDept;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
  
	public static int findDeptIdByName(String item) {
		String sql = "select * from tb_dept where name=?";
        TbDept dept = template.queryForObject(sql, new BeanPropertyRowMapper<TbDept>(TbDept.class),item);
        return dept.getId();
	}

	public static List<TbDept> findAllDeptExceptZero() {
		try {
            String sql = "select * from tb_dept where id<>0";
            List<TbDept> tbDept = template.query(sql,new BeanPropertyRowMapper<TbDept>(TbDept.class));
            return tbDept;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}

	public static TbDept findDeptByName(String name) {
		try {
			String sql = "select * from tb_dept where name=?";
			TbDept dept = template.queryForObject(sql, new BeanPropertyRowMapper<TbDept>(TbDept.class),name);
			return dept;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void addDept(TbDept d2) {
		try {
			String sql = "insert into tb_dept(name) values (?)";
			template.update(sql,d2.getName());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
	}

	public static void deleteDeptById(int id) {
		try {
            String sql = "delete from tb_dept where id=?";
            template.update(sql,id);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
		
	}

	public static void updateDept(int id, String input) {
		try {
            String sql = "update tb_dept set name = ? where id = ?";
            template.update(sql,input,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
}
