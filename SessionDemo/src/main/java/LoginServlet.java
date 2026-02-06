import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        HttpSession session = req.getSession();
        session.setAttribute("userName", name);
        session.setAttribute("userEmail", email);

        String encodeName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
        Cookie cookie = new Cookie("user", encodeName);
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);

        // redirect to welcome
        resp.sendRedirect("welcome");
    }
}