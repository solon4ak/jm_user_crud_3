package ru.solon4ak.servlets;

import ru.solon4ak.model.User;
import ru.solon4ak.service.UserService;
import ru.solon4ak.service.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(
        filterName = "adminFilter",
        dispatcherTypes = {DispatcherType.REQUEST},
        urlPatterns = {"/admin", "/admin/*"}
)
public class AdminFilter implements Filter {

    private UserService userService = UserServiceImpl.getInstance();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) req).getSession(false);
        String username = (String) session.getAttribute("username");

        if (username != null) {
            User user = userService.getUserByName(username);
            if (user != null && "admin".equals(user.getRole())) {
                chain.doFilter(req, resp);
            } else {
                ((HttpServletResponse) resp).setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
