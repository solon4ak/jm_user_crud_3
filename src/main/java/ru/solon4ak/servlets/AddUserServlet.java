package ru.solon4ak.servlets;

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
import java.util.Locale;

@WebServlet(urlPatterns = "/add")
public class AddUserServlet extends HttpServlet {

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        User user = null;
        try {
            user = new User(
                    req.getParameter("firstName"),
                    req.getParameter("lastName"),
                    req.getParameter("email"),
                    req.getParameter("address"),
                    req.getParameter("phoneNumber"),
                    formatter.parse(req.getParameter("birthDate"))
            );
        } catch (ParseException e) {
            throw new ServletException(e);
        }

        userService.addUser(user);
        user = userService.getUserByName(req.getParameter("firstName"));
        req.setAttribute("user", user);
        resp.setStatus(200);
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/add.jsp").forward(req, resp);
    }
}
