package common;

import java.util.Random;

public class Constants {
    private Constants() {}

    public static final String TEMPLATE_ENGINE_ATTR = "thymeleaf.TemplateEngineInstance";
    public static final String PATH_PREFIX = "/WEB-INF/templates/";
    public static final String PATH_SUFFIX = ".html";
    public static final String PATH_RANDOM_NUMBER = "/random_number";

    public static final Random RANDOM = new Random();

    public static final int MIN_NUMBER = 1;
    public static final int MAX_NUMBER = 100;
    public static final int MAX_TRIES = 10;

    public static final String VARIABLE_RANDOM = "random";
    public static final String VARIABLE_COUNTER = "counter";
    public static final String VARIABLE_COLOR = "color";
    public static final String VARIABLE_VICTORY = "isVictory";
    public static final String LOST = "Has perdido :(";
    public static final String INTRODUCE_UN_NUMERO_VALIDO = "Introduce un número válido.";
    public static final String RED = "red";
    public static final String NUMERO_MAYOR = "Tu número es mayor";
    public static final String NUMERO_MENOR = "Tu número es menor";
    public static final String WIN = "¡¡HAS ACERTADO!!";
    public static final String GREEN = "green";
    public static final String NAME_RANDOM_NUMBER_SERVLET = "RandomNumberServlet";
    public static final String HTML_RANDOM_NUMBER = "random_number";
    public static final String VARIABLE_NUMBER = "number";
    public static final String VARIABLE_MESSAGE = "message";
}
