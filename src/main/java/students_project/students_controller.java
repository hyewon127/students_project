package students_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class students_controller {
	// 학생 정보 서비스, 성적 서비스 autowired 로 가져오기
	@Autowired private students_service students_service;
	
	@Autowired private scores_service scores_service; 
	
	@Autowired private delete_service delete_service;  
	
	@Autowired private students_update_service students_update_service;
	
	// 1-1. 등록 페이지(입력 폼)만 보여주는 GET 메서드
    @GetMapping(value = "/students")
    public String showForm() {
        return "student_view"; // 입력 폼 JSP로 이동
    }

    // 1-2. 실제 데이터를 저장하는 POST 메서드
    @PostMapping(value = "/students")
    public String insertData(student_request_DTO dto, Model model) {
        String msg = students_service.students_insert(dto.getName(), dto.getStudentNumber());
        scores_service.scores_insert(dto);
        model.addAttribute("msg", msg);
        model.addAttribute("student", dto); // dto 객체를 통째로 넘김 
        return "result_view"; 
    }

    // 2. 관리 페이지 진입 (목록 조회)
    @GetMapping(value = "/manage")
    public String manageForm(Model model) {
        // students_service에 getAllStudents() 메서드를 추가해야 합니다 (아래 설명 참조)
        model.addAttribute("list", students_service.getAllStudents());
        return "manage_view";
    }

    // 3. 삭제 처리 (자동으로 학번 전달받음)
    @PostMapping(value = "/delete")
    public String delete(@RequestParam("studentNumber") String studentNumber, Model model) {
        // 여기서 studentNumber는 JSP에서 넘어온 해당 행의 학번입니다.
        model.addAttribute("delete", delete_service.scores_delete(studentNumber));
        model.addAttribute("list", students_service.getAllStudents());
        return "manage_view";
    }
    
    // 4. 수정 처리 (관리 페이지에서 호출)
    @PostMapping(value = "/update")
    public String update(@RequestParam("name") String name, @RequestParam("studentNumber") String studentNumber, Model model) {
        model.addAttribute("update", students_update_service.students_update(name, studentNumber));
        model.addAttribute("list", students_service.getAllStudents());
        return "manage_view";
    }
}
