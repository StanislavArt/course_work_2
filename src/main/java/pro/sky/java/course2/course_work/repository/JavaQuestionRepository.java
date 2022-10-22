package pro.sky.java.course2.course_work.repository;

import org.springframework.stereotype.Repository;
import pro.sky.java.course2.course_work.domain.Question;


import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository {
    private Set<Question> questions;

    public JavaQuestionRepository() {
        questions = new HashSet<>();
    };

    @PostConstruct
    private void init() {
        questions.addAll(List.of(
                new Question("Что такое полиморфизм?",
                        "Полиморфизм — это способность программы идентично использовать объекты с одинаковым интерфейсом без информации о конкретном типе этого объекта"),
                new Question("Что такое Local Variable?",
                        "Local variable — это переменная, которая определена внутри метода и существует вплоть до того момента, пока выполняется этот метод"),
                new Question("Что такое модификаторы доступа?",
                        "Модификаторы доступа — это инструмент, при помощи которого можно настроить доступ к классам, методам и переменным."),
                new Question("Что такое сигнатура метода?",
                        "Сигнатура метода — это набор из названия метода и аргументов, какие принимает метод"),
                new Question("Что такое перегрузка методов?",
                        "Перегрузка методов — это свойство полиморфизма, в котором при помощи изменения сигнатуры метода можно создать разные методы для одних действий"),
                new Question("Для чего в Java используются статические блоки инициализации?",
                        "Статические блоки инициализация используются для выполнения кода, который должен выполняться один раз при инициализации класса загрузчиком классов, в момент, предшествующий созданию объектов этого класса при помощи конструктора"),
                new Question("test_question", "test_answer")
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
