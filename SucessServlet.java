package servlet;

import vo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/sucessServlet")
public class SucessServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter write = response.getWriter();

        List<UserInfo> userInfoList = (List<UserInfo>) req.getSession().getAttribute("userinfolist");

        write.append("<html>")
                .append("<head><title></title></head>")
                .append("<body>")
                .append("<font color ='red' size ='50px'>")
                .append("登录成功!")
                .append("</font>")
                .append("<table border ='1' width='50' height =\"50\">");
        for (UserInfo userInfo : userInfoList) {
            write.append("<tr>")
                    .append("<td>username</td>")
                    .append("<td>'" + userInfo.getUsername() + "'<td/>")
                    .append("</tr>")
                    .append("<tr>")
                    .append("<td>password</td>")
                    .append("<td>'" + userInfo.getPassword() + "'</td>")
                    .append("</tr>");
        }
        write.append("</table>")
                .append("</body>")
                .append("</html>");
    }
}
