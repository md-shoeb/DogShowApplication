package ca.sheridancollege.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import ca.sheridancollege.dog.Dog;

public class DAO {
	
	public static void addDog(Dog d) {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "insert into dog (dog,owner,breed,dogGroup,gender,dogRank,dayx, email) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getDog());
			ps.setString(2, d.getOwner());
			ps.setString(3, d.getBreed());
			ps.setString(4, d.getDogGroup());
			ps.setString(5, d.getGender());
			ps.setString(6, d.getDogRank());
			ps.setString(7, d.getDayx());
			ps.setString(8, d.getEmail());
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ps.executeUpdate();
			
			conn.close();

		} catch (Exception e) {

			System.out.println(e);
		}
	}
	
	public static ArrayList<Dog> getDog() {
		ArrayList dogList = new ArrayList<Dog>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select * from dog";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				int idx = rs.getInt(1);
				String dog = rs.getString(2);
				String owner = rs.getString(3);
				String breed = rs.getString(4);
				String dogGroup = rs.getString(5);
				String gender = rs.getString(6);
				String dogRank = rs.getString(7);
				String dayx = rs.getString(8);
				String email = rs.getString(9);
				Dog dx = new Dog(idx, dog, owner, breed, dogGroup, gender, dogRank, dayx, email);
				
				dogList.add(dx);
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return dogList;
	}
	
	public static ArrayList<Dog> getDogByID(String dog, String s) {
		ArrayList<Dog> dogList = new ArrayList<Dog>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select * from dog where " + s + " LIKE '" + dog + "%'";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(query); 

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				int idx = rs.getInt(1);
				String dogx = rs.getString(2);
				String owner = rs.getString(3);
				String breed = rs.getString(4);
				String dogGroup = rs.getString(5);
				String gender = rs.getString(6);
				String dogRank = rs.getString(7);
				String dayx = rs.getString(8);
				String email = rs.getString(9);
				Dog dx = new Dog(idx, dogx, owner, breed, dogGroup, gender, dogRank, dayx, email);
				
				dogList.add(dx);			
				
			}
			conn.close();

		} catch (Exception e) {

			System.out.println(e);

		}
		
		return dogList;
		
	}

}
