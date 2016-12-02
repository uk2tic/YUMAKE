package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Check.CreditCheck;
import Check.OverlapCheck;
import Data.CreditData;
import Data.ChangeData;
import Data.ClassAdd;
import Data.ClassSearch;
import Data.TimeData;

public class SearchUi extends MainUi implements MouseListener, ActionListener {

	// 교수명, 과목명으로 검색 후 추가 버튼 누를시 예비수강목록에 추가됨
	// (아직 UI상으로 추가되나 데이터 문제해결못함.)

	private JFrame searchFrame;
	private JScrollPane searchScrollPane;
	private DefaultTableModel searchModel;
	private JTable searchTable;
	private JButton addButton;
	private JButton searchButton;
	private JComboBox comboBox;
	private JTextField textField;

	// private static int selectedDataCol = 0;
	private static int searchRow = 0;
	private int searchSelRow;
	private static String[][] searchData = null;
	private static int[][] searchTimeData = null;
	private static int[][] searchItime;
	private static String searchText;
	private static String sType = null;
	private String[] saTit = new String[] { "수강번호", "학년", "이수구분", "과목명", "학점", "담당교수", "강의시간", "강의실", "개설학과", "비고" };

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {
					searchData = new String[rowCount][10];
					SearchUi s = new SearchUi();
					s.searchFrame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SearchUi() {

		searchFrame = new JFrame();
		searchFrame.setTitle("YU MAKE");
		searchFrame.setBounds(100, 100, 900, 500);
		searchFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchFrame.getContentPane().setLayout(null);

		searchScrollPane = new JScrollPane();
		searchScrollPane.setBounds(17, 96, 844, 282);
		searchFrame.getContentPane().add(searchScrollPane);

		searchModel = new DefaultTableModel(null, saTit);
		searchTable = new JTable(searchModel);
		searchTable.addMouseListener(this);
		searchTable.setFocusable(false);
		searchScrollPane.setViewportView(searchTable);

		searchTable.setAutoCreateRowSorter(true);
		TableRowSorter Tsorter = new TableRowSorter(searchTable.getModel());
		searchTable.setRowSorter(Tsorter);

		addButton = new JButton("추가");
		addButton.setBounds(764, 393, 97, 23);
		searchFrame.getContentPane().add(addButton);
		addButton.addActionListener(this);

		searchButton = new JButton("검색");
		searchButton.setBounds(520, 61, 97, 23);
		searchFrame.getContentPane().add(searchButton);
		searchButton.addActionListener(this);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "교수명", "과목명" }));
		comboBox.setBounds(153, 62, 89, 21);
		searchFrame.getContentPane().add(comboBox);

		textField = new JTextField();
		textField.setBounds(254, 59, 254, 24);
		searchFrame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == searchButton) {
			
			ClassSearch search=new ClassSearch();
			searchModel.setRowCount(0);
			searchRow = 0;
			searchText = textField.getText();
			sType = (String) comboBox.getSelectedItem();

			
			//검색해서 searchData, searchHms에 넣고 테이블에 띄우는 것까지 완료!!
			if ("교수명".equals(sType)) {
				//search.professorSearch(rowCount, searchRow, totalData, searchData, searchHms, searchItime, searchText);
				
				professorSearch();
				
				for (int i = 0; i < searchRow; i++) {
					searchModel.addRow(searchData[i]);
				}

				searchTable.setModel(searchModel);
			} else {
				subjectSearch();
				for (int i = 0; i < searchRow; i++) {
					searchModel.addRow(searchData[i]);
				}
				searchTable.setModel(searchModel);
			}
		}
		
		
		
		if (e.getSource() == addButton) {
			if (CreditCheck.creditCheck(searchData, searchSelRow, selectedCredit, maxCredit) == 2) {
				JOptionPane.showMessageDialog(null, "신청가능학점이 초과했습니다.");
			} 
			else if (OverlapCheck.classNameOverlapCheck(searchSelRow, selectedDataRow, searchData, selectedData) == 2
					|| OverlapCheck.classOverlapCheck(searchSelRow, selectedDataRow, searchData, selectedData) == 2) {
				JOptionPane.showMessageDialog(null, "이미 추가 된 과목입니다.");
			} 
			else if (OverlapCheck.timeOverlapCheck(searchSelRow, searchTimeData, selectedTimeData, selectedDataRow) == 2) {
				System.out.println(searchSelRow);
				JOptionPane.showMessageDialog(null, "시간 중복입니다.");
			}
			else{
				//ClassAdd add = new ClassAdd();
				add.add(searchData, selectedData, searchTimeData, selectedTimeData, searchSelRow, selectedDataRow);
				add.setSelectedTable(selectedModel, selectedData[selectedDataRow]);
				//ClassAdd.add(searchData, selectedData, searchHms, selectedHms, searchSelRow, selectedDataCol);//데이터들어감.
				//ClassAdd.setSelectedTable(selectedModel, selectedData[selectedDataCol]);//테이블에selectedData들어감
				
				selectedCredit=credit.addCredit(selectedData, selectedDataRow, selectedCredit);
				//selectedCredit = CreditData.addCredit(selectedData, selectedDataCol, selectedCredit);

				selectedDataRow++;
			}
				creditLabel.setText("신청가능학점 : " + selectedCredit + " / " + maxCredit);
			}
			
	}

	public static void professorSearch() {
		for (int i = 0; i < rowCount; i++) {
			if (totalData[i][5].equals(searchText)) {
				searchData[searchRow] = totalData[i].clone();
				searchRow++;
			}
		}
		searchItime=change.changeTimeData(searchRow, searchData);		
		searchTimeData = new int[searchRow][6];
		searchTimeData=timeData.makeTimeData(searchTimeData, searchItime, searchRow);
		
	}

	public static void subjectSearch() {
		for (int i = 0; i < rowCount; i++) {
			if (totalData[i][3].equals(searchText)) {
				searchData[searchRow] = totalData[i].clone();
				searchRow++;
			}
		}
		searchItime=change.changeTimeData(searchRow, searchData);		
		searchTimeData = new int[searchRow][6];
		searchTimeData=timeData.makeTimeData(searchTimeData, searchItime, searchRow);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == searchTable) {
			JTable jtable = (JTable) e.getSource();
			searchSelRow = jtable.getSelectedRow();
			if (searchSelRow > -1) {
				addButton.setEnabled(true);
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