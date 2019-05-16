import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 窗口及基本操作 按钮选择基本动作
public class MainFrame extends JFrame implements ActionListener {
	//菜单栏
	JMenuBar menuBar;  //菜单栏
	JMenu menu;        //菜单
	JMenuItem[] items; 
	Component panel; 
	Font font =new Font("宋体",Font.BOLD,20);  //菜单项字体大小
	
	public MainFrame()
	{
		this.setSize(800,700);
		this.setTitle("文档信息管理系统");
		this.setLayout(null);
		this.initMenu();  //初始化菜单栏
		this.setVisible(true);
	}
	private void initMenu()
	{
		menuBar = new JMenuBar();
		menu = new JMenu("操作");
		menu.setFont(font);
		items = new JMenuItem[4];
		String[] menuNames = {"增加", "删除", "修改", "查询"};
		//创建菜单项并为菜单项添加事件监听器
		for(int i = 0; i < menuNames.length; i++){
			items[i] = new JMenuItem(menuNames[i]);
			items[i].addActionListener(this);
			items[i].setFont(font);
			menu.add(items[i]);
		}
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem)e.getSource();	//获取事件的发起者，getScore()返回Object，所以使用向上转型
		if(panel != null)
		{
			this.remove(panel);	//点击事件发生后，面板不为空要先将之前显示的清空
		}
		if(item.getText().equals("增加"))
		{
			
			panel = new Add();
			this.add(panel);	//将面板添加至窗口
			//重绘component的方法
			//（使用的原因是：component中己有的图形发生变化后不会立刻显示，须使用repaint方法。）
			this.repaint();
		}
		else
		{
			if(item.getText().equals("删除"))
			{
				//点击事件为删除，创建一个删除文档信息的面板对象
				panel = new Del();
				this.add(panel);
				this.repaint();
			}
			else
			{
				if(item.getText().equals("修改"))
				{
					//点击事件为修改，创建一个修改文档信息的面板对象
					panel = new Update();
					this.add(panel);
					this.repaint();
				}
				else
				{
					if(item.getText().equals("查询"))
					{
						//点击事件为查询，创建一个查询文档信息的面板对象
						panel = new Find();
						this.add(panel);
						this.repaint();
					}
				}
			}
		}
		
	}
	
}
