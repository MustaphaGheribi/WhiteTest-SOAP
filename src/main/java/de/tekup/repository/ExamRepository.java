package de.tekup.repository;

import de.tekup.soap.models.whitetest.Exam;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class ExamRepository {
	private static final Map<String, Exam> exams = new HashMap<>();

	@PostConstruct
	public void init() {
		Exam examOCA = new Exam();
		examOCA.setCode("100");
		examOCA.setName("Oracle Certified Associate");
		Exam examOCP = new Exam();
		examOCP.setCode("101");
		examOCP.setName("Oracle Certified Professional");
		exams.put(examOCA.getCode(), examOCA);
		exams.put(examOCP.getCode(), examOCP);
	}

	public Optional<Exam> findByCode(String code) {
		return Optional.ofNullable(exams.get(code));
	}


	public List<Exam> findAll() {
		return new ArrayList<>(exams.values());
	}
}
