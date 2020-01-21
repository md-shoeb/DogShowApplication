package ca.sheridancollege.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import ca.sheridancollege.dog.User;

public class DAO2 {
	public static User findUserAccount(String name) {
		User user = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
				
				String query = "select * from sec_user where user_name='"+name+"'";
				Statement st = conn.createStatement();
				
				//st.executeQuery(); --> Get a Table. //for select
				//st.executeUpdate(); --> Modify a Table // for others

				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				
				while(rs.next()) {
					user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
				}
				
				conn.close();

			} catch (Exception e) {

				System.out.println(e);

			}
			return user;

		}
	
	public static ArrayList<String> getRoleNames(int i) {
		ArrayList<String> roles = new ArrayList<String>();

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
				
				String query = "select r.role_id, r.user_id, r.role_id, "
						+ "sr.role_id, sr.role_name "
						+ "from sec_user s, user_role r, sec_role sr where r.role_id = sr.role_id and r.user_id = s.user_id and s.user_id="+ i;
				System.out.println(query);
				Statement st = conn.createStatement();
				
				//st.executeQuery(); --> Get a Table. //for select
				//st.executeUpdate(); --> Modify a Table // for others

				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				
				while(rs.next()) {
					roles.add(rs.getString(5));
				}
				
				conn.close();

			} catch (Exception e) {

				System.out.println(e);

			}
			
			return roles;

		}
	
	public static void addUser(String userName, String encryptedPassword) {
		User user = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
				
				String query = "insert into SEC_User (USER_NAME, ENCRYTED_PASSWORD, ENABLED)" + 
						"values (?,?, 1)";
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, userName);
				ps.setString(2, encryptedPassword);
				ps.executeUpdate();
				
				conn.close();

			} catch (Exception e) {

				System.out.println(e);

			}

		}
	
	public static void addUserRoles(long userID, long roleID) {
		User user = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");
				
				String query = "insert into user_role (USER_ID, ROLE_ID)" + 
						"values (?,?)";
				
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setLong(1, userID);
				ps.setLong(2, roleID);
				ps.executeUpdate();
				
				conn.close();

			} catch (Exception e) {

				System.out.println(e);

			}

		}
	
		
}


	
