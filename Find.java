import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class Find extends JPanel implements ActionListener {
	JLabel label;
	JLabel aLabel;
	
	TextField aText;
	JButton delBt;
	JScrollPane scrollpane;
	JTable table;	//չʾ�����Ϣ���
	Font font = new Font("����", Font.BOLD, 20);
	
	public Find()
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
		label = new JLabel("������Ҫ��ѯ���ļ���");
		label.setFont(new Font("����", Font.BOLD,22));
		label.setSize(300, 30);
		label.setLocation(180, 2);
		this.add(label);
		
		aLabel = new JLabel("�ļ���");
		aLabel.setFont(font);
		aLabel.setSize(100, 40);
		aLabel.setLocation(50, 40);
		this.add(aLabel);
		
		aText = new TextField();
		aText.setFont(font);
		aText.setSize(200, 40);
		aText.setLocation(200, 40);
		this.add(aText);
		
		delBt = new JButton("��ѯ");
		delBt.setFont(font);
		delBt.setSize(120, 40);
		delBt.setLocation(450, 40);
		delBt.addActionListener(this);
		this.add(delBt);	
	}

	
	
	//������
	private void refreshTable()
	{
		String[] titles = {"��������", "�ļ���", "�ļ�����"};
		DAO pdao = new DAO();
		ArrayList<Text> parts = pdao.findAllParts();
		Object[][] objs = new Object[parts.size()][3];
		for(int i = 0; i < parts.size(); i++){
			Text pa = new Text();
			pa = parts.get(i);
			objs[i][0] = pa.getAuthorName();
			objs[i][1] = pa. getTextName();
			objs[i][2] = pa.getContent();
		}
		this.removeAll();
		this.init();
		table = new JTable(objs, titles);
		table.setSize(700, 300);
		scrollpane = new JScrollPane(table);
		scrollpane.setLocation(20, 180);
		scrollpane.setSize(620, 300);
		this.add(scrollpane);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		//�ж��û�����Ϊ��
		if(checkIsNull())
		{
			JOptionPane.showMessageDialog(this, "�ļ�������Ϊ��");
			return ;
		}
		
			//�ж�������Ϣ�Ƿ�����
			if(isError())
			{
				JOptionPane.showMessageDialog(this, "�ļ���������");
				aText.setText("");
				return ;
			}
			
				DAO dao = new DAO();
				
				Text pa = dao.findPartByNum(aText.getText().trim());
				JOptionPane.showMessageDialog(this, "aname="+pa.getAuthorName()+", cname="+pa.getTextName()+", content="+pa.getContent()+"}");
				aText.setText("");
	}
		
	
	
	//�ж��û������Ƿ�Ϊ��
	private boolean checkIsNull()
	{
		if ("".equals(aText.getText().trim()) || aText.getText() == null) {
            return true;
        }
        return false;
	}
	 private boolean isError()
	    {	       
	        DAO pdao = new DAO();
			ArrayList<Text> parts = pdao.findAllParts();
			Object[][] objs = new Object[parts.size()][3];
			System.out.println(parts.size());
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
