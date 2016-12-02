package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Check.OverlapCheck;
import Data.MyClass;

public class MyClassUi extends MainUi implements MouseListener, ActionListener {

	private JFrame myclassFrame;
	private JTextField myTimeField;
	private JComboBox dayCb;
	private JComboBox startTimeCb;
	private JComboBox finishTimeCb;

	private String[] Time = new String[] { "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
			"13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30",
			"19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30", "24:00" };

	private JButton addButton;

	private MyClass myClass = new MyClass();

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyClassUi myclassUi = new MyClassUi();
					myclassUi.myclassFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyClassUi() {

		myclassFrame = new JFrame();
		myclassFrame.setTitle("YU MAKE");
		myclassFrame.setBounds(100, 100, 300, 270);
		myclassFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myclassFrame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("My Time : ");
		lblNewLabel.setBounds(17, 15, 93, 21);
		myclassFrame.add(lblNewLabel);

		myTimeField = new JTextField();
		myTimeField.setBounds(106, 12, 156, 27);
		myclassFrame.add(myTimeField);
		myTimeField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("요일 : ");
		lblNewLabel_1.setBounds(17, 51, 60, 21);
		myclassFrame.add(lblNewLabel_1);

		dayCb = new JComboBox();
		dayCb.setBounds(76, 48, 186, 27);
		myclassFrame.add(dayCb);
		dayCb.addItem("월요일");
		dayCb.addItem("화요일");
		dayCb.addItem("수요일");
		dayCb.addItem("목요일");
		dayCb.addItem("금요일");
		dayCb.addItem("토요일");

		JLabel lblNewLabel_2 = new JLabel("시작시간 : ");
		lblNewLabel_2.setBounds(17, 87, 93, 21);
		myclassFrame.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("종료시간 : ");
		lblNewLabel_3.setBounds(17, 123, 93, 21);
		myclassFrame.add(lblNewLabel_3);

		startTimeCb = new JComboBox();
		startTimeCb.setBounds(106, 84, 156, 27);
		myclassFrame.add(startTimeCb);

		for (int i = 0; i < 31; i++) {
			startTimeCb.addItem(Time[i]);
		}

		finishTimeCb = new JComboBox();
		finishTimeCb.setBounds(106, 120, 156, 27);
		myclassFrame.add(finishTimeCb);

		for (int i = 0; i < 31; i++) {
			finishTimeCb.addItem(Time[i]);
		}

		addButton = new JButton("추가");
		addButton.setBounds(17, 157, 245, 43);
		myclassFrame.add(addButton);
		addButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addButton) {
			if (OverlapCheck.myTimeOverlapCheck(selectedTimeData, selectedDataRow, dayCb.getSelectedIndex()+1, startTimeCb.getSelectedIndex(), finishTimeCb.getSelectedIndex()) == 2) {
				JOptionPane.showMessageDialog(null, "시간 중복입니다.");
			} else {
				myClass.add(selectedData, selectedDataRow, myTimeField.getText(), dayCb.getSelectedItem(),
						startTimeCb.getSelectedItem(), finishTimeCb.getSelectedItem());
				myClass.addTimeData(selectedTimeData, selectedDataRow, dayCb.getSelectedIndex() + 1,
						startTimeCb.getSelectedIndex(), finishTimeCb.getSelectedIndex());
				add.setSelectedTable(selectedModel, selectedData[selectedDataRow]);
				selectedDataRow++;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}
