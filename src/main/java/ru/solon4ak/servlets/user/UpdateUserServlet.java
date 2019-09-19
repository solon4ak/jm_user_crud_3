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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns = "/user/edit")
public class UpdateUserServlet extends HttpServlet {
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.forLanguageTag("ru_RU"));

        try {
            String username = (String) req.getSession().getAttribute("username");
            User user = userService.getUserByName(username);

            user.setFirstName(req.getParameter("firstName"));
            user.setLastName(req.getParameter("lastName"));
            user.setEmail(req.getParameter("email"));
            user.setAddress(req.getParameter("address"));
            user.setPhoneNumber(req.getParameter("phoneNumber"));
            user.setBirthDate(formatter.parse(req.getParameter("birthDate")));
            user.setPassword(req.getParameter("password"));

            userService.updateUser(user);

            req.setAttribute("user", user);
            resp.setStatus(200);
            req.getRequestDispatcher("/WEB-INF/jsp/view/user/view.jsp").forward(req, resp);
        } catch (ParseException ex) {
            resp.setStatus(400);
            Logger.getLogger(UpdateUserServlet.class.getName())
                    .log(Level.SEVERE, "Exception while updating user.", ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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
        req.getRequestDispatcher("/WEB-INF/jsp/view/user/edit.jsp").forward(req, resp);
    }
}
