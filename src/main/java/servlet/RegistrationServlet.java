package servlet;

import controller.UserDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uname = req.getParameter("name");
        String uemail = req.getParameter("email");
        String upasword = req.getParameter("pass");
        String umobile = req.getParameter("contact");

        User user = new User(uname, uemail, upasword, umobile);
        UserDao userDao = new UserDao();

        RequestDispatcher dispatcher = null;

        try{
            int rowCount = userDao.signp(user);

            dispatcher = req.getRequestDispatcher("registration.jsp");

            if(rowCount > 0){
                req.setAttribute("status", "success");
            }else {
                req.setAttribute("status", "failed");
            }
            dispatcher.forward(req, resp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
