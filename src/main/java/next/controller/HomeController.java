package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class HomeController implements Controller {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("여기");
        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        log.debug("questions : {}", questions.toString());
        request.setAttribute("questions", questions);
        return "home.jsp";
    }
}
