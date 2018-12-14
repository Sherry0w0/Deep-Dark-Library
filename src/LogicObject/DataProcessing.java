package LogicObject;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Hashtable;

import javax.swing.JOptionPane;



public class DataProcessing {
	public static void main(String[] args) {
		
	}
	private static Connection connection;
	private static Statement statement;
	private static Statement statement_1;
	private static ResultSet resultSet;
	private static ResultSet resultSet_1;
	private static boolean connectedToDB=false;
	static String driverName="com.mysql.jdbc.Driver";               // �������ݿ�������
	static String url="jdbc:mysql://localhost:3306/dblm";       // �������ݿ��URL
	static String user="root";                                      // ���ݿ��û�
	static String password="123456";
	static Hashtable<String, Borrower> borrowers;
	static {
		borrowers = new Hashtable<String, Borrower>();
		try {
			init();
		} catch (ClassNotFoundException e) {
			System.out.println("������������");
		} catch (SQLException e) {
			System.out.println("���ݿ����");
		}
	}
	public static void init() throws ClassNotFoundException, SQLException
	{
		connectToDB(driverName);
		Borrower temp=null;
		if(!connectedToDB)
			throw new SQLException("�޷����ӵ����ݿ�");
		statement=connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		statement_1=connection.createStatement(
				ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		String sql="select * from borrower";
		String tempsql;
		resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			String name=resultSet.getString("name");
			String pwd=resultSet.getString("password");
			boolean type=resultSet.getBoolean("type");
			String ID=resultSet.getString("borrowerid");
			String CardNo=resultSet.getString("cardno");
			String department=resultSet.getString("department");
			if (type)
			{
				tempsql="select * from undergraduate where borrowerid="+"'"+ID+"'";
				resultSet_1 = statement_1.executeQuery(tempsql);
				resultSet_1.next();
				String major=resultSet_1.getString("major");
				temp = new Undergraduate(ID,CardNo,department,name,type,pwd,major);
				System.out.println(temp.getPassword());
			}
			else
			{
				tempsql="select * from graduate where borrowerid="+"'"+ID+"'";
				resultSet_1 = statement_1.executeQuery(tempsql);
				resultSet_1.next();
				String major=resultSet_1.getString("major");
				String director=resultSet_1.getString("director");
				temp = new GraduatedStu(ID,CardNo,department,name,type,pwd,major,director);
			}
			borrowers.put(ID, temp);
		}
	}
	public static Borrower searchBorrower(String ID, String pwd){
		Borrower temp=null;
		temp=borrowers.get(ID);
		String str=temp.getPassword();
		if(str.equals(pwd))
			return temp;
		else
		{
			JOptionPane.showMessageDialog(null, "�˺Ż��������");
		}
		return null;
	}
	public static  void connectToDB(String driverName) throws SQLException, ClassNotFoundException{
		Class.forName(driverName);		
		connection=DriverManager.getConnection(url, user, password);
		connectedToDB=true;
	}
	public static void disconnectFromDB() {
		if(connectedToDB) {
			try {
				resultSet.close();
				statement.close();
			}catch(SQLException sqlException) {
				sqlException.printStackTrace();
			}finally {
				connectedToDB=false;
			}
		}
	}
}