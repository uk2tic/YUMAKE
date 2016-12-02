package Data;

public class GradeData {
	
	public int gradeDataRenew(String sGrade, String sSemester, int i){
		int grade;
		int semester;
		
		sGrade=sGrade.trim();
		sSemester=sSemester.trim();
		grade=Integer.parseInt(sGrade);
		semester=Integer.parseInt(sSemester);
		
		if(semester==2){
			semester=1;
			grade=grade+1;
		}
		else
			semester=2;
		
		if(i==1){
			return grade;
		}
		else
			return semester;
	}
}
