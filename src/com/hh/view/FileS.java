package com.hh.view;
//主界面
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.hh.controller.Controller;
import com.hh.initframe.InitFrame;

public class FileS extends InitFrame {

	private CardLayout card = new CardLayout();//卡片布局，可选择顶端的界面（自动选择最上段）
	private JPanel pmain = new JPanel(null);//搜索，修改，删除，查看的容器
	private JPanel pquery = new JPanel(null);///搜索
	private JPanel plist = new JPanel();//
	private JPanel pupdate = new JPanel(null);//修改
	private JPanel pfun = new JPanel(null);//
	private JPanel pcard = new JPanel(card);//容器
	private JPanel pshow = new JPanel();//查看

	private JButton bquery = new JButton("搜索");//bquery
	private JButton bupdate = new JButton("修改");//bupdate
	private JButton bsort = new JButton("排序");//bsort
	private JButton bdelete = new JButton("删除");//dbelete
	private JButton bshow = new JButton("查看");//按钮
	private JTextArea jta = new JTextArea();//文本域
	JScrollPane scrollPanep = new JScrollPane(plist);//滑动
	JScrollPane scrollPaneu = new JScrollPane(jta);//滑动

	private JLabel load = new JLabel("路径");//标签。load。路径
	private JTextField loadT = new JTextField();//文本框

	@Override
	public void addPart() {
		load.setBounds(40, 10, 100, 30);//x,y,w,l路径
		loadT.setBounds(90, 10, 100, 30);//文本框
		bquery.setBounds(210, 10, 70, 30);//搜索
		pquery.add(load);//路径
		pquery.add(loadT);//文本框
		pquery.add(bquery);//搜索
		bsort.setBounds(10, 10, 70, 30);//排序
		bshow.setBounds(90, 10, 70, 30);//查看
		bupdate.setBounds(170, 10, 70, 30);//修改
		bdelete.setBounds(250, 10, 70, 30);//删除
		pfun.add(bsort);//排序
		pfun.add(bshow);//查看
		pfun.add(bupdate);//修改
		pfun.add(bdelete);//删除
		scrollPaneu.setBounds(0, 0, 300, 200);//
		pupdate.add(scrollPaneu);//
		pquery.setBounds(10, 10, 330, 50);//
		pcard.setBounds(25, 70, 300, 200);//
		pcard.add(pshow,"pshow");//
		pcard.add(plist,"plist");//
		pcard.add(scrollPanep);//
		pfun.setBounds(10, 280, 330, 50);//
		pupdate.setBounds(25, 340, 300, 200);//
		pquery.setBackground(Color.red);//
		pfun.setBackground(Color.black);//
		
		pmain.add(pquery);//
		pmain.add(pupdate);//
		pmain.add(pcard);//
		pmain.add(pfun);//
		this.add(pmain);//
	}

	@Override
	public void addLis() {
		bquery.addActionListener(new ActionListener() {//GUI事件监听模型
//
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = loadT.getText();//监视文本框
				Controller.queryFile(l,plist,pshow,pcard,card);
			}//获取文件名
		});//
		bsort.addActionListener(new ActionListener() {
//
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.sort(plist,pshow,pcard,card);
			}
		});//排序
		bshow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.show(jta,pupdate);
			}
		});//显示
		bupdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.update(jta,pupdate);

			}//更改
		});
		bdelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controller.delete(plist,pshow,pcard,card);
			}
		});//删除

	}

}
