package pro.sky.java.course2.course_work.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.course_work.domain.Question;
import pro.sky.java.course2.course_work.repository.MathQuestionRepository;
import pro.sky.java.course2.course_work.repository.QuestionRepository;

import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private final QuestionRepository questionRepository;
    private final Random random;

    public MathQuestionService(MathQuestionRepository questionRepository) {
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
        // генерация вопроса на лету
        Integer argument1 = random.nextInt(1000);
        Integer argument2 = random.nextInt(1000);
        int operation = random.nextInt(3);

        Question result;
        switch(operation) {
            case 0:  result = new Question(argument1.toString() + " + " + argument2.toString() + " =", Integer.toString(argument1 + argument2)); break;
            case 1:  result = new Question(argument1.toString() + " - " + argument2.toString() + " =", Integer.toString(argument1 - argument2)); break;
            case 2:  result = new Question(argument1.toString() + " * " + argument2.toString() + " =", Integer.toString(argument1 * argument2)); break;
            default: throw new RuntimeException("Unknown operation");
        }
        return result;
    }

}
