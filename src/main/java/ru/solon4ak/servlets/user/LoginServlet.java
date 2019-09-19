package ru.solon4ak.servlets.user;

import ru.solon4ak.model.User;
import ru.solon4ak.service.UserService;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("logout") != null) {
            session.invalidate();
            response.sendRedirect("login");
            return;
        } else if (session.getAttribute("username") != null) {
            response.sendRedirect("user/view");
            return;
        }

        request.setAttribute("loginFailed", false);
        request.getRequestDispatcher("/WEB-INF/jsp/view/user/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            response.sendRedirect("user/view");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty()
            || password == null || password.isEmpty()
        || !userService.verifyUser(username, password)) {
            session.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/user/login.jsp").forward(request, response);
        } else {
            session.setAttribute("username", username);
            request.changeSessionId();
            User user = userService.getUserByName(username);
            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin/list");
            } else {
                response.sendRedirect("user/view");
            }

        }
    }
}
