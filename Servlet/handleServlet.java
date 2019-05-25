package servlet;

import service.QueryService;
import vo.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/handleServlet")
public class handleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.包装成UserInfo类
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);

        //3.查询
        QueryService qs = new QueryService();
        List<UserInfo> list = qs.queryDb(userInfo);

        if(list!=null){
            request.getSession().setAttribute("userinfolist",list);
            request.getRequestDispatcher("sucessServlet").forward(request,response);
        }else{
            request.getRequestDispatcher("failureServlet").forward(request,response);
        }

    }

}
