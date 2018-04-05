package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import core.mvc.CommonController;
import core.mvc.Controller;
import next.dao.UserDao;
import next.model.Response;
import next.model.User;

import java.io.IOException;

public class LoginController implements Controller {
    private static final long serialVersionUID = 1L;

    @Override
    public Response execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);

        if (user == null) {
            req.setAttribute("loginFailed", true);
            // loginFailed 가 view 로 전달되는지 확인필요.
            return Response.isNotAjax("/user/login.jsp");
        }
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            // session 에 세션아이디 이름으로 user 등록
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            return Response.isNotAjax("redirect:/");
        }
        // 비밀번호 틀렸을 때
        req.setAttribute("loginFailed", true);
        return Response.isNotAjax("user/login.jsp");
    }
}


