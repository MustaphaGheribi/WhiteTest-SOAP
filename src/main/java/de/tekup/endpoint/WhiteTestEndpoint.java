package de.tekup.endpoint;

import de.tekup.service.WhiteTestService;
import de.tekup.soap.models.whitetest.ExamResponse;
import de.tekup.soap.models.whitetest.StudentRequest;
import de.tekup.soap.models.whitetest.WhiteTestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class WhiteTestEndpoint {
	private final static String NAME_SPACE = "http://www.tekup.de/soap/models/whitetest";

	@Autowired
	private WhiteTestService whiteTestService;


	@PayloadRoot(namespace = NAME_SPACE, localPart = "StudentRequest")
	@ResponsePayload
	public WhiteTestResponse reserve(@RequestPayload StudentRequest request) {
		return whiteTestService.reserve(request);
	}

	@PayloadRoot(namespace = NAME_SPACE, localPart = "AllExam")
	@ResponsePayload
	public ExamResponse getAllExam() {
		ExamResponse examResponse = new ExamResponse();
		examResponse.getExam().addAll(whiteTestService.getAllExams());
		return examResponse;
	}
}
