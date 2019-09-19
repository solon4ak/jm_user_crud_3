package ru.solon4ak.servlets.admin;

import ru.solon4ak.service.UserService;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/list")
public class ListUsersServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        request.setAttribute("users", userService.getAllUsers());
        response.setStatus(200);
        request.getRequestDispatcher("/WEB-INF/jsp/view/admin/list.jsp").forward(request, response);
    }
}
