package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "mylogin" , urlPatterns = "/mylogin")
public class LoginServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        writer.append("<html>").
                append("<head><title></title></head>")
                .append("<form action ='handleServlet' method ='post'>")
                .append("username:<input name ='username' type ='text'/><br/>")
                .append("password:<input name='password' type='password'/></br>")
                .append("<input type = 'submit' value = 'Submit' >")
                .append("<input type = 'reset' value = 'Reset'>")
                .append("</form>")
                .append("</html>");
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
