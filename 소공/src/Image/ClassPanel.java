package Image;

import java.awt.Color;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

public class ClassPanel {

	//private static MakePanel makePanel=new MakePanel();
	//선택된 과목의 정보를 받아 TimeTable에 들어갈 모양의 Panel을 생성
//	private static Color color[] = { Color.yellow, Color.blue, Color.red, Color.green, Color.pink, Color.gray,
//			Color.orange};	
	

	public void insertClassPanel(JPanel timeTablePanel, GridBagConstraints gbc, int selectedDataRow,
			int[][] selectedTimeData, String[][] selectedData) {
		
		MakePanel makePanel=new MakePanel();
		RandomColor rColor=new RandomColor();
		
		
		for (int i = 0; i < selectedDataRow; i++) {
			Color color=rColor.randomColor();
			
			makePanel.makeClassPanel(timeTablePanel, gbc, selectedTimeData[i][0], selectedTimeData[i][1], selectedTimeData[i][2],
					selectedData[i][3], color);
			
			if (selectedTimeData[i][3] == 0) {

			}

			else{
				makePanel.makeClassPanel(timeTablePanel, gbc, selectedTimeData[i][3], selectedTimeData[i][4], selectedTimeData[i][5],
						selectedData[i][3], color);
				}
		}
	}
}
