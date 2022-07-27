package pl.coderslab.users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserEdit", value = "/user/edit")
public class UserEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");

        UserDao userDao = new UserDao();
        User read = userDao.read(Integer.parseInt(id));

        request.setAttribute("user", read);

        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User user = new User();
        user.setId(Integer.parseInt(request.getParameter("id")));
        user.setUserName(request.getParameter("userName"));
        user.setUserEmail(request.getParameter("userEmail"));
        user.setUserPassword(request.getParameter("userPassword"));

        UserDao userDao = new UserDao();
        userDao.update(user);

        response.sendRedirect(request.getContextPath() + "/user/list");
    }
}
