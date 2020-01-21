package ca.sheridancollege.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import ca.sheridancollege.dog.Dog;
import ca.sheridancollege.dog.Dog2;

public class DAO3 {
	
	public static String[][] getDog(String myDay) {
		ArrayList dogList = new ArrayList<Dog>();
		String[][] my2 = new String[50][10];
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select count(*), breed from dog where dayx='"+myDay+"' group by breed order by dogGroup, breed ASC";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int i=0;
			while(rs.next()) {
				int idx = rs.getInt(1);
				String dog = rs.getString(2);
				my2[i][0]=Integer.toString(idx);
				my2[i][1]=dog;
				i++;
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return my2;
	}

	public static Dog getDogById(int idx) {
		Dog dg = new Dog();
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select * from dog where id="+idx;
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String dogx = rs.getString(2);
				String owner = rs.getString(3);
				String breed = rs.getString(4);
				String dogGroup = rs.getString(5);
				String gender = rs.getString(6);
				String dogRank = rs.getString(7);
				String dayx = rs.getString(8);
				String email = rs.getString(9);
				dg = new Dog(id, dogx, owner, breed, dogGroup, gender, dogRank, dayx, email);
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return dg;
	}
	
	public static void deleteDog(int idx) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "delete from dog where id="+idx;
			Statement st = conn.createStatement();
			
			st.executeUpdate(query); 
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void updateDog(Dog d) {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "update dog set dog=?, owner=?, breed=?, dogGroup=?, "
					+ "gender=?, dogRank=?, dayx=?, email = ? where id=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, d.getDog());
			ps.setString(2, d.getOwner());
			ps.setString(3, d.getBreed());
			ps.setString(4, d.getDogGroup());
			ps.setString(5, d.getGender());
			ps.setString(6, d.getDogRank());
			ps.setString(7, d.getDayx());
			ps.setString(8, d.getEmail());
			ps.setInt(9, d.getId());
			
			
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ps.executeUpdate();
			
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	
	public static int breedCountForOne(String x, String myDay) {
		
		int y=0;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select count(*) from dog where breed='"+x+"' && dayx='"+myDay+"'";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int i=0;
			while(rs.next()) {
				int idx = rs.getInt(1);
				y=idx;
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		;
		return y;
	}
	
	public static String[] getInfo(String x, String myDay) {
		String[] my2 = new String[100];
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select * from dog where breed='"+x+"' && dayx='"+myDay+"'";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int i=0;
			int mc=0, fc=0, ms=0, fs=0;
			
			while(rs.next()) {
				
				String breed = rs.getString(4);
				my2[0]=breed;
				String groupx = rs.getString(5);
				my2[1]=groupx;
				
				String gen = rs.getString(6);
				String rankx = rs.getString(7);
				
				if(gen.equalsIgnoreCase("Male") && rankx.equalsIgnoreCase("Class"))
					mc++;
				else if(gen.equalsIgnoreCase("Female") && rankx.equalsIgnoreCase("Class"))
					fc++;
				else if(gen.equalsIgnoreCase("Male") && rankx.equalsIgnoreCase("Specialty"))
					ms++;
				else if(gen.equalsIgnoreCase("Female") && rankx.equalsIgnoreCase("Specialty"))
					fs++;
				
			}
			my2[2]=mc+"";
			my2[3]=ms+"";
			my2[4]=fc+"";
			my2[5]=fs+"";
			
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return my2;
	}
	
		public static int groupCountForOne(String x) {
		int y=0;
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

			String query = "select count(*) from dog where dogGroup='"+x+"'";
			Statement st = conn.createStatement();
			
			//st.executeQuery(); --> Get a Table. //for select
			//st.executeUpdate(); --> Modify a Table // for others

			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			int i=0;
			while(rs.next()) {
				int idx = rs.getInt(1);
				y=idx;
			}
			conn.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		;
		return y;
	}
		
		public static int getCount(String myDay) {
			int breedCount=0;
			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = null;
				conn = DriverManager.getConnection("jdbc:mysql://localhost/dog?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root");

				String query = "select count(*) from dog where dayx='"+myDay+"' group by breed";
				Statement st = conn.createStatement();
				
				//st.executeQuery(); --> Get a Table. //for select
				//st.executeUpdate(query); 

				ResultSet rs = st.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				int i=0;
				while(rs.next()) {
					int idx = rs.getInt(1);
					breedCount++;
				}
				
				
				
				conn.close();

			} catch (Exception e) {

				System.out.println(e);

			}
			
			return breedCount;
			
		}
	
	

}
