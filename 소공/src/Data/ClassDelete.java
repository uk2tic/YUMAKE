package Data;

import GUI.MainUi;

public class ClassDelete{

	public void deleteSelectedData(String[][] selectedData, int row, int selectedDataCol, int colCount) {
		//선택된 과목의 데이터를 delete selectedData
		for (int n = row; n < selectedDataCol; n++) {
			for (int m = 0; m < colCount; m++) {

				if (n == selectedDataCol - 1) {

					selectedData[n][m] = null;
				} else
					selectedData[n][m] = selectedData[n + 1][m];
			}
		}
	}
	
	
	public void deleteClassTimeData(int row, int selectedDataRow, int colCount, int[][] selectedTimeData) {
		//선택된 과목의 데이터를 delete selectedHms
		for (int n = row; n < selectedDataRow; n++) {
			for (int m = 0; m < 6; m++) {

				if (n == selectedDataRow - 1) {

					selectedTimeData[n][m] = 0;
				} else
					selectedTimeData[n][m] = selectedTimeData[n + 1][m];
			}
		}
	}
	
}
