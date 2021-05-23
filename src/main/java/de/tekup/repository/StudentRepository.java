package de.tekup.repository;

import de.tekup.soap.models.whitetest.Student;
import de.tekup.soap.models.whitetest.Student.Address;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class StudentRepository {
	private static final Map<Integer, Student> students = new HashMap<>();

	@PostConstruct
	public void init() {
		Address address1 = new Address();
		address1.setCity("Tunis");
		address1.setStreet("Abd waheb");
		address1.setCode("1008");

		Address address2 = new Address();
		address2.setCity("Bizerte");
		address2.setStreet("rue ali");
		address2.setCode("1005");

		Student student1 = new Student();
		student1.setId(1);
		student1.setName("Mustapha Gheribi");
		student1.setAddress(address1);

		Student student2 = new Student();
		student2.setId(2);
		student2.setName("Ali ben ali");
		student2.setAddress(address2);

		students.put(student1.getId(), student1);
		students.put(student2.getId(), student2);
	}

	public Optional<Student> findById(int id) {
		return Optional.ofNullable(students.get(id));
	}


}
