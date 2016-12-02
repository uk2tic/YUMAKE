package GUI;

import java.io.File;

import javax.swing.JFileChooser;

public class FileSelect {
	public static File fileSelect() { // 파일선택
		
		JFileChooser fc = new JFileChooser();	//JFileChooser 선언
		File file = null;
		
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();	//file에 선택된 파일을 저장
			return file;	//선택된 파일을 return
		}
		return file = null;
	}
}
