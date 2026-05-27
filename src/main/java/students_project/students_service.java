package students_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public String students_insert(String name, String studentNumber) {
	    // 1. 여기서 변수를 명확하게 선언합니다.
	    String checkSql = "SELECT count(*) FROM students WHERE student_number = ?";
	    String insertSql = "INSERT INTO students(name, student_number) VALUES(?,?)";
	    
	    // 2. DB 연결
	    try (Connection conn = dataSource.getConnection()) {
	        
	        // 3. 중복 체크 (먼저 선언한 checkSql 변수 사용)
	        try (PreparedStatement pstmtCheck = conn.prepareStatement(checkSql)) {
	            pstmtCheck.setString(1, studentNumber);
	            ResultSet rs = pstmtCheck.executeQuery();
	            
	            if (rs.next()) {
	                int count = rs.getInt(1);
	                if (count > 0) {
	                    return "에러: 이미 존재하는 학번(" + studentNumber + ")입니다.";
	                }
	            }
	        }
	        
	        // 4. 데이터 삽입 (선언한 insertSql 변수 사용)
	        try (PreparedStatement pstmtInsert = conn.prepareStatement(insertSql)) {
	            pstmtInsert.setString(1, name);
	            pstmtInsert.setString(2, studentNumber);
	            pstmtInsert.executeUpdate();
	        }
	        
	        return "학생 정보 (" + name + ", " + studentNumber + ") 입력 완료";
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "DB 에러 발생: " + e.getMessage();
	    }
	}
		
		// 학생 목록 조회를 위한 결과값 list 담는 로직
		public List<Map<String, String>> getAllStudents() {
		    List<Map<String, String>> list = new ArrayList<>(); 
		    // sql에 실제로 입력되는 쿼리 
		    String sql = "SELECT * FROM students";
		    // db 연동
		    try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);
		    	// 결과값 담기
	    		ResultSet rs = pstmt.executeQuery()) {
		    	// 조회값 map 에 담아서 list 에 추가
		    	while (rs.next()) {
		            Map<String, String> map = new HashMap<>();
		            map.put("name", rs.getString("name"));
		            map.put("student_number", rs.getString("student_number"));
		            list.add(map);
		        }
		    } catch (Exception e) { 
		    	e.printStackTrace();
		    }
		    return list;
		}	
	
}
