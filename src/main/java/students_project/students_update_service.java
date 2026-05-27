package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;


import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class students_update_service {
	// 사용할 변수 등록
		private final DataSource dataSource; 
		
		// 데이터 베이스 정보(beans_students.xml에 있는 datasorce 객체를 주입 받음)
		public students_update_service(DataSource dataSource) {
			this.dataSource = dataSource;
		}
		
		// 학생 정보 수정(Update)
				public String students_update(String name, String student_number) {
					// sql에 실제로 입력될 쿼리 작성
					String sql = "UPDATE students SET name = ? WHERE student_number = ?";
					// db와 연동하는 구현 로직
					try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
					        pstmt.setString(1, name);
					        pstmt.setString(2, student_number); 
					        // 업데이트 된 내용 int 값으로 담음 
					        int rows = pstmt.executeUpdate();     
					        if (rows > 0) {
					            return "수정 완료";
					        } else {
					            return "해당 학번을 찾을 수 없습니다.";
					        }
					} catch (Exception e) {
				        e.printStackTrace();
				        return "수정 실패: " + e.getMessage();
				    }
				}
}
