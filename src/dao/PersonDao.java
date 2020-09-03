package dao;

import java.util.List;

import model.TbPerson;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import tools.JDBCUtils;

public class PersonDao {
    private static JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public static TbPerson queryRecordByNum(String num) {
        try {
            String sql = "select * from tb_person where record_number=?";
            TbPerson tbRecord = template.queryForObject(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),num);
            return tbRecord;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static List<TbPerson> findAllPerson() {
        try {
            String sql = "select * from tb_person";
            List<TbPerson> tbPerson = template.query(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class));
            return tbPerson;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

	public static List<TbPerson> likePersonByRecordNumber(String recordNumber) {
		try {
            String sql = "select * from tb_person where record_number like '%' ? '%'";
            List<TbPerson> tbPerson = template.query(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),recordNumber);
            return tbPerson;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}

	public static List<TbPerson> findPersonByDeptId(int i) {
		try {
            String sql = "select * from tb_person where dept_id=?";
            List<TbPerson> tbPerson = template.query(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),i);
            return tbPerson;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	public static TbPerson findPersonByRecordNumber(String recordNumber) {
		try {
            String sql = "select * from tb_person where record_number=?";
            TbPerson tbPerson = template.queryForObject(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),recordNumber);
            return tbPerson;
        } catch (DataAccessException e) {
        	System.out.println("查找员工为空数据");
            e.printStackTrace();
            return null;
        }
	}

	public static void updatePerson(TbPerson person) {
		try {
            String sql = "update tb_person set dept_id=?,duty_id=?,name=?,sex=?,"
            		+ "birthday=?,photo=?,id_card=?,marriaged=?,native_place_id=?,"
            		+ "party_member=?,school_age=?,specialty=?,foreign_language=?,"
            		+ "grade=?,state=?,role_id=? where record_number=?";
            template.update(sql,person.getDeptId(),person.getDutyId(),person.getName(),
            		person.getSex(),person.getBirthday(),person.getPhoto(),person.getIdCard(),
            		person.getMarriaged(),person.getNativePlaceId(),person.getPartyMember(),
            		person.getSchoolAge(),person.getSpecialty(),person.getForeignLanguage(),
            		person.getGrade(),person.getState(),person.getRoleId(),person.getRecordNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public static void addPerson(TbPerson person) {
		try {
            String sql = "insert into tb_person(record_number,dept_id,duty_id,name,sex,"
            		+ "birthday,photo,id_card,marriaged,native_place_id,"
            		+ "party_member,school_age,specialty,foreign_language,"
            		+ "grade,state,role_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            template.update(sql,person.getRecordNumber(),person.getDeptId(),person.getDutyId(),
            		person.getName(),person.getSex(),person.getBirthday(),person.getPhoto(),
            		person.getIdCard(),person.getMarriaged(),person.getNativePlaceId(),
            		person.getPartyMember(),person.getSchoolAge(),person.getSpecialty(),
            		person.getForeignLanguage(),person.getGrade(),person.getState(),person.getRoleId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
		
	}

	public static void deletePersonByrecordNumber(String recordNumber) {
		try {
            String sql = "delete from tb_person where record_number=?";
            template.update(sql,recordNumber);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
	}

	public static TbPerson login(TbPerson person) {
		try {
            String sql = "select * from tb_person where record_number=? and password=?";
            TbPerson tbPerson = template.queryForObject(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),person.getRecordNumber(),person.getPassword());
            return tbPerson;
        } catch (DataAccessException e) {
        	System.out.println("空结果数据访问异常");
            e.printStackTrace();
            return null;
        }
	}

	public static void updatePersonPassword(TbPerson person) {
		try {
            String sql = "update tb_person set password = ? where record_number= ?";
            template.update(sql,person.getPassword(),person.getRecordNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public static List<TbPerson> likePersonByName(String name) {
        try {
            String sql = "select * from tb_person where name like '%' ? '%'";
            List<TbPerson> tbPerson = template.query(sql,new BeanPropertyRowMapper<TbPerson>(TbPerson.class),name);
            return tbPerson;
        } catch (DataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
