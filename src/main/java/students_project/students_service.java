package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class students_service {
	// 사용할 변수 등록
	private final DataSource dataSource; 
	
	// 데이터 베이스 정보(beans_students.xml에 있는 datasorce 객체를 주입 받음)
	public students_service(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	// 학생 정보 입력 로직(create) 
		public String students_insert(String name, String student_number) {
			String result = "";
			// sql에 실제로 입력될 쿼리 작성
			String sql = "INSERT INTO students(name, student_number) VALUES(?,?)";
			// db와 연동하는 구현 로직
			try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				// ?,? 에 맞는 변수 입력
				pstmt.setString(1, name);
				pstmt.setString(2, student_number);
				// insert하는 executeUpdate 함수 사용 
				pstmt.executeUpdate();
				// 결과 확인을 위해 빈 변수에 학생 정보가 어떤게 들어갔는지 알 수 있는 내용 입력
				result = "학생 정보 ("+name+", "+student_number+")입력 완료";
			} catch (Exception e) {
				// DB 조회 에러 발생할 경우 확인하는 catch 로직 (getmessage: 에러 핵심 메세지, printStackTrace: 에러 위치 추적)
				result = "DB 조회 에러 발생: " + e.getMessage();
				e.printStackTrace();
			}
			//화면에 보낼 값 
			return result; 
		}
}
