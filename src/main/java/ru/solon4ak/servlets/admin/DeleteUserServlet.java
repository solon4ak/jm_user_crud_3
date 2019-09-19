package ru.solon4ak.servlets.admin;

import ru.solon4ak.model.User;
import ru.solon4ak.service.UserService;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String idString = req.getParameter("id");
        long id = Long.parseLong(idString);
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        resp.setStatus(200);
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/jsp/view/admin/list.jsp").forward(req, resp);
    }

}
