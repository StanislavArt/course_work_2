package pro.sky.java.course2.course_work.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.course_work.domain.Question;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class MathQuestionRepository implements QuestionRepository{
    private Set<Question> questions;

    public MathQuestionRepository() {
        questions = new HashSet<>();
    };

    @PostConstruct
    private void init() {
        questions.addAll(List.of(
            new Question("7 + 5 = ?", "12"),
            new Question("120 / 2 = ?", "60"),
            new Question("3 - 5 = ?", "-2"),
            new Question("7 * 5 = ?", "35")
        ));
    }

    public Question add (String questionString, String answerString) {
        if (questionString == null || answerString == null) {
            throw new IllegalArgumentException("Один из аргументов равен null");
        }
        Question question = new Question (questionString, answerString);
        boolean result = questions.add(question);
        if (result) return question;
        else return null;
    }

    public Question add (Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Аргумент равен null");
        }
        boolean result = questions.add(question);
        if (result) return question;
        else return null;
    }

    public Question remove (Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Аргумент равен null");
        }
        boolean result = questions.remove(question);
        if (result) return question;
        else return null;
    }

    public Collection<Question> getAll() {
        Set<Question> result = new HashSet<>(questions);
        return result;
    }
}
