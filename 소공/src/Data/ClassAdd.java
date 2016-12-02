
package Data;

import javax.swing.table.DefaultTableModel;

public class ClassAdd {

	public void add(String[][] totalData, String[][] selectedData, int[][] classTimeData, int[][] selectedTimeData, int row, int selectedDataRow) {
		//totalData, classHms 중 선택된 데이터를 selectedData, selectedHms에 넣어주는 메소드 
		selectedData[selectedDataRow] = totalData[row].clone();
		selectedTimeData[selectedDataRow]=classTimeData[row].clone();
	}
	
	public void setSelectedTable(DefaultTableModel selectedModel, String[] selectedData){	
		selectedModel.addRow(selectedData);
	}
}
