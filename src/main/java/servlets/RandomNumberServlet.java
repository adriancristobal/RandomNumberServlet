package servlets;

import common.Constants;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = Constants.NAME_RANDOM_NUMBER_SERVLET, value = Constants.PATH_RANDOM_NUMBER)
public class RandomNumberServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                Constants.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String userNumber = req.getParameter(Constants.VARIABLE_NUMBER);
        context.setVariable(Constants.VARIABLE_VICTORY, false);

        int counter;
        int random;
        if (userNumber != null) {
            req.getSession().setAttribute(Constants.VARIABLE_NUMBER, userNumber);

            counter = (Integer) req.getSession().getAttribute(Constants.VARIABLE_COUNTER);
            random = (Integer) req.getSession().getAttribute(Constants.VARIABLE_RANDOM);
            counter = checkResult(context, userNumber, counter, random);
        } else {
            random = Constants.RANDOM.nextInt(Constants.MAX_NUMBER + 1);
            req.getSession().setAttribute(Constants.VARIABLE_RANDOM, random);
            counter = 0;
        }

        req.getSession().setAttribute(Constants.VARIABLE_COUNTER, counter);
        context.setVariable(Constants.VARIABLE_COUNTER, counter);
        templateEngine.process(Constants.HTML_RANDOM_NUMBER, context, resp.getWriter());
    }

    private int checkResult(WebContext context, String userNumber, int counter, int random) {
        boolean validNumber = false;
        if (isInteger(userNumber)) {
            int number = Integer.parseInt(userNumber);
            if (number >= Constants.MIN_NUMBER && number <= Constants.MAX_NUMBER) {
                if (counter < Constants.MAX_TRIES - 1) {
                    isCorrectNumber(context, number, random);
                } else {
                    context.setVariable(Constants.VARIABLE_MESSAGE, Constants.LOST);
                    context.setVariable(Constants.VARIABLE_COLOR, Constants.RED);
                }
                counter++;
                validNumber = true;
            }
        }

        if (!validNumber) {
            context.setVariable(Constants.VARIABLE_MESSAGE, Constants.INTRODUCE_UN_NUMERO_VALIDO);
            context.setVariable(Constants.VARIABLE_COLOR, Constants.RED);
        }
        return counter;
    }

    private void isCorrectNumber(WebContext context, int number, int random) {
        if (number > random) {
            context.setVariable(Constants.VARIABLE_MESSAGE, Constants.NUMERO_MAYOR);
        } else if (number < random) {
            context.setVariable(Constants.VARIABLE_MESSAGE, Constants.NUMERO_MENOR);
        } else {
            context.setVariable(Constants.VARIABLE_MESSAGE, Constants.WIN);
            context.setVariable(Constants.VARIABLE_COLOR, Constants.GREEN);
            context.setVariable(Constants.VARIABLE_VICTORY, true);
        }
    }

    public boolean isInteger(String userNumber) {
        boolean isInteger;
        try {
            Integer.parseInt(userNumber);
            isInteger = true;
        } catch (NumberFormatException e) {
            isInteger = false;
        }
        return isInteger;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
