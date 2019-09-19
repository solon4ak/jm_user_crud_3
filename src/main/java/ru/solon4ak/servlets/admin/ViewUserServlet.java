package ru.solon4ak.servlets.admin;

import ru.solon4ak.model.User;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/view")
public class ViewUserServlet extends HttpServlet {
    private UserServiceImpl userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String idString = req.getParameter("id");
        long id = Long.parseLong(idString);

        User user = userService.getUserById(id);
        req.setAttribute("user", user);
        resp.setStatus(200);
        req.getRequestDispatcher("/WEB-INF/jsp/view/admin/view.jsp").forward(req, resp);
    }
}