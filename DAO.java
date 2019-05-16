import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class DAO {
	//获得数据库连接对象
		public Connection connToMySql(){
			Connection conn;
			try {
				//加载MySQL JDBC驱动程序，""中为驱动程序名
				Class.forName("com.mysql.jdbc.Driver");
				//配置连接字符串
				String url = "jdbc:mysql://localhost:3306/java_dir";
				String user = "root";	//用户名
				String password = "12345";	//密码
				// getConnection()方法，连接MySQL数据库
				conn = DriverManager.getConnection(url, user, password);
				if(!conn.isClosed()){
					System.out.println("成功连接数据库");
				}
				//返回对应的数据库连接对象
				return conn;
				
			} 
			catch (ClassNotFoundException e) {
				// 数据库驱动异常处理
				System.out.println("没有找到对应的数据库驱动类");
				e.printStackTrace();
			} 
			catch (SQLException e) {
				//数据库连接异常处理
				System.out.println("数据库连接或者数据操作失败");
				e.printStackTrace();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		// 查询文档信息
		public Text findPartByNum(String texName){
			Connection conn = null;	
			Statement st = null;
			ResultSet rs = null;	
			Text pa = new Text();
			
			try {
				//获得数据的连接
				conn = connToMySql();
				//获得Statement对象
				st = conn.createStatement();
				//发送sql语句
				StringBuffer querySql = new StringBuffer();
				
				querySql.append("select * from text where textname='");
				querySql.append(texName);
				querySql.append("'");
				rs = st.executeQuery(querySql.toString());
				//处理结果集
				while(rs.next()){
					pa.setAuthorName(rs.getString("authorname"));
					pa.setTextName(rs.getString("textname"));
					pa.setContent(rs.getString("content"));
				
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return pa;
		}
		
		
		// 查询所有文档信息
		public ArrayList<Text> findAllParts(){
			Connection conn = null;	
			Statement st = null;
			ResultSet rs = null;
			ArrayList<Text> parts = new ArrayList<Text>();
			
			try {
				//获得数据的连接
				conn = connToMySql();
				//获得Statement对象
				st = conn.createStatement();
				//发送sql语句
				StringBuffer querySql = new StringBuffer();
				
				querySql.append("select * from text;");
				rs = st.executeQuery(querySql.toString());
				//处理结果集
				while(rs.next()){
					Text pa = new Text();
					pa.setAuthorName(rs.getString("authorname"));
					pa.setTextName(rs.getString("textname"));
					pa.setContent(rs.getString("content"));
				
					parts.add(pa);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return parts;
		}
	
		//增加一条学生记录
		public boolean addPart(String aname, String cname,String content)
		{
			StringBuffer insertSql = new StringBuffer();
			insertSql.append("insert into text values('");
			insertSql.append(aname);
			insertSql.append("','");
			insertSql.append(cname);
			insertSql.append("','");
			insertSql.append(content);
			insertSql.append("');");
			PreparedStatement ps = null;
			
			try {
				//获得数据的连接
				Connection conn = connToMySql();
				//获得Statement对象
				ps = conn.prepareStatement(insertSql.toString());
				ps.executeUpdate();	
				return true;
			} 
			catch (Exception e) {
				// TODO 自动生成的 catch 块
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					ps = null;
				}
			}
		}
		
	
		// 删除一条零件记录
		public boolean deletePart(String cname)
		{
			StringBuffer deleteSql = new StringBuffer();
			deleteSql.append("delete from text where textname='");
			deleteSql.append(cname);
			deleteSql.append("';");
			PreparedStatement ps = null;
			try {
				//获得数据的连接
				Connection conn = connToMySql();
				//获得Statement对象
				ps = conn.prepareStatement(deleteSql.toString());
				ps.executeUpdate();
				return true;
			} 
			catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					ps = null;
				}
			}	
		}
		//修改一条零件记录
		public boolean updatePart(String aname, String cname,String content) {
			StringBuffer updateSql = new StringBuffer();
			updateSql.append("update text set authorname='");
			updateSql.append(aname);
			updateSql.append("',textname='");
			updateSql.append(cname);
			updateSql.append("',content='");
			updateSql.append(content);
			updateSql.append("' where textname='");
			updateSql.append(cname);
			updateSql.append("';");
			PreparedStatement ps = null;
			try {
				//获得数据的连接
				Connection conn = connToMySql();
				//获得Statement对象
				ps = conn.prepareStatement(updateSql.toString());
				ps.executeUpdate();
				return true;
			} 
			catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					ps = null;
				}
			}
		}
}
