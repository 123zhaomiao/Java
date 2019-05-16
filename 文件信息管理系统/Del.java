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
	JTable table;	//展示零件信息表格
	Font font = new Font("宋体", Font.BOLD, 15);
	
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
		label = new JLabel("请输入要删除的文件名");
		label.setFont(new Font("宋体", Font.BOLD,22));
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
		
		delBt = new JButton("删除");
		delBt.setFont(font);
		delBt.setSize(120, 40);
		delBt.setLocation(450, 40);
		delBt.addActionListener(this);
		this.add(delBt);	
	}
	//表格设计
	private void refreshTable()
	{
		String[] titles = {"作者姓名", "文件名", "主要内容"};
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
		//判断用户输入为空
		if(!checkIsNull())
		{
			JOptionPane.showMessageDialog(this, "文件名输入为空");
			return;
		}
		//判断文件名长度
        if (isExit()) {
            JOptionPane.showMessageDialog(this, "文件名不存在");
            aText.setText("");
            return ;
        }
				//创建一个操作零件信息文本文件的对象
				DAO dao = new DAO();
				if(dao.deletePart(aText.getText().trim()))
				{
					JOptionPane.showMessageDialog(this, "删除文档记录成功");
				
					aText.setText("");
					refreshTable();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "删除文档记录失败");
					aText.setText("");
				}
			}
	//判断用户输入是否为空
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
