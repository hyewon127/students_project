-- 학생 이름, 학번을 담을 테이블 생성
CREATE TABLE students (
	-- scores 와 이어지는 외래키 지정함. 
    student_number varchar(20) PRIMARY KEY, 
    name VARCHAR(50)
);

-- 학생 성적을 담을 테이블 생성
create table scores(
	student_number varchar(20),
	korean INT,
	english INT,
    math INT,
    science INT,
    average DOUBLE,
    grade VARCHAR(2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    -- 학번을 외래키로 지정하고 학생이 삭제되면 성적도 자동 삭제 설정
    FOREIGN KEY (student_number) REFERENCES students(student_number) ON DELETE CASCADE
);

-- 잘 만들었는지 확인
select *
from scores;

-- 잘 만들었는지 확인
select *
from students s;



DROP TABLE IF EXISTS scores;
