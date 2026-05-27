package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class delete_service {
	private final DataSource dataSource;
	// 데이터 베이스 정보(beans_students.xml에 있는 datasorce 객체를 주입 받음)
	public delete_service(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//성적 정보 삭제(delete) 쿼리
	public String scores_delete(String Student_number) {
		// scores 작성한 학번 입력 받아 해당 데이터 삭제하는 쿼리문
		String sql = "DELETE FROM students WHERE student_number = ?";
		// db와 연동하는 구현 로직
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			// 쿼리문 ? 맞는 변수 값 set 으로 가져오기 
			pstmt.setString(1, Student_number);
				//pstmt 로 인해 업데이터(영향) 받는 개수를 int 값으로 반환하기에 affected_rows 에 담음
	            int affected_rows = pstmt.executeUpdate();
	            // 변환된 값이 0이상으로 있으면 return(삭제완료), 없으면 (데이터 없음)
	            if (affected_rows > 0) {
	                return "성적 정보가 삭제 되었습니다.";
	            } else {
	                return "삭제할 데이터가 없습니다.";
	            }
	        } catch (Exception e) {
	            return "삭제 중 에러 발생: " + e.getMessage();
	        }
	}
}
