import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;


public class DAO {
	//������ݿ����Ӷ���
		public Connection connToMySql(){
			Connection conn;
			try {
				//����MySQL JDBC��������""��Ϊ����������
				Class.forName("com.mysql.jdbc.Driver");
				//���������ַ���
				String url = "jdbc:mysql://localhost:3306/java_dir";
				String user = "root";	//�û���
				String password = "12345";	//����
				// getConnection()����������MySQL���ݿ�
				conn = DriverManager.getConnection(url, user, password);
				if(!conn.isClosed()){
					System.out.println("�ɹ��������ݿ�");
				}
				//���ض�Ӧ�����ݿ����Ӷ���
				return conn;
				
			} 
			catch (ClassNotFoundException e) {
				// ���ݿ������쳣����
				System.out.println("û���ҵ���Ӧ�����ݿ�������");
				e.printStackTrace();
			} 
			catch (SQLException e) {
				//���ݿ������쳣����
				System.out.println("���ݿ����ӻ������ݲ���ʧ��");
				e.printStackTrace();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		// ��ѯ�ĵ���Ϣ
		public Text findPartByNum(String texName){
			Connection conn = null;	
			Statement st = null;
			ResultSet rs = null;	
			Text pa = new Text();
			
			try {
				//������ݵ�����
				conn = connToMySql();
				//���Statement����
				st = conn.createStatement();
				//����sql���
				StringBuffer querySql = new StringBuffer();
				
				querySql.append("select * from text where textname='");
				querySql.append(texName);
				querySql.append("'");
				rs = st.executeQuery(querySql.toString());
				//��������
				while(rs.next()){
					pa.setAuthorName(rs.getString("authorname"));
					pa.setTextName(rs.getString("textname"));
					pa.setContent(rs.getString("content"));
				
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return pa;
		}
		
		
		// ��ѯ�����ĵ���Ϣ
		public ArrayList<Text> findAllParts(){
			Connection conn = null;	
			Statement st = null;
			ResultSet rs = null;
			ArrayList<Text> parts = new ArrayList<Text>();
			
			try {
				//������ݵ�����
				conn = connToMySql();
				//���Statement����
				st = conn.createStatement();
				//����sql���
				StringBuffer querySql = new StringBuffer();
				
				querySql.append("select * from text;");
				rs = st.executeQuery(querySql.toString());
				//��������
				while(rs.next()){
					Text pa = new Text();
					pa.setAuthorName(rs.getString("authorname"));
					pa.setTextName(rs.getString("textname"));
					pa.setContent(rs.getString("content"));
				
					parts.add(pa);
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return parts;
		}
	
		//����һ��ѧ����¼
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
				//������ݵ�����
				Connection conn = connToMySql();
				//���Statement����
				ps = conn.prepareStatement(insertSql.toString());
				ps.executeUpdate();	
				return true;
			} 
			catch (Exception e) {
				// TODO �Զ����ɵ� catch ��
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					ps = null;
				}
			}
		}
		
	
		// ɾ��һ�������¼
		public boolean deletePart(String cname)
		{
			StringBuffer deleteSql = new StringBuffer();
			deleteSql.append("delete from text where textname='");
			deleteSql.append(cname);
			deleteSql.append("';");
			PreparedStatement ps = null;
			try {
				//������ݵ�����
				Connection conn = connToMySql();
				//���Statement����
				ps = conn.prepareStatement(deleteSql.toString());
				ps.executeUpdate();
				return true;
			} 
			catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					ps = null;
				}
			}	
		}
		//�޸�һ�������¼
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
				//������ݵ�����
				Connection conn = connToMySql();
				//���Statement����
				ps = conn.prepareStatement(updateSql.toString());
				ps.executeUpdate();
				return true;
			} 
			catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
				return false;
			}
			finally {
				if(ps != null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					ps = null;
				}
			}
		}
}
