-- 학생 이름, 학번을 담을 테이블 생성
create table students (
	name varchar(10),
	student_number varchar(20)
)

-- 학생 성적을 담을 테이블 생성
create table scores(
	korean int,
	english int,
	math int,
	average double,
	grade varchar(2)
)

-- 잘 만들었는지 확인
select *
from scores 

-- 잘 만들었는지 확인
select *
from students s 


