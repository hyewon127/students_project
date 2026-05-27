package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class scores_service {
	
	private final DataSource dataSource;

	// 데이터 베이스 정보(beans_students.xml에 있는 datasorce 객체를 주입 받음)
	public scores_service(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 성적 정보 db 등록(insert)
	public String scores_insert(student_request_DTO dto) {
		String result = "";
		//성적 평균 평균 계산 
		double average = (dto.getKorean()+dto.getEnglish()+dto.getMath()+dto.getScience())/4.0;
		//국어 등급 계산(if-else) 
		String grade = "";
		if(average >= 90) {
			grade = "A";
		}else if(average >=80) {
			grade = "B";
		}else if(average >=70) {
			grade = "C";
		}else if(average >=60) {
			grade = "D";
		}else {
			grade = "F";	
		}
		// sql에 실제로 입력될 쿼리 작성
		String sql = "INSERT INTO scores(student_number, korean, english, math, science, average, grade, created_at) VALUES(?,?,?,?,?,?,?, now())";
		
		// db와 연동하는 구현 로직
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 쿼리문 ? 값에 맞는 변수들 set 으로 입력받기
			pstmt.setString(1, dto.getStudentNumber());
			pstmt.setInt(2, dto.getKorean());
			pstmt.setInt(3, dto.getEnglish());
			pstmt.setInt(4, dto.getMath());
			pstmt.setInt(5, dto.getScience());
			pstmt.setDouble(6, average);
			pstmt.setString(7, grade);
			// insert하는 executeUpdate 함수 사용 
			pstmt.executeUpdate();
			result = dto.getName() + " 학생의 평균 성적 " + String.format("%.2f", average) + "점, 등급 " + grade + "로 등록 완료";
		} catch (Exception e) {
			e.printStackTrace();
			// 에러 시 메시지 생성
	        result = "DB 저장 실패: " + e.getMessage();
		}
		//화면에 보낼 값 
		return result; 
	}
}
