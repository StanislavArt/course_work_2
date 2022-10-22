package pro.sky.java.course2.course_work.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("exam")
public class ExaminerController {
    private final ExaminerService examinerService;

    public ExaminerController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("get/{amount}")
    public Collection<Question> getQuestions(@PathVariable Integer amount) {
        return examinerService.getQuestions(amount);
    }
}
