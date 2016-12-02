package Data;

public class CreditData {
	//수강학점관련클래스
	public int addCredit(String[][] selectedData, int selectedDataRow, int selectedCredit){
		//선택된 과목의 학점을 추가
		String credit=selectedData[selectedDataRow][4].trim();
		selectedCredit=selectedCredit+Integer.parseInt(credit);
		return selectedCredit;
	}
	
	public int deleteCredit(String[][] selectedData, int row, int selectedCredit){
		//선택된 과목의 학점을 삭제
		String credit=selectedData[row][4].trim();
		selectedCredit=selectedCredit-Integer.parseInt(credit);
		return selectedCredit;
	}
	
	public int resetCredit(int selectedCredit){
		//학점을 초기화
		selectedCredit=0;
		return selectedCredit;
	}
}