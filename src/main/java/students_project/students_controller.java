package students_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class students_controller {
	// 학생 정보 서비스, 성적 서비스 autowired 로 가져오기
	@Autowired
	private students_service students_service;
	
	@Autowired
	private scores_service scores_service; 
	
	@Autowired
	private delete_service delete_service;  
	
	//post 학생 정보 및 성적 입력(get)
	@GetMapping(value = "/students")
	public String studentsForm() {
	    return "student_view"; // 입력창이 있는 JSP 이름
	}
	
	// 데이터를 보냈을 때 처리하는 역할(post)
	@PostMapping(value = "/students")
	// dto(박스) 를 만들어서 가져올 변수를 모두 매개변수로 넣음. 
	public String students(student_request_DTO dto, Model model) {
		students_service.students_insert(dto.getName(),dto.getStudent_number()); 
		String scoreResult = scores_service.scores_insert(dto);
		model.addAttribute("msg", scoreResult);
		
		//jsp 파일를 화면에 띄움
		return "student_view";
	}
	
	@PostMapping(value = "/delete")
	public String delete(String Student_number, Model model) {
		// 삭제 서비스 호출
		String result = delete_service.scores_delete(Student_number);
		model.addAttribute("delete", result);
		//jps 파일 
		return "student_view";
	}
	
}	

