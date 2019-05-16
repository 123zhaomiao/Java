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
	JTable table;	//展示零件信息表格
	Font font = new Font("宋体", Font.BOLD, 20);
	
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
		label = new JLabel("请输入要查询的文件名");
		label.setFont(new Font("宋体", Font.BOLD,22));
		label.setSize(300, 30);
		label.setLocation(180, 2);
		this.add(label);
		
		aLabel = new JLabel("文件名");
		aLabel.setFont(font);
		aLabel.setSize(100, 40);
		aLabel.setLocation(50, 40);
		this.add(aLabel);
		
		aText = new TextField();
		aText.setFont(font);
		aText.setSize(200, 40);
		aText.setLocation(200, 40);
		this.add(aText);
		
		delBt = new JButton("查询");
		delBt.setFont(font);
		delBt.setSize(120, 40);
		delBt.setLocation(450, 40);
		delBt.addActionListener(this);
		this.add(delBt);	
	}

	
	
	//表格设计
	private void refreshTable()
	{
		String[] titles = {"作者姓名", "文件名", "文件内容"};
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
		//判断用户输入为空
		if(checkIsNull())
		{
			JOptionPane.showMessageDialog(this, "文件名输入为空");
			return ;
		}
		
			//判断输入信息是否有误
			if(isError())
			{
				JOptionPane.showMessageDialog(this, "文件名不存在");
				aText.setText("");
				return ;
			}
			
				DAO dao = new DAO();
				
				Text pa = dao.findPartByNum(aText.getText().trim());
				JOptionPane.showMessageDialog(this, "aname="+pa.getAuthorName()+", cname="+pa.getTextName()+", content="+pa.getContent()+"}");
				aText.setText("");
	}
		
	
	
	//判断用户输入是否为空
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
