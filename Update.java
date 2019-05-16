import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Update extends JPanel implements ActionListener {
	JLabel label;
	JLabel authorLabel;
	JLabel nameLabel;
	JLabel contentLabel;
	
	JTextField authorText;
	JTextField nameText;
	JTextField contentText;
	
	JButton updateBt;
	JScrollPane scrollpane;
	JTable table;	//չʾ�ĵ���Ϣ���
	Font font = new Font("����", Font.BOLD, 20);
	
	public Update()
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
		label = new JLabel("������Ҫ�޸ĵ���Ϣ");
		label.setFont(new Font("����", Font.BOLD,15));
		label.setSize(200, 30);
		label.setLocation(230, 2);
		this.add(label);
		
		authorLabel = new JLabel("��������");
		authorLabel.setFont(font);
		authorLabel.setSize(100, 30);
		authorLabel.setLocation(50, 40);
		this.add(authorLabel);
		
		authorText = new JTextField();
		authorText.setFont(font);
		authorText.setSize(120, 40);
		authorText.setLocation(30, 80);
		this.add(authorText);
		
		nameLabel = new JLabel("�ļ���");
		nameLabel.setFont(font);
		nameLabel.setSize(100, 30);
		nameLabel.setLocation(200, 40);
		this.add(nameLabel);
		nameText = new JTextField();
		nameText.setFont(font);
		nameText.setSize(120, 40);
		nameText.setLocation(180, 80);
		this.add(nameText);
		
		contentLabel = new JLabel("�ļ�����");
		contentLabel.setFont(font);
		contentLabel.setSize(100, 30);
		contentLabel.setLocation(350, 40);
		this.add(contentLabel);
		contentText = new JTextField();
		contentText.setFont(font);
		contentText.setSize(120, 40);
		contentText.setLocation(330, 80);
		this.add(contentText);
		

		
		updateBt = new JButton("�޸�");
		updateBt.setFont(font);
		updateBt.setSize(120, 40);
		updateBt.setLocation(260, 130);
		updateBt.addActionListener(this);
		this.add(updateBt);
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
		if(isEmpty())	
		{
			JOptionPane.showMessageDialog(this, "����������������Ϊ��");
			authorText.setText("");
			nameText.setText("");
			contentText.setText("");	
			return ;
		}
			if(isExit())
			{
				JOptionPane.showMessageDialog(this, "�ļ���������");
				authorText.setText("");
				nameText.setText("");
				contentText.setText("");	
				return;
			}
				DAO dao = new DAO();
				String aname = authorText.getText().trim();
				String cname = nameText.getText().trim();
				String content= contentText.getText().trim();	
				
				System.out.println("�ɹ�����");
				if(dao.updatePart(aname,cname,content))
				{
					JOptionPane.showMessageDialog(this, "�޸ĳɹ�");
					//��ӳɹ���ˢ�±��
					refreshTable();
						
				}
				else
				{
					JOptionPane.showMessageDialog(this, "�޸�ʧ��");
					
				}
				authorText.setText("");
				nameText.setText("");
				contentText.setText("");
			}
		
	 private boolean isEmpty() {
	        if (("".equals(authorText.getText().trim()) || authorText.getText() == null) 
	        		||("".equals(nameText.getText().trim()) || nameText.getText() == null)) {
	            return true;
	        }
	        return false;
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
				if((nameText.getText().equals((String)objs[i][1])))		
				{
					return false;  
				}			
			}      
	        return true;
	    }
	

}
