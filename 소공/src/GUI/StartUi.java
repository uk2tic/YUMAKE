package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class StartUi extends JFrame implements ItemListener, ActionListener  {
	
	//시작화면 새내기인지 기존사용자인지 선택받아 해당되는 사용자에 따른 UI호출
	
   private JPanel contentPane;
   private JRadioButton radioButton;
   private JRadioButton radioButton2;
   private ButtonGroup rbGroup;
   private String delngSe="0";
   private JButton startButton;
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               StartUi frame = new StartUi();
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
   public StartUi() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 500, 350);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);
      
      rbGroup = new ButtonGroup();
      
      radioButton = new JRadioButton("기존사용자");
      radioButton.setBounds(100, 200, 90, 30);
      contentPane.add(radioButton);
      radioButton.addItemListener(this);
      rbGroup.add(radioButton);
      
      radioButton2 = new JRadioButton("새내기");
      radioButton2.setBounds(300, 200, 70, 30);
      contentPane.add(radioButton2);
      radioButton2.addItemListener(this);
      rbGroup.add(radioButton2);
      
      startButton = new JButton("Start");
      startButton.setBounds(53, 236, 385, 50);
      contentPane.add(startButton);
      startButton.addActionListener(this);
   }
   
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      if ( e.getSource() == startButton ){
         if(delngSe.equals("1")){
            existUserUi.main(null);
            this.dispose();
         }
         else if(delngSe.equals("2")){
            NewUserUi.main(null);
            this.dispose();
         }
         else{
            JOptionPane.showMessageDialog(null, "선택해주세요.");
         }
      }
   }
   
   public void itemStateChanged(ItemEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource() == radioButton)
      {
         if( e.getStateChange() == ItemEvent.SELECTED )
         {
            delngSe = "1";
            System.out.println(delngSe);
         }
      }
      else if(e.getSource() == radioButton2)
      {
         if( e.getStateChange() == ItemEvent.SELECTED )
         {
            delngSe = "2";
            System.out.println(delngSe);
         }
      }
      
   }
}