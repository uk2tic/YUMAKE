package Data;

public class StudentInformation {	//학적정보

	private String name;	//이름
	private String dept;	//학과
	private int grade;		//학년
	private int semester;	//학기
	private int applicationC;	//신청가능학점
	private int saveRow;
	
	public int getSaveRow() {
		return saveRow;
	}

	public void setSaveRow(int saveRow) {
		this.saveRow = saveRow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getApplicationC() {
		return applicationC;
	}

	public void setApplicationC(int applicationC) {
		this.applicationC = applicationC;
	}

}
