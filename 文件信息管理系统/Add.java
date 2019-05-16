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


public class Add  extends JPanel implements ActionListener{
	JLabel label;   //提示标签
	JLabel authorLabel;
	JLabel nameLabel;
	JLabel contentLabel;
	
	JTextField authorText;
	JTextField nameText;
	JTextField contentText;
	
	JButton addBt;
	JScrollPane scrollpane;
	JTable table;	//展示文档信息表格
	Font font = new Font("宋体", Font.BOLD, 20);
	
	public Add()
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
		label = new JLabel("请输入要添加的信息");
		label.setFont(new Font("宋体", Font.BOLD,15));
		label.setSize(200, 30);
		label.setLocation(230, 2);
		this.add(label);
		
		authorLabel = new JLabel("作者姓名");
		authorLabel.setFont(font);
		authorLabel.setSize(100, 30);
		authorLabel.setLocation(50, 40);
		this.add(authorLabel);
		
		authorText = new JTextField();
		authorText.setFont(font);
		authorText.setSize(120, 40);
		authorText.setLocation(30, 80);
		this.add(authorText);
		
		nameLabel = new JLabel("文件名");
		nameLabel.setFont(font);
		nameLabel.setSize(100, 30);
		nameLabel.setLocation(200, 40);
		this.add(nameLabel);
		nameText = new JTextField();
		nameText.setFont(font);
		nameText.setSize(120, 40);
		nameText.setLocation(180, 80);
		this.add(nameText);
		
		contentLabel = new JLabel("文件内容");
		contentLabel.setFont(font);
		contentLabel.setSize(100, 30);
		contentLabel.setLocation(350, 40);
		this.add(contentLabel);
		contentText = new JTextField();
		contentText.setFont(font);
		contentText.setSize(120, 40);
		contentText.setLocation(330, 80);
		this.add(contentText);
		

		
		addBt = new JButton("添加");
		addBt.setFont(font);
		addBt.setSize(120, 40);
		addBt.setLocation(260, 130);
		addBt.addActionListener(this);
		this.add(addBt);
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
				objs[i][1] = pa.getTextName();
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
		
		 public void actionPerformed(ActionEvent e)
		    {
		        //判断文件名为空
		        if (isEmpty()) {
		            JOptionPane.showMessageDialog(this, "作者名或者文件名为空");
		            authorText.setText("");
		            nameText.setText("");
		            contentText.setText("");
		            return ;
		        }
		        //判断文件名长度
		        if (isError()) {
		            JOptionPane.showMessageDialog(this, "文件名超出字符长度限制");
		            authorText.setText("");
		            nameText.setText("");
		            contentText.setText("");
		            return ;
		        }
		        String aname = authorText.getText().trim();
				String cname = nameText.getText().trim();
				String content= contentText.getText().trim();	
				DAO dao = new DAO();
				if(dao.addPart(aname,cname,content))
				{
					JOptionPane.showMessageDialog(this, "添加成功");
					//未进行清空处理？
					authorText.setText("");
					nameText.setText("");
					contentText.setText("");
					//添加成功后刷新表格
					refreshTable();	
				}
				else
				{
					JOptionPane.showMessageDialog(this, "文件名重复，添加失败");
					authorText.setText("");
					nameText.setText("");
					contentText.setText("");

				}
		    }
		   
		    private boolean isEmpty() {
		        if (("".equals(authorText.getText().trim()) || authorText.getText() == null) 
		        		||("".equals(nameText.getText().trim()) || nameText.getText() == null)) {
		            return true;
		        }
		        return false;   
		    }
		    private boolean isError()
		    {
		        //文件名长度不能超过10
		        if (nameText.getText().length() > 10)
		            return true;
		        return false;
		    }

}
