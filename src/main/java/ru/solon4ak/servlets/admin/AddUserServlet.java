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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@WebServlet(urlPatterns = "/admin/add")
public class AddUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        User user;
        try {
            user = new User(
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    req.getParameter("address"),
                    req.getParameter("phoneNumber"),
                    formatter.parse(req.getParameter("birthDate")),
                    req.getParameter("nickName"),
                    req.getParameter("password")
            );
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        userService.addUser(user, req.getParameter("role"));
        user = userService.getUserByName(req.getParameter("nickName"));
        req.setAttribute("user", user);
        resp.setStatus(200);
        req.getRequestDispatcher("/WEB-INF/jsp/view/admin/view.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<String> userRoles = new ArrayList<>(Arrays.asList(new String[] {"user", "admin"}));
        req.setAttribute("roles", userRoles);
        req.getRequestDispatcher("/WEB-INF/jsp/view/admin/add.jsp").forward(req, resp);
    }
}
