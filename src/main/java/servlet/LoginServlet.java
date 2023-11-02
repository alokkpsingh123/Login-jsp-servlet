package servlet;

import controller.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;

@WebServlet("/signin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uemail = req.getParameter("username");
        String upwd = req.getParameter("password");

        HttpSession session = req.getSession();
        UserDao userDao = new UserDao();
        RequestDispatcher dispatcher = null;

        try{

            String uname = userDao.signin(uemail, upwd);
            if(uname != null){
                session.setAttribute("name", uname);
                dispatcher = req.getRequestDispatcher("index.jsp");
            }else {
                req.setAttribute("status" , "failed");
                dispatcher = req.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
