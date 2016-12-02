package Image;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MakePanel {
	

	public void makeClassPanel(JPanel timeTablePanel, GridBagConstraints gbc, int day, int startTime, int classClock, String className, Color color) {
		//선택과목의 panel을 위치와 시간에 따른 크기를 받아 색칠
		gbc.gridx = day;
		gbc.gridy = startTime+1;
		gbc.gridwidth = 1;
		gbc.gridheight = classClock;
		
		System.out.println("startTime :"+startTime);
		
		JPanel classPanel = new JPanel(new GridBagLayout());
		JLabel classLabel = new JLabel();
		createSquareJPanel(classPanel, classLabel, color, className);
		timeTablePanel.add(classPanel, gbc);
	}

	public static void createSquareJPanel(JPanel classPanel, JLabel classLabel, Color color, String name) {
		//각각 칸에 해당하는 panel을 생성
		classPanel.setOpaque(true);
		classPanel.setBackground(color);
		classPanel.setPreferredSize(new Dimension(30, 10));
		classPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
		classLabel = new JLabel(name, JLabel.CENTER);
		classPanel.add(classLabel);
	}

	public void backGroundPanel(JPanel timeTablePanel, GridBagConstraints gbc, JPanel classPanel,
			JLabel classLabel) {
		
		//TimeTable의 기본 Panel틀을 생성

		String[] Day = new String[] { " ", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		String[] Time = new String[] {"9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
				"13:00", "13:30","14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
				"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30","24:00" };

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 29; j++) {
				if (i == 0) {
					gbc.gridx = i;
					gbc.gridy = j;
					if (j == 0) {
						createSquareJPanel(classPanel, classLabel, Color.gray, " ");
						timeTablePanel.add(classPanel, gbc);
					}
					else{
					classPanel = new JPanel(new GridBagLayout());
					createSquareJPanel(classPanel, classLabel, Color.gray, Time[j-1] + "-" + Time[j]);
					timeTablePanel.add(classPanel, gbc);
					}
				} else if (j == 0) {
					gbc.gridx = i;
					gbc.gridy = j;
					classPanel = new JPanel(new GridBagLayout());
					createSquareJPanel(classPanel, classLabel, Color.gray, Day[i]);
					timeTablePanel.add(classPanel, gbc);
				} else {
					gbc.gridx = i;
					gbc.gridy = j;
					classPanel = new JPanel(new GridBagLayout());
					createSquareJPanel(classPanel, classLabel, Color.white, "   ");
					timeTablePanel.add(classPanel, gbc);
				}
			}
		}

	}

}