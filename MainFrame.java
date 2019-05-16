import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// ���ڼ��������� ��ťѡ���������
public class MainFrame extends JFrame implements ActionListener {
	//�˵���
	JMenuBar menuBar;  //�˵���
	JMenu menu;        //�˵�
	JMenuItem[] items; 
	Component panel; 
	Font font =new Font("����",Font.BOLD,20);  //�˵��������С
	
	public MainFrame()
	{
		this.setSize(800,700);
		this.setTitle("�ĵ���Ϣ����ϵͳ");
		this.setLayout(null);
		this.initMenu();  //��ʼ���˵���
		this.setVisible(true);
	}
	private void initMenu()
	{
		menuBar = new JMenuBar();
		menu = new JMenu("����");
		menu.setFont(font);
		items = new JMenuItem[4];
		String[] menuNames = {"����", "ɾ��", "�޸�", "��ѯ"};
		//�����˵��Ϊ�˵�������¼�������
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
		JMenuItem item = (JMenuItem)e.getSource();	//��ȡ�¼��ķ����ߣ�getScore()����Object������ʹ������ת��
		if(panel != null)
		{
			this.remove(panel);	//����¼���������岻Ϊ��Ҫ�Ƚ�֮ǰ��ʾ�����
		}
		if(item.getText().equals("����"))
		{
			
			panel = new Add();
			this.add(panel);	//��������������
			//�ػ�component�ķ���
			//��ʹ�õ�ԭ���ǣ�component�м��е�ͼ�η����仯�󲻻�������ʾ����ʹ��repaint��������
			this.repaint();
		}
		else
		{
			if(item.getText().equals("ɾ��"))
			{
				//����¼�Ϊɾ��������һ��ɾ���ĵ���Ϣ��������
				panel = new Del();
				this.add(panel);
				this.repaint();
			}
			else
			{
				if(item.getText().equals("�޸�"))
				{
					//����¼�Ϊ�޸ģ�����һ���޸��ĵ���Ϣ��������
					panel = new Update();
					this.add(panel);
					this.repaint();
				}
				else
				{
					if(item.getText().equals("��ѯ"))
					{
						//����¼�Ϊ��ѯ������һ����ѯ�ĵ���Ϣ��������
						panel = new Find();
						this.add(panel);
						this.repaint();
					}
				}
			}
		}
		
	}
	
}
