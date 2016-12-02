package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class HelpUi extends JFrame {

	// JScrollPane scrollPane;
	ImageIcon icon;

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpUi frame = new HelpUi();
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
	public HelpUi() {

		setTitle("도움말");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 489, 43);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("도움말");
		panel_1.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(0, 0, 0));

		/*-----------------------------도움말-----------------*/
		/*-----------------------------탭-----------------*/
		JTabbedPane tabpane = new JTabbedPane();
		tabpane.setBounds(0, 42, 500, 220);
		contentPane.add(tabpane);

		icon = new ImageIcon("img/캡처.png"); // ("폴더이름/이미지이름.이미지확장자")

		JPanel p1 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height - 20, null);// ㅇㅇ

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		JPanel p2 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height - 20, null);// ㅇㅇ

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		JPanel p3 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height - 20, null);// ㅇㅇ

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		JPanel p4 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height - 20, null);// ㅇㅇ

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		JPanel p5 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height - 20, null);// ㅇㅇ

				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		tabpane.addTab("1.파일 열기", p1);
		p1.setLayout(null);
		textField = new JTextField("시간표 파일을 불러온다.");
		textField.setBounds(0, 170, 490, 20);
		p1.add(textField);
		textField.setColumns(10);

		tabpane.addTab("2.과목 추가/삭제", p2);
		p2.setLayout(null);
		textField_1 = new JTextField("수강하고자 하는 과목을 추가하거나 삭제한다");
		textField_1.setBounds(0, 170, 490, 20);
		p2.add(textField_1);
		textField_1.setColumns(10);

		tabpane.addTab("3.시간표 생성", p3);
		p3.setLayout(null);
		textField_1 = new JTextField("시간표 모양으로 만들어준다");
		textField_1.setBounds(0, 170, 490, 20);
		p3.add(textField_1);
		textField_1.setColumns(10);

		tabpane.addTab("4.시간표 저장", p4);
		p4.setLayout(null);
		textField_1 = new JTextField("image file로 저장한다");
		textField_1.setBounds(0, 170, 490, 20);
		p4.add(textField_1);
		textField_1.setColumns(10);
		tabpane.addTab("5.초기화", p5);
		
		p5.setLayout(null);
		textField_1 = new JTextField("선택한 과목들과 완성된 시간표를 다 삭제한다");
		textField_1.setBounds(0, 170, 490, 20);
		p5.add(textField_1);
		textField_1.setColumns(10);
		setVisible(true);

		// Approach 2: Scale image to size of component[접근]
		// g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
		// Approach 3: Fix the image position in the scroll pane[이미지고정]
		// Point p = scrollPane.getViewport().getViewPosition();
		// g.drawImage(icon.getImage(), p.x, p.y, null);
	}
}
