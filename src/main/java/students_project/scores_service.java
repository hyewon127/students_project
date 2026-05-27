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
		String sql = "INSERT INTO scores VALUES(?,?,?,?,?,?,?, now())";
		
		// db와 연동하는 구현 로직
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 쿼리문 ? 값에 맞는 변수들 set 으로 입력받기
			pstmt.setString(1, dto.getStudent_number());
			pstmt.setInt(2, dto.getKorean());
			pstmt.setInt(3, dto.getEnglish());
			pstmt.setInt(4, dto.getMath());
			pstmt.setInt(5, dto.getScience());
			pstmt.setDouble(6, average);
			pstmt.setString(7, grade);
			// insert하는 executeUpdate 함수 사용 
			pstmt.executeUpdate();
			// 학생 평균 및 등급 알 수 있는 result 값 넣음 + 평균은 소수점 2개까지 표시 되게 함. 
			result = dto.getName() + " 학생 평균 성적 "+String.format("%.2f", average)+"점, 성적 등급 "+grade;
		} catch (Exception e) {
			// DB 조회 에러 발생할 경우 확인하는 catch 로직 (getmessage: 에러 핵심 메세지, printStackTrace: 에러 위치 추적)
			result = "DB 조회 에러 발생: " + e.getMessage();
			e.printStackTrace();
		}
		//화면에 보낼 값 
		return result; 
	}
}
