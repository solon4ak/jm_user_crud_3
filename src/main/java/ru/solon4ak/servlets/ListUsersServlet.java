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

@WebServlet(urlPatterns = "/list")
public class ListUsersServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            request.setAttribute("users", userService.getAllUsers());
            response.setStatus(200);
            request.getRequestDispatcher("/WEB-INF/jsp/view/user/list.jsp").forward(request, response);
        } catch (DBException ex) {
            response.setStatus(400);
            Logger.getLogger(ListUsersServlet.class.getName())
                    .log(Level.SEVERE, "Exception while retrieve users", ex);
        }
    }
}
