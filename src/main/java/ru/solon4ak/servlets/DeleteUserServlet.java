package ru.solon4ak.servlets;

import ru.solon4ak.service.UserService;
import ru.solon4ak.service.UserServiceImpl;
import ru.solon4ak.util.DBException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/delete")
public class DeleteUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String idString = req.getParameter("id");
        long id = Long.parseLong(idString);
        try {
            userService.deleteUser(id);
            resp.setStatus(200);
            req.setAttribute("users", userService.getAllUsers());
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(req, resp);
        } catch (DBException ex) {
            resp.setStatus(400);
            Logger.getLogger(DeleteUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while deleting user.", ex);
        }
    }

}
