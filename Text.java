// �����������Լ�get set ���ݿ�����
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Text {
    private String authorName;  //��������
    private String content;    //�ı�����
    private String textName;   //�ı���
  

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String d) {
        this.content = d;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public Connection connectToMysql()
    {
        Connection con;
        //����������
        String driver = "com.mysql.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���
        String url = "jdbc:mysql://localhost:3306/java_dir";
        //�û���
        String user = "root";
        //����
        String password = "12345";
        //������ѯ���
        try {
            Class.forName(driver);
            //1.getConnection()����������MySql���ݿ�
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
            {
                System.out.println("Succeeded connecting the DataBase");
            }
            return con;
        } catch (ClassNotFoundException e) {
            //���ݿ������쳣����
            System.out.println("û���ҵ�����");
            e.printStackTrace();
        } catch (SQLException e) {
            //���ݿ�����ʧ���쳣����
            System.out.println("���ݿ�����ʧ��");
            e.printStackTrace();
        }
        return null;
    }

}
