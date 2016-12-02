package Check;

public class CreditCheck {	//과목추가시 수강신청가능학점을 초과하는지 체크
	
	public static int creditCheck(String[][] totalData, int row, int selectedCredit, int maxCredit){
		String credit;
		credit=totalData[row][4].trim();
		selectedCredit=selectedCredit+Integer.parseInt(credit);
		if(selectedCredit>maxCredit){
			selectedCredit=selectedCredit-Integer.parseInt(credit);
			return 2;
		}
		selectedCredit=selectedCredit-Integer.parseInt(credit);
		return 1;
	}

}
