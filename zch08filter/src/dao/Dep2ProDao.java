package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Project;

public class Dep2ProDao extends BaseDao {

	public List<Project> searchByDepartment(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			rs = stat.executeQuery("select * from v_dep_pro where d_id=" + depId);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("p_id"));
				pro.setName(rs.getString("p_name"));

				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public List<Project> searchByNotDepartment(int depId) {
		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "select * from project where id not in( select p_id from v_dep_pro where d_id=" + depId + ")";
			rs = stat.executeQuery(sql);

			while (rs.next()) {
				Project pro = new Project();
				pro.setId(rs.getInt("id"));
				pro.setName(rs.getString("name"));

				list.add(pro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, rs);
		}
		return list;
	}

	public boolean add(int depId, String[] proIds) {

		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			for(int i=0;i< proIds.length;i++) {
				int proId=Integer.parseInt(proIds[i]) ;
				String sql="insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ") ";
				rs = stat.executeUpdate(sql);
			}
			
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}
	public boolean add(int depId, int proId) {

		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "insert into r_dep_pro(d_id,p_id) values(" + depId + "," + proId + ") ";
			rs = stat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}
	public boolean delete(int depId, String[] proIds) {

		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			for(int i=0;i< proIds.length;i++) {
				int proId=Integer.parseInt(proIds[i]) ;
				String sql="delete from r_dep_pro where d_id="+depId+" and p_id="+proId;
				rs = stat.executeUpdate(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}
	public boolean delete(int depId, int proId) {

		List<Project> list = new ArrayList<>();
		Connection conn = null;
		Statement stat = null;
		int rs = 0;
		try {
			conn = getConnection();
			stat = conn.createStatement();
			String sql = "delete from r_dep_pro where d_id="+depId+" and p_id="+proId;
			rs = stat.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, stat, null);
		}
		return rs > 0;
	}
}
