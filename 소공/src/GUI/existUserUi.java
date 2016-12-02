package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.GetData;
import Data.GradeData;
import Data.StudentInformation;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class existUserUi extends JFrame implements ActionListener {

	// 기존에 사용한 학생일 경우 뜨는 UI, 수강가능학점만 입력받은 후 mainUI 호출

	private StudentInformation sInformation;
	private JPanel contentPane;
	private JTextField textApplicationC;
	private JButton startButton;
	private static String[] loginData;
	private static int rowCount;
	private static File file; // 학적정보파일

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					GetData getData=new GetData();
					
					// fileSelect();
					file = FileSelect.fileSelect();
					rowCount=getData.count(1, file);
					//rowCount = GetData.count(1, file);
					loginData = new String[4];
					existUserUi frame = new existUserUi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public existUserUi() {

		sInformation = new StudentInformation();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 203, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			Workbook wb = null;
			wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			for (int i = 0; i < 4; i++) {
				Cell cell = sheet.getCell(i, 0);
				if (cell == null)
					continue;
				loginData[i] = cell.getContents();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		JLabel lblNewLabel = new JLabel("신청가능학점 : ");
		lblNewLabel.setBounds(12, 21, 91, 21);
		contentPane.add(lblNewLabel);

		textApplicationC = new JTextField();
		textApplicationC.setBounds(96, 21, 77, 21);
		contentPane.add(textApplicationC);
		textApplicationC.setColumns(10);

		startButton = new JButton("시간표 만들기");
		startButton.setBounds(12, 62, 161, 45);
		contentPane.add(startButton);
		startButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startButton) {
			
			GradeData gradeData=new GradeData();
			
			sInformation.setApplicationC(Integer.parseInt(textApplicationC.getText()));
			
			sInformation.setName(loginData[0]);
			sInformation.setDept(loginData[1]);
			
			int grade=gradeData.gradeDataRenew(loginData[2], loginData[3], 1);
			int semester=gradeData.gradeDataRenew(loginData[2], loginData[3], 2);
			
//			sInformation.setGrade(Integer.parseInt(loginData[2]));
//			sInformation.setSemester(Integer.parseInt(loginData[3]));		
			
			sInformation.setGrade(grade);
			sInformation.setSemester(semester);	
			
			sInformation.setSaveRow(rowCount);

			MainUi.main(sInformation);
			this.dispose();
		}

	}
}