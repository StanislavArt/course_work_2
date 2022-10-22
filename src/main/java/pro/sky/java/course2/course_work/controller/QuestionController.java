package pro.sky.java.course2.course_work.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.service.JavaQuestionService;
import pro.sky.java.course2.course_work.service.MathQuestionService;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/exam")
public class QuestionController {
    private final QuestionService questionServiceJava;
    private final QuestionService questionServiceMath;

    public QuestionController(JavaQuestionService questionServiceJava, MathQuestionService questionServiceMath) {
        this.questionServiceJava = questionServiceJava;
        this.questionServiceMath = questionServiceMath;
    }

    @GetMapping("java/add")
    public Question addQuestionJava(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionServiceJava.add(question, answer);
    }

    @GetMapping("java/remove")
    public Question removeQuestionJava(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionServiceJava.remove(new Question(question, answer));
    }

    @GetMapping("java/list")
    public Collection<Question> listQuestionJava() {
        return questionServiceJava.getAll();
    }

    @GetMapping("math/add")
    public Question addQuestionMath(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionServiceMath.add(question, answer);
    }

    @GetMapping("math/remove")
    public Question removeQuestionMath(@RequestParam("question") String question, @RequestParam("answer") String answer) {
        return questionServiceMath.remove(new Question(question, answer));
    }

    @GetMapping("math/list")
    public Collection<Question> listQuestionMath() {
        return questionServiceMath.getAll();
    }
}
