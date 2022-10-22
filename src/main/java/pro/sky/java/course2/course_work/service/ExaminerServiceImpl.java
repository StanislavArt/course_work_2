package pro.sky.java.course2.course_work.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.course_work.domain.Question;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
	private final QuestionService questionServiceJava;
	private final QuestionService questionServiceMath;
	
	public ExaminerServiceImpl (JavaQuestionService questionServiceJava, MathQuestionService questionServiceMath) {
		this.questionServiceJava = questionServiceJava;
		this.questionServiceMath = questionServiceMath;
	}
	
	/*
	Будут браться 50% вопросов от запрошенного количества из java-репозитория
	(но не более, чем имеется всего в репозитории),
	а остальное количество математических вопросов будет генерироваться на лету.
	*/
	public Collection<Question> getQuestions (int amount) {
		if (amount == 0) return null;
		
		Set<Question> questions = new HashSet<>();
		
		int counter = 0;	// общее количество полученных вопросов
		int required = amount / 2;
		int attempt = required * 2; // удвоим количество попыток для получения нужного количества случайных вопросов из java-репозитория, поскольку могут попадаться одинаковые вопросы
		
		while (attempt > 0 && counter < required) {
			Question question = questionServiceJava.getRandomQuestion();
			if (question == null) break;
			attempt--;
			if (questions.contains(question)) continue;
			questions.add(question);
			counter++;		
		}

		attempt = required * 2;
		// получаем оставшиеся вопросы по математике
		while (attempt > 0 && counter < amount) {
			Question question = questionServiceMath.getRandomQuestion();
			if (question == null) break;
			attempt--;
			if (questions.contains(question)) continue;
			questions.add(question);
			counter++;
		}

		return questions;
	}
}