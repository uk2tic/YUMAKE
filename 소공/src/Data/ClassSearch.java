package Data;

public class ClassSearch {
	
	private static TimeData timeData=new TimeData();
	private static ChangeData change=new ChangeData();
	//private int rowCount;
	
//	public int getRowCount() {
//		return rowCount;
//	}
//
//	public void setSaveRow(int rowCount) {
//		this.rowCount = rowCount;
//	}
	
	public void professorSearch(int rowCount, int searchRow, String[][] totalData, String[][] searchData,int searchTimeData[][], int searchItime[][],
			String searchText) {
		//교수명으로 검색
		for (int i = 0; i < rowCount; i++) {
			if (totalData[i][5].equals(searchText)) {
				searchData[searchRow] = totalData[i].clone();
				searchRow++;
			}
		}
		searchItime=change.changeTimeData(searchRow, searchData);		
		searchTimeData = new int[searchRow][6];
		searchTimeData=timeData.makeTimeData(searchTimeData, searchItime, searchRow);
	}

	public void subjectSearch(int rowCount, int searchRow, String[][] totalData, String[][] searchData,int searchTimeData[][], int searchItime[][],
			String searchText) {
		//과목이름으로 검색
		for (int i = 0; i < rowCount; i++) {
			if (totalData[i][3].equals(searchText)) {
				searchData[searchRow] = totalData[i].clone();
				searchRow++;
			}
		}
		searchItime=change.changeTimeData(searchRow, searchData);		
		searchTimeData = new int[searchRow][6];
		searchTimeData=timeData.makeTimeData(searchTimeData, searchItime, searchRow);
	}
}
