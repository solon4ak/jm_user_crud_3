package ru.solon4ak.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) req).getSession(false);
        if (session != null && session.getAttribute("username") == null) {
            ((HttpServletRequest) req).getRequestDispatcher("/WEB-INF/jsp/view/user/login.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
