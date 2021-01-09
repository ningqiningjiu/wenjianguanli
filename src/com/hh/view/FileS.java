package com.hh.view;
//������
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

	private CardLayout card = new CardLayout();//��Ƭ���֣���ѡ�񶥶˵Ľ��棨�Զ�ѡ�����϶Σ�
	private JPanel pmain = new JPanel(null);//�������޸ģ�ɾ�����鿴������
	private JPanel pquery = new JPanel(null);///����
	private JPanel plist = new JPanel();//
	private JPanel pupdate = new JPanel(null);//�޸�
	private JPanel pfun = new JPanel(null);//
	private JPanel pcard = new JPanel(card);//����
	private JPanel pshow = new JPanel();//�鿴

	private JButton bquery = new JButton("����");//bquery
	private JButton bupdate = new JButton("�޸�");//bupdate
	private JButton bsort = new JButton("����");//bsort
	private JButton bdelete = new JButton("ɾ��");//dbelete
	private JButton bshow = new JButton("�鿴");//��ť
	private JTextArea jta = new JTextArea();//�ı���
	JScrollPane scrollPanep = new JScrollPane(plist);//����
	JScrollPane scrollPaneu = new JScrollPane(jta);//����

	private JLabel load = new JLabel("·��");//��ǩ��load��·��
	private JTextField loadT = new JTextField();//�ı���

	@Override
	public void addPart() {
		load.setBounds(40, 10, 100, 30);//x,y,w,l·��
		loadT.setBounds(90, 10, 100, 30);//�ı���
		bquery.setBounds(210, 10, 70, 30);//����
		pquery.add(load);//·��
		pquery.add(loadT);//�ı���
		pquery.add(bquery);//����
		bsort.setBounds(10, 10, 70, 30);//����
		bshow.setBounds(90, 10, 70, 30);//�鿴
		bupdate.setBounds(170, 10, 70, 30);//�޸�
		bdelete.setBounds(250, 10, 70, 30);//ɾ��
		pfun.add(bsort);//����
		pfun.add(bshow);//�鿴
		pfun.add(bupdate);//�޸�
		pfun.add(bdelete);//ɾ��
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
		bquery.addActionListener(new ActionListener() {//GUI�¼�����ģ��
//
			@Override
			public void actionPerformed(ActionEvent e) {
				String l = loadT.getText();//�����ı���
				Controller.queryFile(l,plist,pshow,pcard,card);
			}//��ȡ�ļ���
		});//
		bsort.addActionListener(new ActionListener() {
//
			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.sort(plist,pshow,pcard,card);
			}
		});//����
		bshow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.show(jta,pupdate);
			}
		});//��ʾ
		bupdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Controller.update(jta,pupdate);

			}//����
		});
		bdelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Controller.delete(plist,pshow,pcard,card);
			}
		});//ɾ��

	}

}
