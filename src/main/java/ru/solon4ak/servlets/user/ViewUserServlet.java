package ru.solon4ak.servlets.user;

import ru.solon4ak.model.User;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/user/view")
public class ViewUserServlet extends HttpServlet {
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        User user = null;
        if (username != null) {
            user = userService.getUserByName(username);
        }

        req.setAttribute("user", user);
        resp.setStatus(200);
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
    }
}
