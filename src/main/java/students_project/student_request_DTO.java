package students_project;

public class student_request_DTO {
    private String name;
    private String studentNumber; 
    private int korean;
    private int english;
    private int math;
    private int science;

 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getStudentNumber() { return studentNumber; }
    public void setStudentNumber(String studentNumber) { this.studentNumber = studentNumber; }
    
    public int getKorean() { return korean; }
    public void setKorean(int korean) { this.korean = korean; }
    
    public int getEnglish() { return english; }
    public void setEnglish(int english) { this.english = english; }
    
    public int getMath() { return math; }
    public void setMath(int math) { this.math = math; }
    
    public int getScience() { return science; }
    public void setScience(int science) { this.science = science; }
    
    // 평균을 계산하여 반환하는 메서드 추가
    public double getAverage() {
        return (korean + english + math + science) / 4.0;
    }
    
    // 등급을 반환하는 메서드 추가
    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else return "F";
    }
}