package dao;

import java.util.List;

import model.TbAccount;
import model.TbDept;
import model.TbPerson;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class AccountDao {
	private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	public static List<TbAccount> findAllAccount() {
		try {
	        String sql = "select * from tb_account";
	        List<TbAccount> a = template.query(sql,new BeanPropertyRowMapper<TbAccount>(TbAccount.class));
	        return a;
	    } catch (DataAccessException e) {
	        e.printStackTrace();
	        return null;
	    }	
	}
	public static String findAccountMoneyById(int i) {
		try {
            String sql = "select * from tb_account where timecard_id=?";
            TbAccount tbAccount = template.queryForObject(sql,new BeanPropertyRowMapper<TbAccount>(TbAccount.class),i);
            return tbAccount.getMoney();
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
}
