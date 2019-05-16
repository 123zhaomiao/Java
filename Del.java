import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Del extends JPanel implements ActionListener{
	JLabel label;
	JLabel aLabel;
	
	TextField aText;
	JButton delBt;
	JScrollPane scrollpane;
	JTable table;	//չʾ�����Ϣ���
	Font font = new Font("����", Font.BOLD, 15);
	
	public Del()
	{
		this.setLayout(null);
		this.setSize(660, 490);
		this.setLocation(10, 15);
		this.setBackground(Color.lightGray);
		this.init();
		this.refreshTable();
		this.setVisible(true);
	}
	private void init()
	{
		label = new JLabel("������Ҫɾ�����ļ���");
		label.setFont(new Font("����", Font.BOLD,22));
		label.setSize(300, 30);
		label.setLocation(180, 2);
		this.add(label);
		
		aLabel = new JLabel("");
		aLabel.setFont(font);
		aLabel.setSize(100, 40);
		aLabel.setLocation(50, 40);
		this.add(aLabel);
		
		aText = new TextField();
		aText.setFont(font);
		aText.setSize(200, 40);
		aText.setLocation(200, 40);
		this.add(aText);
		
		delBt = new JButton("ɾ��");
		delBt.setFont(font);
		delBt.setSize(120, 40);
		delBt.setLocation(450, 40);
		delBt.addActionListener(this);
		this.add(delBt);	
	}
	//������
	private void refreshTable()
	{
		String[] titles = {"��������", "�ļ���", "��Ҫ����"};
		DAO pdao = new DAO();
		ArrayList<Text> parts = pdao.findAllParts();
		Object[][] objs = new Object[parts.size()][3];
		for(int i = 0; i < parts.size(); i++){
			Text pa = new Text();
			pa = parts.get(i);
			objs[i][0] = pa.getAuthorName();
			objs[i][1] = pa.getTextName();
			objs[i][2] = pa.getContent();
		}
		this.removeAll();
		this.init();
		table = new JTable(objs, titles);
		table.setSize(700, 300);
		scrollpane = new JScrollPane(table);
		scrollpane.setLocation(20, 100);
		scrollpane.setSize(620, 380);
		this.add(scrollpane);
	}
	public void actionPerformed(ActionEvent e) {
		//�ж��û�����Ϊ��
		if(!checkIsNull())
		{
			JOptionPane.showMessageDialog(this, "�ļ�������Ϊ��");
			return;
		}
		//�ж��ļ�������
        if (isExit()) {
            JOptionPane.showMessageDialog(this, "�ļ���������");
            aText.setText("");
            return ;
        }
				//����һ�����������Ϣ�ı��ļ��Ķ���
				DAO dao = new DAO();
				if(dao.deletePart(aText.getText().trim()))
				{
					JOptionPane.showMessageDialog(this, "ɾ���ĵ���¼�ɹ�");
				
					aText.setText("");
					refreshTable();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "ɾ���ĵ���¼ʧ��");
					aText.setText("");
				}
			}
	//�ж��û������Ƿ�Ϊ��
	private boolean checkIsNull()
	{
		boolean flag=true;
		if(aText.getText().trim().equals(""))
		{
			flag=false;
		}
		return flag;
	}
	 private boolean isExit()
	    {	       
	        DAO pdao = new DAO();
			ArrayList<Text> parts = pdao.findAllParts();
			Object[][] objs = new Object[parts.size()][3];
			
			for(int i = 0; i < parts.size(); i++){
				Text pa = new Text();
				pa = parts.get(i);
				
				objs[i][1] = pa. getTextName();
			

				if((aText.getText().equals((String)objs[i][1])))		
				{
					return false;  
				}			
			}      
	        return true;
	    }
}
