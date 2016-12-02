package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.StudentInformation;

public class NewUserUi extends JFrame implements ActionListener {
	
	//새내기 선택하였을경우 뜨는 UI, 각각 학적정보입력 받은 후 mainUI 호출
	
	private StudentInformation sInformation;
	private JPanel contentPane;
	private JTextField textMajorC;
	private JTextField textLiveralC;
	private JTextField textName;
	private JTextField textDept;
	private JTextField textGrade;
	private JTextField textApplicationC;
	private JTextField textSemester;
	private JButton startButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUserUi frame = new NewUserUi();
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
	public NewUserUi() {
		sInformation = new StudentInformation();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 231, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("이름: ");
		lblNewLabel.setBounds(12, 10, 40, 21);
		contentPane.add(lblNewLabel);
		
		textName = new JTextField();	//이름
		textName.setBounds(47, 10, 156, 21);
		contentPane.add(textName);
		textName.setColumns(10);
		

		JLabel lblNewLabel_1 = new JLabel("학과: ");
		lblNewLabel_1.setBounds(12, 41, 40, 21);
		contentPane.add(lblNewLabel_1);
		
		textDept = new JTextField();	//학과
		textDept.setBounds(47, 41, 156, 21);
		contentPane.add(textDept);
		textDept.setColumns(10);
				

		JLabel lblNewLabel_2 = new JLabel("수강학기 : ");
		lblNewLabel_2.setBounds(12, 72, 65, 21);
		contentPane.add(lblNewLabel_2);
		
		textGrade = new JTextField();	//학년
		textGrade.setBounds(73, 72, 20, 21);
		contentPane.add(textGrade);
		textGrade.setColumns(10);
		
		textSemester = new JTextField();	//학기
		textSemester.setColumns(10);
		textSemester.setBounds(131, 72, 20, 21);
		contentPane.add(textSemester);
		
		
		JLabel lblNewLabel_6 = new JLabel("신청가능학점 : ");
		lblNewLabel_6.setBounds(12, 103, 89, 21);
		contentPane.add(lblNewLabel_6);

		textApplicationC = new JTextField();	//신청가능학점
		textApplicationC.setBounds(100, 103, 105, 21);
		contentPane.add(textApplicationC);
		textApplicationC.setColumns(10);
		
		
		JLabel label_1 = new JLabel("\uD559\uB144");
		label_1.setBounds(100, 72, 30, 21);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uD559\uAE30");
		label_2.setBounds(160, 72, 30, 21);
		contentPane.add(label_2);
		
		startButton = new JButton("시간표만들기");
		startButton.setBounds(12, 138, 191, 43);
		contentPane.add(startButton);
		startButton.addActionListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == startButton) {
			
			sInformation.setApplicationC(Integer.parseInt(textApplicationC.getText()));	//신청가능학점
			
			sInformation.setName(textName.getText());//이름
			sInformation.setDept(textDept.getText());	//학과
			sInformation.setGrade(Integer.parseInt(textGrade.getText()));	//학년
			sInformation.setSemester(Integer.parseInt(textSemester.getText()));	//학기
			
			sInformation.setSaveRow(1);
			
			MainUi.main(sInformation);
			this.dispose();
		}
	}
}