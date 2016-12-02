package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Check.CreditCheck;
import Check.OverlapCheck;
import Data.ClassSave;
import Data.CreditData;
import Data.ChangeData;
import Data.ClassAdd;
import Data.ClassDelete;
import Data.ClassReset;
import Data.GetData;
import Data.TimeData;
import Data.StudentInformation;

public class MainUi implements MouseListener, ActionListener {
	
	//메인화면
	private static StudentInformation sInformation; // 학적정보
	private int saveRow=0;
	private static File file; // 시간표파일
	private static File newFile;	//새로 불러오는 파일
	
	protected static String[][] totalData = null; // 시간표전체데이터
	protected static String[][] selectedData = null; // 예비과목데이터
	protected static int[][] classTimeData = null; // 전체과목변환데이터
	protected static int[][] selectedTimeData = null; // 예비과목변환데이터
	private static int[][] iTime = null; // int형 타임데이터
	private static int[][] newItime=null;

	private static JFrame frmYuMake;
	private JScrollPane totalScrollPane;
	private JScrollPane selectedScrollPane;
	private JTable totalTable;
	protected JTable selectedTable;

	protected static JLabel creditLabel;

	private DefaultTableModel tableModel; // 기본테이블모델
	protected static DefaultTableModel selectedModel; // 선택된과목테이블모델
	private String[] saTit = new String[] { "수강번호", "학년", "이수구분", "과목명", "학점", "담당교수", "강의시간", "강의실", "개설학과", "비고" };
	private JButton addButton;
	private JButton deleteButton;
	private JButton resetButton;
	private JButton makeTimeTableButton;
	private JButton searchButton;
	private JButton myclassAddButton;
	private int row;
	protected static int rowCount;
	protected static int colCount;
	protected static int selectedDataRow = 0;
	protected static int maxCredit = 0;
	protected static int selectedCredit = 0;
	
	private JMenuItem helpMenuItem;
	private JMenuItem informationMenuItem;
	private JMenuItem newFileOpenItem;
	private JMenuItem saveFileItem;
	
	private static GetData getData=new GetData();
	protected static CreditData credit=new CreditData();
	protected static TimeData timeData=new TimeData();
	protected static ChangeData change=new ChangeData();
	protected static ClassAdd add = new ClassAdd();
	

	public static void main(StudentInformation sInformation) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					file = FileSelect.fileSelect();

					rowCount=getData.count(1, file);
					colCount =getData.count(2, file);
					
					totalData=getData.getTotalDataSize(file, totalData);
					totalData=getData.getTotalData(file, totalData);
					classTimeData=getData.getClassTimeDataSize(file, classTimeData);
					
					selectedData=new String[50][colCount];
					selectedTimeData=new int[50][colCount];

					MainUi window = new MainUi(sInformation);
					window.frmYuMake.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainUi() {}

	public MainUi(StudentInformation sInformation) {		
		
		this.sInformation=sInformation;
		totalData = getData.getTotalData(file, totalData); // totalData에파일데이터읽어오기
		iTime=change.changeTimeData(rowCount, totalData);
		//iTime = ChangeData.changeTimeData(rowCount, totalData); // 수강시간토큰으로나눈데이터
		
		classTimeData=timeData.makeTimeData(classTimeData, iTime, rowCount);		
		
		frmYuMake = new JFrame();
		frmYuMake.setTitle("YU MAKE");
		frmYuMake.setBounds(100, 100, 900, 650);
		frmYuMake.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmYuMake.getContentPane().setLayout(null);

		// 테이블
		totalScrollPane = new JScrollPane();
		totalScrollPane.setBounds(12, 93, 849, 196);
		frmYuMake.getContentPane().add(totalScrollPane);

		tableModel = new DefaultTableModel(totalData, saTit);
		totalTable = new JTable(tableModel);
		totalTable.addMouseListener(this);
		totalTable.setFocusable(false);
		totalScrollPane.setViewportView(totalTable);

		totalTable.setAutoCreateRowSorter(true);
		TableRowSorter Tsorter = new TableRowSorter(totalTable.getModel());
		totalTable.setRowSorter(Tsorter);

		selectedScrollPane = new JScrollPane();
		selectedScrollPane.setBounds(12, 342, 849, 199);
		frmYuMake.getContentPane().add(selectedScrollPane);

		selectedModel = new DefaultTableModel(saTit, 0);
		selectedTable = new JTable(selectedModel);
		selectedTable.addMouseListener(this);
		// selectedTable.setFocusable(false);
		selectedScrollPane.setViewportView(selectedTable);

		// 학생정보 : 이름
		JLabel name = new JLabel(sInformation.getName());
		name.setBounds(12, 63, 45, 15);
		frmYuMake.getContentPane().add(name);
		// 학과
		JLabel dept = new JLabel(sInformation.getDept());
		dept.setBounds(74, 63, 80, 15);
		frmYuMake.getContentPane().add(dept);
		// 학년
		JLabel grade = new JLabel(sInformation.getGrade() + "학년");
		grade.setBounds(175, 63, 35, 15);
		frmYuMake.getContentPane().add(grade);
		// 학기
		JLabel semester = new JLabel(sInformation.getSemester() + "학기");
		semester.setBounds(227, 63, 35, 15);
		frmYuMake.getContentPane().add(semester);
		// 신청가능학점
		maxCredit = sInformation.getApplicationC();

		creditLabel = new JLabel("New label");
		creditLabel.setBounds(673, 304, 188, 23);
		frmYuMake.getContentPane().add(creditLabel);
		creditLabel.setText("신청가능학점 : " + selectedCredit + " / " + maxCredit);

		// 버튼
		addButton = new JButton("추가");
		addButton.setEnabled(false);
		addButton.setBounds(12, 304, 97, 23);
		frmYuMake.getContentPane().add(addButton);
		addButton.addActionListener(this);

		deleteButton = new JButton("제거");
		deleteButton.setEnabled(false);
		deleteButton.setBounds(126, 304, 97, 23);
		frmYuMake.getContentPane().add(deleteButton);
		deleteButton.addActionListener(this);

		resetButton = new JButton("초기화");
		resetButton.setBounds(764, 556, 97, 23);
		frmYuMake.getContentPane().add(resetButton);
		resetButton.addActionListener(this);

		makeTimeTableButton = new JButton("YU MAKE");
		makeTimeTableButton.setBounds(626, 556, 127, 23);
		frmYuMake.getContentPane().add(makeTimeTableButton);
		makeTimeTableButton.addActionListener(this);

		searchButton = new JButton("검색");
		searchButton.setBounds(764, 58, 97, 23);
		frmYuMake.getContentPane().add(searchButton);
		searchButton.addActionListener(this);
		
		myclassAddButton = new JButton("공강 추가");
		myclassAddButton.setBounds(240, 304, 111, 23);
		frmYuMake.getContentPane().add(myclassAddButton);
		myclassAddButton.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 878, 31);
		frmYuMake.getContentPane().add(menuBar);	
		
		JMenu mnNewMenu = new JMenu("파일");
		menuBar.add(mnNewMenu);	
		
		newFileOpenItem = new JMenuItem("불러오기");
		mnNewMenu.add(newFileOpenItem);
		newFileOpenItem.addActionListener(this);
		
		saveFileItem = new JMenuItem("저장하기");
		mnNewMenu.add(saveFileItem);
		saveFileItem.addActionListener(this);
		
		JMenu mnNewMenu_1 = new JMenu("도움말");
		menuBar.add(mnNewMenu_1);		
		
		helpMenuItem = new JMenuItem("도움말");
		mnNewMenu_1.add(helpMenuItem);
		helpMenuItem.addActionListener(this);
		
		informationMenuItem = new JMenuItem("프로그램정보");
		mnNewMenu_1.add(informationMenuItem);		
		informationMenuItem.addActionListener(this);		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addButton) {
			if (CreditCheck.creditCheck(totalData, row, selectedCredit, maxCredit) == 2) {
				JOptionPane.showMessageDialog(null, "신청가능학점이 초과했습니다.");
			} else if (OverlapCheck.classNameOverlapCheck(row, selectedDataRow, totalData, selectedData) == 2
					|| OverlapCheck.classOverlapCheck(row, selectedDataRow, totalData, selectedData) == 2) {
				JOptionPane.showMessageDialog(null, "이미 추가 된 과목입니다.");
			} 
			else if (OverlapCheck.timeOverlapCheck(row, classTimeData, selectedTimeData, selectedDataRow)==2){
				
				JOptionPane.showMessageDialog(null, "시간 중복입니다.");
			}			
			else {
				add.add(totalData, selectedData, classTimeData, selectedTimeData, row, selectedDataRow);
				add.setSelectedTable(selectedModel, selectedData[selectedDataRow]);
				
				selectedCredit = credit.addCredit(selectedData, selectedDataRow, selectedCredit);			
				selectedDataRow++;
			}
			creditLabel.setText("신청가능학점 : " + selectedCredit + " / " + maxCredit);

		}

		if (e.getSource() == deleteButton) {
			
			if(selectedData[row][0]==null){
				ClassDelete delete=new ClassDelete();
				delete.deleteSelectedData(selectedData, row, selectedDataRow, colCount);
				delete.deleteClassTimeData(row, selectedDataRow, colCount, selectedTimeData);
				
				
				selectedModel.removeRow(row);
				selectedTable.setModel(selectedModel);			
				
				selectedDataRow--;
			}
			else{
			selectedCredit = credit.deleteCredit(selectedData, row, selectedCredit);
			//selectedCredit = CreditData.deleteCredit(selectedData, row, selectedCredit);
			
			ClassDelete delete=new ClassDelete();
			delete.deleteSelectedData(selectedData, row, selectedDataRow, colCount);
			delete.deleteClassTimeData(row, selectedDataRow, colCount, selectedTimeData);
			
			
			selectedModel.removeRow(row);
			selectedTable.setModel(selectedModel);			
			
			selectedDataRow--;
			}

			creditLabel.setText("신청가능학점 : " + selectedCredit + " / " + maxCredit);
		}

		if (e.getSource() == resetButton) {
			
			ClassReset reset =new ClassReset();
			reset.resetSelectedTable(selectedData, selectedTimeData, selectedDataRow, colCount);
			//ClassReset.resetSelectedTable(selectedData,selectedHms, selectedDataCol, colCount);
			
			selectedModel.setRowCount(0);
			
			selectedCredit=credit.resetCredit(selectedCredit);
			//selectedCredit = CreditData.resetCredit(selectedCredit);
			
			selectedDataRow=0;
			creditLabel.setText("신청가능학점 : " + selectedCredit + " / " + maxCredit);
			
		}

		if (e.getSource() == makeTimeTableButton) {
			TimeTableUi.getTimeTable(selectedDataRow, selectedTimeData, selectedData);
		}

		
		
		if(e.getSource()==newFileOpenItem){
			newFile = FileSelect.fileSelect();
			
			rowCount=getData.count(1, newFile);
			colCount =getData.count(2, newFile);
			
			totalData =getData.getTotalDataSize(newFile, totalData);
			totalData =getData.getTotalData(newFile, totalData);
			classTimeData=getData.getClassTimeDataSize(newFile, classTimeData);
			newItime=change.changeTimeData(rowCount, totalData);
			classTimeData=timeData.makeTimeData(classTimeData, newItime, rowCount);
			
			
			tableModel = new DefaultTableModel(totalData, saTit);
			totalTable.setModel(tableModel);
			
		}
		
		if(e.getSource()==saveFileItem){
			ClassSave save=new ClassSave();
			save.classSave(sInformation, selectedData, selectedDataRow, colCount);
			//ClassSave.classSave(sInformation, selectedData,selectedDataCol,colCount);
		}
		
		if(e.getSource()==helpMenuItem){
			HelpUi.main(null);
		}
		
		if(e.getSource()==informationMenuItem){
			InformationUi.main(null);
		}
		
		if (e.getSource() == searchButton) {
			SearchUi.main();
		}
		
		if(e.getSource()==myclassAddButton){
			MyClassUi.main();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == totalTable) {
			JTable jtable = (JTable) e.getSource();
			row = jtable.getSelectedRow();
			deleteButton.setEnabled(false);
			if (row > -1) {
				addButton.setEnabled(true);
			}
		}
		if (e.getSource() == selectedTable) {
			JTable jtable = (JTable) e.getSource();
			row = jtable.getSelectedRow();
			addButton.setEnabled(false);
			if (row > -1) {
				deleteButton.setEnabled(true);
			}
		}
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
