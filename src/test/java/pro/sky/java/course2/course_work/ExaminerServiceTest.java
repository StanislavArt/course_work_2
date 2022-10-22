package pro.sky.java.course2.course_work;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.repository.JavaQuestionRepository;
import pro.sky.java.course2.course_work.service.ExaminerServiceImpl;
import pro.sky.java.course2.course_work.service.JavaQuestionService;
import pro.sky.java.course2.course_work.service.MathQuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExaminerServiceTest {
    @Mock
    private JavaQuestionService javaQuestionService;
    @Mock
    private MathQuestionService mathQuestionService;
    @InjectMocks
    private ExaminerServiceImpl out;

    private ArrayList<Question> listOfQuestions;

    @BeforeAll
    public void initCollection() {
        listOfQuestions = new ArrayList<>();
        listOfQuestions.add(new Question("question1", "answer1"));
        listOfQuestions.add(new Question("question2", "answer2"));
        listOfQuestions.add(new Question("question3", "answer3"));
    }

    @BeforeEach
    public void init() {
        Mockito.when(javaQuestionService.getRandomQuestion()).thenReturn(listOfQuestions.get(0));
        Mockito.when(javaQuestionService.getAll()).thenReturn(listOfQuestions);

        Question mathQuestion = new Question("1 + 1 = ", "2");
        Mockito.when(mathQuestionService.getRandomQuestion()).thenReturn(mathQuestion);
    }

    @Test
    public void getQuestions() {
        Collection<Question> actual = out.getQuestions(0);
        Assertions.assertNull(actual);

        int sizeJavaQuestion = javaQuestionService.getAll().size();
        Assertions.assertTrue(sizeJavaQuestion > 0);

        int numberOfQuestions = sizeJavaQuestion * 2;
        actual = out.getQuestions(numberOfQuestions);
        int numberOfAttempts = numberOfQuestions * 4;

        Mockito.verify(javaQuestionService, Mockito.atLeast(numberOfQuestions)).getRandomQuestion();
        Mockito.verify(javaQuestionService, Mockito.atMost(numberOfAttempts)).getRandomQuestion();
    }
}
