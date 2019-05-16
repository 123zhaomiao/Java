// 用来创建属性及get set 数据库连接
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Text {
    private String authorName;  //作者姓名
    private String content;    //文本内容
    private String textName;   //文本名
  

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
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名
        String url = "jdbc:mysql://localhost:3306/java_dir";
        //用户名
        String user = "root";
        //密码
        String password = "12345";
        //遍历查询结果
        try {
            Class.forName(driver);
            //1.getConnection()方法，链接MySql数据库
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
            {
                System.out.println("Succeeded connecting the DataBase");
            }
            return con;
        } catch (ClassNotFoundException e) {
            //数据库驱动异常处理
            System.out.println("没有找到驱动");
            e.printStackTrace();
        } catch (SQLException e) {
            //数据库链接失败异常处理
            System.out.println("数据库连接失败");
            e.printStackTrace();
        }
        return null;
    }

}
