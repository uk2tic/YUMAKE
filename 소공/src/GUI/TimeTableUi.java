package GUI;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Image.ClassPanel;
import Image.MakePanel;

public class TimeTableUi{
	
	//시간표만들어 주는 클래스

	protected static JPanel timeTablePanel;
	protected static JFrame timeTableFrame;
	protected static GridBagConstraints gbc;

	
	public static void getTimeTable(int selectedDataCol, int[][] selectedTimeData, String[][] selectedData){

		MakePanel makePanel=new MakePanel();
		ClassPanel classPanel=new ClassPanel();
		
		timeTableFrame = new JFrame();
		timeTableFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		timeTablePanel = new JPanel();
		timeTablePanel.setLayout(new GridBagLayout());

		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;

		JPanel basePanel = new JPanel(new GridBagLayout());
		JLabel baseLabel = new JLabel();

		classPanel.insertClassPanel(timeTablePanel, gbc, selectedDataCol, selectedTimeData, selectedData);

		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;

		makePanel.backGroundPanel(timeTablePanel, gbc, basePanel, baseLabel);
		
		
		
//		Image image=timeTablePanel.createImage(500, 500);
//		Graphics Gc;
//		Gc.drawImage(image,0,0,this);
		
		timeTableFrame.setContentPane(timeTablePanel);
		timeTableFrame.pack();
		timeTableFrame.setSize(500, 500);
		timeTableFrame.setLocationByPlatform(true);
		timeTableFrame.setVisible(true);
	}
}
