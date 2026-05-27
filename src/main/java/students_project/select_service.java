package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

@Service
public class select_service {
	private final DataSource dataSource;

	// 데이터 베이스 정보(beans_students.xml에 있는 datasorce 객체를 주입 받음)
	public select_service(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	//성적 조회(select) 쿼리
	public Map<String, Object> scores_select(String Student_number) {
		// map 제네릭 명시
	    Map<String, Object> map = new HashMap<String, Object>();
		
		// sql에 실제로 입력될 쿼리 작성
		String sql = "select s.name, s2.* from students s join scores s2 on s.student_number = s2.student_number where s.student_number = ? ";
		
		//db와 연동함.
		try (Connection conn = dataSource.getConnection();PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        // ? 값 set 으로 받아옴
		        pstmt.setString(1, Student_number);
		        // db 조회한 값 resultset 에 담기
		        try (ResultSet rs = pstmt.executeQuery()) {
		            if (rs.next()) {
		                map.put("status", "success"); // status 결과값 받기
		                map.put("name", rs.getString("name"));
		                map.put("student_number", rs.getString("student_number"));
		                map.put("korean", rs.getInt("korean"));
		                map.put("english", rs.getInt("english"));
		                map.put("math", rs.getInt("math"));
		                map.put("science", rs.getInt("science"));
		                map.put("average", rs.getDouble("average"));
		                map.put("grade", rs.getString("grade"));
		            } else {
		                map.put("status", "fail");
		                map.put("message", "해당 학번의 데이터가 없습니다.");
		            }
		        }
		    } catch (Exception e) { 
		        e.printStackTrace();
		        map.put("status", "error");
		        map.put("message", "조회 중 오류가 발생했습니다: " + e.getMessage());
		    }
		    return map;
		
		}
	
}