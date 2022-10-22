package pro.sky.java.course2.course_work.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.repository.JavaQuestionRepository;
import pro.sky.java.course2.course_work.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;
import java.util.Set;

@Service
public class JavaQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random;

    public JavaQuestionService(JavaQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
        this.random = new Random(System.currentTimeMillis());
    }

    public Question add (String question, String answer) {
        return questionRepository.add (question, answer);
    }

    public Question add (Question question) {
        return questionRepository.add (question);
    }

    public Question remove (Question question) {
        return questionRepository.remove (question);
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    public Question getRandomQuestion() {
        Collection<Question> questions = getAll();
        int quantity = questions.size();
        if (quantity == 0) return null;

        Question[] array = questions.toArray(Question[]::new);
        int randomIndex = random.nextInt(quantity);
        return array[randomIndex];
    }

}
