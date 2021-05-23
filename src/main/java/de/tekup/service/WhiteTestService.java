package de.tekup.service;

import de.tekup.repository.ExamRepository;
import de.tekup.repository.StudentRepository;
import de.tekup.soap.models.whitetest.Exam;
import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;
import org.springframework.stereotype.Service;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WhiteTestService {
	private final StudentRepository studentRepository;
	private final ExamRepository examRepository;

	public WhiteTestService(StudentRepository studentRepository, ExamRepository examRepository) {
		this.studentRepository = studentRepository;
		this.examRepository = examRepository;
	}

	public WhiteTestResponse reserve(StudentRequest request) {

		WhiteTestResponse response = new WhiteTestResponse();
		Optional<Student> optionalStudent = studentRepository.findById(request.getStudentId());
		Optional<Exam> optionalExam = examRepository.findByCode(request.getExamCode());
		if (request.getStudentId() < 0)
			response.getBadRequests().add("Student ID is invalid");

		if (!optionalStudent.isPresent()) {
			response.getBadRequests().add("Student Is not found");
		}

		if (!optionalExam.isPresent()) {
			response.getBadRequests().add("Exam Is not found");
		}

		try {
			Integer.parseInt(request.getExamCode());
		} catch (NumberFormatException e) {
			response.getBadRequests().add("Exam Code not valid");
		}

		if (response.getBadRequests().isEmpty()) {

			response.setExam(optionalExam.get());
			response.setStudent(optionalStudent.get());

			LocalDateTime localDateTime = LocalDateTime.now();

			XMLGregorianCalendar xmlGregorianCalendar = null;
			try {
				String iso = localDateTime.toString();
				if (localDateTime.getSecond() == 0 && localDateTime.getNano() == 0) {
					iso += ":00";
				}
				xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(iso);
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}

			response.setDate(xmlGregorianCalendar);
		}
		return response;
	}

	public List<Exam> getAllExams(){
		return examRepository.findAll();
	}

}
