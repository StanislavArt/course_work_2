package pro.sky.java.course2.course_work;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.repository.JavaQuestionRepository;
import pro.sky.java.course2.course_work.service.JavaQuestionService;
import pro.sky.java.course2.course_work.service.QuestionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @Mock
    private JavaQuestionRepository questionRepository;
    @InjectMocks
    private JavaQuestionService out;
    private Collection<Question> listOfQuestions;

    @BeforeEach
    public void init() {
        listOfQuestions = new HashSet<>();
        listOfQuestions.add(new Question("question11", "answer11"));
        listOfQuestions.add(new Question("question12", "answer12"));
        listOfQuestions.add(new Question("question13", "answer13"));
    }

    @Test
    public void addQuestion() {
        Question expected = new Question("question_1", "answer_1");
        Mockito.when(questionRepository.add(any(), any())).thenReturn(new Question("question_1", "answer_1"));
        Question actual = out.add("question_1", "answer_1");

        Assertions.assertEquals(expected, actual);

        expected = new Question("question_2", "answer_2");
        Mockito.when(questionRepository.add(any())).thenReturn(expected);
        actual = out.add(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addQuestionException() {
        Mockito.when(questionRepository.add(any(), eq(null))).thenThrow(IllegalArgumentException.class);
        Assertions.assertThrows(IllegalArgumentException.class, () -> out.add("question", null));
    }

    @Test
    public void removeQuestion() {
        Question expected = new Question("test_question", "test_answer");
        Mockito.when(questionRepository.remove(any())).thenReturn(expected);
        Question actual = out.remove(expected);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void getAllQuestions() {
        int sizeExpected = 3;
        Collection<Question> expected = new ArrayList<>(List.of(
                new Question("a1", "b1"),
                new Question("a2", "b2"),
                new Question("a3", "b3")
        ));

        JavaQuestionRepository repository = new JavaQuestionRepository();
        for (Question q : expected) {
            repository.add(q);
        }
        QuestionService questionService = new JavaQuestionService(repository);

        Collection<Question> actual = questionService.getAll();
        int sizeActual = actual.size();

        Assertions.assertEquals(sizeExpected, sizeActual);
        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());
    }
}
