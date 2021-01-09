package com.hh.controller;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.hh.model.ElementList;

public class Controller {

	private static ElementList<File> ef = new ElementList<File>();// �洢�ļ�����

	private static ElementList<JCheckBox> ej = new ElementList<JCheckBox>();//	 �洢��ѡ�����

	public static void queryFile(String load, JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//��ѯ�ļ��ķ���
		ef.getList().clear();//���ef�����е��ļ�����ֹ�ۼ�
		ej.getList().clear();//���eg�����ڵĸ�ѡ�򣬷�ֹ�ۼ�
		File file = new File(load); //����һ���ļ�����
		if (file.exists()) {//����Ӳ�ѯ�����κ��У���EXISTS���������true�� ����������false���ж����ļ��Ƿ����
			if (file.isFile()) {//�ж����ļ������ļ���
				String info = getInfo(file);//����getinfo�����������ļ������ִ�С�޸�ʱ��
				JCheckBox jCheckBox = new JCheckBox("0-" + info);//��������ѡ�������ʾ���ı�ʱ��һ�е���Ϣ
				ef.getList().add(file);//���ļ�������ӵ�ef������
				ej.getList().add(jCheckBox);//����ѡ�������ӵ�ej������
			} 
			    else {//�ļ���
				File[] files = file.listFiles();//�ļ����еĶ�����ӵ��ļ�������
				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						String info = getInfo(files[i]);
						JCheckBox jCheckBox = new JCheckBox("" + count + "-" + info);//������ѡ�������ʾ
						ef.getList().add(files[i]);
						ej.getList().add(jCheckBox);
						count++;
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(pshow, "�ļ�������");
		}
		showJB(plist, pshow, pcard, card);//����ѡ��չʾ��������ʾ
	}

	public static void showJB(JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//��Ƭ
		card.show(pcard, "pshow");//
		card.show(pcard, "plist");//ˢ�¸�ѡ��
		plist.removeAll();//�Ƴ��������������ݣ���ֹ�ۼ�
		JPanel jp = new JPanel(new GridLayout(ej.getList().size(), 1));//�½�JP�����񲼾֣���=��ѡ���������1��
		JScrollPane scrollPanep;//������������bug��
		for (JCheckBox box : ej.getList()) {//�Ӹ�ѡ�򼯺��ó�ÿһ����ѡ����ӵ���������ʾ
			jp.add(box);//
		}
		scrollPanep = new JScrollPane(jp);//��������
		plist.add(jp);//��ӵ�������
		plist.repaint();//�ػ�����
	}

	public static String getInfo(File file) {
		String name = file.getName();//�õ��ļ���
		long length = file.length();//�õ��ļ�����
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd HH:mm:ss");//�ļ�����޸�ʱ�䣬����Ϊϰ�ߵĸ�ʽ
		Calendar cal = Calendar.getInstance();//�ļ�����޸�ʱ�䣬����Ϊϰ�ߵĸ�ʽ
		cal.setTimeInMillis(file.lastModified());//�ļ�����޸�ʱ�䣬����Ϊϰ�ߵĸ�ʽ
		String time = sdf.format(cal.getTime());//�ļ�����޸�ʱ�䣬����Ϊϰ�ߵĸ�ʽ
		return name + "-" + length + "-" + time;//������Щ��Ϣ

	}

	public static void sort(JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//����
		for (int i = 0; i < ef.getList().size(); i++) {//
			for (int j = i + 1; j < ef.getList().size(); j++) {//
				if (ef.getList().get(i).length() < ef.getList().get(j).length()) {//
					File file1 = ef.getList().get(i);//
					File file2 = ef.getList().get(j);//
					ef.getList().remove(file1);//
					ef.getList().remove(file2);//
					ef.getList().add(i, file2);//
					ef.getList().add(j, file1);//ð������


				}
			}

		}
		ej.getList().clear();//�����
		for (int i = 0; i < ef.getList().size(); i++) {//
			if (ef.getList().get(i).isFile()) {//
				String info = getInfo(ef.getList().get(i));//
				JCheckBox jCheckBox = new JCheckBox("" + i + "-" + info);//
				ej.getList().add(jCheckBox);//�����ļ�����λ�ã�ʹ���븴ѡ�����ļ�һһ��Ӧ
			}
		}
		showJB(plist, pshow, pcard, card);//����ѡ��չʾ��������ʾ

	}

	public static void show(JTextArea jta, JPanel pupdate) {//���ļ�����չʾ���ı�����
		List<String> sd = getSd();//�����ѡ�еĸ�ѡ�����ļ���Ϣ��λ���±꣨λ�þ����ļ���������
		if (sd.size() > 1) {//
			JOptionPane.showMessageDialog(pupdate, "ÿ��ֻ�ܲ鿴һ��");//
		} else {//
			for (String s : sd) {//
				int index = Integer.parseInt(s);//
				try (BufferedReader br = new BufferedReader(//
						new InputStreamReader(new FileInputStream(ef.getList().get(index)), "utf-8"))) {//
					String line = "";//
					while ((line = br.readLine()) != null) {//
						jta.setText(line);//
						jta.append("\n");
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	
	public static void update(JTextArea jta, JPanel pupdate) {

		List<String> sd = getSd();
		if (sd.size() > 1) {
			JOptionPane.showMessageDialog(pupdate, "ÿ��ֻ���޸�һ��");
		} else {
			for (String s : sd) {
				int index = Integer.parseInt(s);
				try (FileWriter fw = new FileWriter(ef.getList().get(index))) {
					String text = jta.getText();
					fw.write(text);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	public static List<String> getSd() {
		List<String> list = new ArrayList();
		for (JCheckBox box : ej.getList()) {
			if (box.isSelected()) {
				String text = box.getText();
				String[] split = text.split("-");
				list.add(split[0]);
			}
		}
		return list;
	}

	

	public static void delete(JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {

		List<String> sd = getSd();
		for (String s : sd) {
			int index = Integer.parseInt(s);
			File file = ef.getList().get(index);
			file.delete();//��file����ļ��������Ӧ���ļ��Ӵ�����ɾ��
			ef.getList().remove(file);
		}

		ej.getList().clear();
		for (int i = 0; i < ef.getList().size(); i++) {
			if (ef.getList().get(i).isFile()) {
				String info = getInfo(ef.getList().get(i));
				JCheckBox jCheckBox = new JCheckBox("" + i + "-" + info);
				ej.getList().add(jCheckBox);
			}//һһ��Ӧ
		}

		showJB(plist, pshow, pcard, card);
	}

}
