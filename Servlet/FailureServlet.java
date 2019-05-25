package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/failureServlet")
public class FailureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = response.getWriter();

        writer.append("<html>")
                .append("<head><title></title></head>")
                .append("<body>")
                .append("<font coor='red' size='50px'>")
                .append("登录失败<br/><a href ='mylogin'>点击回到登录页面</a>")
                .append("</font>")
                .append("</body>")
                .append("</html>");
    }
}
