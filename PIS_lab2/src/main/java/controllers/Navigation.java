package controllers;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Navigation {
    public static final String MAIN_PAGE = "content/jsp/mainPage.jsp";
    public static final String ERROR_PAGE = "content/jsp/error.jsp";
    public static final String EXAM_PAGE = "content/jsp/examPage.jsp";
    public static final String SPECIALIZATION_PAGE = "content/jsp/specializationPage.jsp";

    public static final Map<String, String> studentNavigationBar = Stream.of(new String[][]{
            {"Exams", "student?command=exams"},
            {"Specializations", "student?command=specialization"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static final Map<String, String> professorNavigationBar = Stream.of(new String[][]{
            {"Exams", "professor?command=exams"},
            {"Specializations", "professor?command=specialization"},
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

}
