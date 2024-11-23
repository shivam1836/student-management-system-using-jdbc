package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Studentoperation {

	FileInputStream fis;
	Connection con;
	Properties property=new Properties();
	PreparedStatement ps;
	ResultSet rs;
	Statement stm;
	{
		//Step 1: load and register 
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver loaded");
			//Step 2: connection;
			fis=new FileInputStream("C:\\Users\\shiva\\eclipse-workspace\\advanced java\\jdbc\\practice\\src\\main\\resources\\data.properties");
			property.load(fis);
			
			String url=property.getProperty("url");
			System.out.println(url);
			con=DriverManager.getConnection(url,property);
			System.out.println("Connection done");
			
			stm=con.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public boolean insertstudent(Student s) {
		String insert="insert into student(name,percentage,phone,stream) values(?,?,?,?)";
		try {
			ps=con.prepareStatement(insert);
		ps.setString(1,s.getName());
		ps.setDouble(2,s.getPercentage());
		ps.setLong(3, s.getPhone());
		ps.setString(4,s.getStream());
		if(ps.executeUpdate()>0) {
			return true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return false;
	}

	public boolean updaterecord(Student s) {
		String update="update student set name=?,percentage=?,phone=?,stream=? where id=?";
		try {
			ps=con.prepareStatement(update);
			ps.setString(1,s.getName());
			ps.setDouble(2,s.getPercentage());
			ps.setLong(3, s.getPhone());
			ps.setString(4,s.getStream());
			ps.setInt(5, s.getId());
			
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean deletestudent(int id) {
		String del="Delete from student where id=(?)";
		try {
			ps=con.prepareStatement(del);
		ps.setInt(1,id);
		if(ps.executeUpdate()>0) {
			return true;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return false;
	}
	
	public Student viewstudent(int id) {
	
			String sql = "select * from student where id=(?)";
try {
	ps=con.prepareStatement(sql);
	ps.setInt(1, id);
	rs=ps.executeQuery();
	if(rs.next()) {
		return new Student(rs.getInt("id"),rs.getString("name"),rs.getDouble("percentage"),rs.getLong("phone"),rs.getString("stream"));
	}
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}return null;

	}
	
	public List<Student> viewallstudent() {
		List<Student> data= new ArrayList<Student>();
		String sql = "select * from student";
try {

rs=stm.executeQuery(sql);
if(rs.next()) {
	do{
		data.add(new Student(rs.getInt("id"),rs.getString("name"),rs.getDouble("percentage"),rs.getLong("phone"),rs.getString("stream")));
	}while(rs.next());
	return data;
}
// we use do while because if we use only while it will point the second row ,
//for fetching data of first row we use do-while loop 

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}return null;

}
	
	public static void main(String[] args) {
		
		Studentoperation obj =new Studentoperation();
		Scanner sc=new Scanner(System.in);
//		Student s=new Student("shivam",98,8978,"science");
//		System.out.println(obj.insertstudent(s));
//		System.out.println(obj.viewallstudent());
//		System.out.println(obj.deletestudent(1));
		
		System.out.println("Application started");
		boolean condition =true;
		while(condition) {
			System.out.println("1.Insert Student");
			System.out.println("2.view Student");
			System.out.println("3.View all Student");
			System.out.println("4.Delete Student");
			System.out.println("5.update Student");
//			System.out.println("6.Grade for each student");
			System.out.println("Any other key to exit.");
			
			System.out.println("Enter your choice:");
			int choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: {
				System.out.println("Enter name:");
				String name=sc.nextLine();
				System.out.println("enter percentage:");
				double percentage=sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter phone:");
				long phone=sc.nextLong();
				sc.nextLine();
				System.out.println("Enter Stream:");
				String stream=sc.nextLine();
				
				Student s=new Student(name,percentage,phone,stream);
				if(obj.insertstudent(s)) {
					System.out.println("Data inserted successfully");
					break;
				}else {
					System.out.println("failed to insert data");
					break;
				}
			}
			case 2:{
				System.out.println("Enter id:");
				int id=sc.nextInt();
				sc.nextLine();
				System.out.println(obj.viewstudent(id));
				break;
			}
			case 3:{
				System.out.println(obj.viewallstudent());
				break;
			}
			case 4:{
				System.out.println("Enter id:");
				int id=sc.nextInt();
				sc.nextLine();
				System.out.println(obj.deletestudent(id));
				break;
			}
			case 5:{
				System.out.println("Enter id:");
				int id=sc.nextInt();
				sc.nextLine();
				
				Student s=obj.viewstudent(id);
				System.out.println(obj.viewstudent(id));
				if(s!=null) {
				
					System.out.println("Enter the data you want to update");
					System.out.println("Enter name:");
					String name=sc.nextLine();
					System.out.println("enter percentage:");
					String percentage=sc.nextLine();
					System.out.println("Enter phone:");
					String phone=sc.nextLine();
					System.out.println("Enter Stream:");
					String stream=sc.nextLine();
					
					if(!name.isEmpty()) {
						s.setName(name);
					}
					if(!percentage.isEmpty()) {
						s.setPercentage(Double.parseDouble(percentage));
					}
					if(!phone.isEmpty()) {
						s.setPhone(Long.parseLong(phone));
					}
					if(!stream.isEmpty()) {
						s.setStream(stream);
					}
					if(obj.updaterecord(s)==true) {
						System.out.println("update successfully");
						break;
					}else {
						System.out.println("error");
						break;
					}
				}
			}
			
			default:
				condition=false;
				break;
			}
		}

	}
}
