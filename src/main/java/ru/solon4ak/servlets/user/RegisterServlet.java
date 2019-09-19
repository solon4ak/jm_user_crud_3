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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

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
            userService.addUser(user, "user");
            req.getSession(true).setAttribute("username", req.getParameter("nickName"));
            req.changeSessionId();
            resp.sendRedirect("user/view");
        } catch (ParseException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/add.jsp").forward(req, resp);
    }
}
