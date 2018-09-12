package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreDao extends BaseDao {

	public List<Score> search() {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = " SELECT e.id as e_id, e.name AS e_name, d.id AS d_id,d.name AS d_name ,  p.id AS p_id,p.name AS p_name,s.id AS s_id,value ,grade "
					+ "FROM employee AS e  " + "LEFT JOIN department AS d  ON e.d_id=d.id  "
					+ "LEFT JOIN r_dep_pro AS r  ON r.d_id=d.id  " + "LEFT JOIN project AS p  ON r.p_id=p.id  "
					+ "LEFT JOIN score AS s  ON s.e_id=e.id AND p.id=s.p_id  ";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Score sc = new Score();
				sc.setId(rs.getInt("s_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				emp.setDep(dep);
				sc.setEmp(emp);
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));

				list.add(sc);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	
	
	
	public List<Score> search(Score condition, int begin, int size) {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getEmp().getName() != null &&!condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getEmp().getDep().getName()!= null &&!condition.getEmp().getDep().getName().equals("")) {
				where += " and d.name='" + condition.getEmp().getDep().getName() + "'";
			}
			if (condition.getPro().getName()!= null && !condition.getPro().getName().equals("")) {
				where += " and p.name='" + condition.getPro().getName() + "'";
			}
			if (condition.getValue()!= null &&condition.getValue() != -1) {
				where += " and value= " + condition.getValue();
			}
			String sql = " SELECT e.id as e_id, e.name AS e_name, d.id AS d_id,d.name AS d_name ,  p.id AS p_id,p.name AS p_name,s.id AS s_id,value ,grade "
					+ "FROM employee AS e  " + "LEFT JOIN department AS d  ON e.d_id=d.id  "
					+ "LEFT JOIN r_dep_pro AS r  ON r.d_id=d.id  " + "LEFT JOIN project AS p  ON r.p_id=p.id  "
					+ "LEFT JOIN score AS s  ON s.e_id=e.id AND p.id=s.p_id  "+ where + " limit " + begin + "," + size;
					rs = stat.executeQuery(sql);

			while (rs.next()) {

				Score sc = new Score();
				sc.setId(rs.getInt("s_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				emp.setDep(dep);
				sc.setEmp(emp);
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));

				list.add(sc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}
	

	
	public Score search( int id) {
		Score sc = new Score();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			
			
			String sql = " SELECT * from  v_emp_sc where sc_id="+id ;
					rs = stat.executeQuery(sql);

			while (rs.next()) {


				sc.setId(rs.getInt("sc_id"));

				Employee emp = new Employee();
				emp.setId(rs.getInt("e_id"));
				emp.setName(rs.getString("e_name"));

				Department dep = new Department();
				dep.setId(rs.getInt("d_id"));
				dep.setName(rs.getString("d_name"));

				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				emp.setDep(dep);
				sc.setEmp(emp);
				sc.setPro(pro);
				sc.setValue((Integer) rs.getObject("value"));
				sc.setGrade(rs.getString("grade"));

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return sc;
	}
	
	
	public int searchCount() {
		List<Employee> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = " select count(*)"
					+ "FROM employee AS e  " + "LEFT JOIN department AS d  ON e.d_id=d.id  "
					+ "LEFT JOIN r_dep_pro AS r  ON r.d_id=d.id  " + "LEFT JOIN project AS p  ON r.p_id=p.id  "
					+ "LEFT JOIN score AS s  ON s.e_id=e.id AND p.id=s.p_id  ";
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

	
	public int searchCount(Score condition) {
		List<Score> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getEmp().getName() != null &&!condition.getEmp().getName().equals("")) {
				where += " and e.name='" + condition.getEmp().getName() + "'";
			}
			if (condition.getEmp().getDep().getName()!= null &&!condition.getEmp().getDep().getName().equals("")) {
				where += " and d.name='" + condition.getEmp().getDep().getName() + "'";
			}
			if (condition.getPro().getName()!= null && !condition.getPro().getName().equals("")) {
				where += " and p.name='" + condition.getPro().getName() + "'";
			}
			if (condition.getValue()!= null &&condition.getValue() != -1) {
				where += " and value= " + condition.getValue();
			}
			String sql = " select count(*)"
					+ "FROM employee AS e  " + "LEFT JOIN department AS d  ON e.d_id=d.id  "
					+ "LEFT JOIN r_dep_pro AS r  ON r.d_id=d.id  " + "LEFT JOIN project AS p  ON r.p_id=p.id  "
					+ "LEFT JOIN score AS s  ON s.e_id=e.id AND p.id=s.p_id  " + where ;
			
			rs = stat.executeQuery(sql);

			if (rs.next()) {
				count = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return count;
	}

//	public List<Score> searchCount(Score condition) {
//		List<Score> list = new ArrayList<>();
//		Connection conn = null;
//		Statement stat = null;
//		ResultSet rs = null;
//		try {
//			conn = getConnection();
//			stat = conn.createStatement();
//			String where = " where 1=1 ";
//			if (!condition.getEmp().getName().equals("")) {
//				where += " and e.name='" + condition.getEmp().getName() + "'";
//			}
//			if (!condition.getEmp().getDep().getName().equals("")) {
//				where += " and d.name='" + condition.getEmp().getDep().getName() + "'";
//			}
//			if (!condition.getPro().getName().equals("")) {
//				where += " and p.name='" + condition.getPro().getName() + "'";
//			}
//			if (condition.getValue() != -1) {
//				where += " and value= " + condition.getValue();
//			}
//			String sql = " SELECT  e.id as e_id, e.name AS e_name, d.id AS d_id,d.name AS d_name ,  p.id AS p_id,p.name AS p_name,s.id AS s_id,value ,grade "
//					+ "FROM employee AS e  " + "LEFT JOIN department AS d  ON e.d_id=d.id  "
//					+ "LEFT JOIN r_dep_pro AS r  ON r.d_id=d.id  " + "LEFT JOIN project AS p  ON r.p_id=p.id  "
//					+ "LEFT JOIN score AS s  ON s.e_id=e.id AND p.id=s.p_id  " + where;
//			rs = stat.executeQuery(sql);
//
//			while (rs.next()) {
//
//				Score sc = new Score();
//				sc.setId(rs.getInt("s_id"));
//
//				Employee emp = new Employee();
//				emp.setId(rs.getInt("e_id"));
//				emp.setName(rs.getString("e_name"));
//
//				Department dep = new Department();
//				dep.setId(rs.getInt("d_id"));
//				dep.setName(rs.getString("d_name"));
//
//				Project pro = new Project();
//				pro.setId(rs.getInt("p_id"));
//				pro.setName(rs.getString("p_name"));
//
//				emp.setDep(dep);
//				sc.setEmp(emp);
//				sc.setPro(pro);
//				sc.setValue((Integer) rs.getObject("value"));
//				sc.setGrade(rs.getString("grade"));
//
//				list.add(sc);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, stat, rs);
//		}
//		return list;
//	}

	
	
	public void save(Set<Score> set) {

		for (Score sc : set) {

			if (sc.getId() == 0) {
				add(sc);
			} else {
				update(sc);
			}

		}
	}

	public boolean update(Score sc) {
		int rs = 0;
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = getConnection();

			String sql = "update score set value=? where id=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getValue());
			pstat.setInt(2, sc.getId());

			rs = pstat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return rs > 0;
	}

	public int add(Score sc) {
		int id = 0;
		int rs=0;
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = getConnection();

			String sql = "insert into score(e_id,p_id,value) values(?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, sc.getEmp().getId());
			pstat.setInt(2, sc.getPro().getId());
			pstat.setInt(3, sc.getValue());
			rs = pstat.executeUpdate();
			pstat.close();
			 sql = "select last_insert_id()";
			 
			pstat = conn.prepareStatement(sql);
		ResultSet	r = pstat.executeQuery();
			if(r.next()) {
				id=r.getInt(1);
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstat, null);
		}
		return id;
	}
}
