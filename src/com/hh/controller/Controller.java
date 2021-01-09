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

	private static ElementList<File> ef = new ElementList<File>();// 存储文件对象

	private static ElementList<JCheckBox> ej = new ElementList<JCheckBox>();//	 存储复选框对象

	public static void queryFile(String load, JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//查询文件的方法
		ef.getList().clear();//清空ef集合中的文件，防止累加
		ej.getList().clear();//清除eg集合内的复选框，防止累加
		File file = new File(load); //创建一个文件对象
		if (file.exists()) {//如果子查询包含任何行，则EXISTS运算符返回true。 否则它返回false。判断是文件是否存在
			if (file.isFile()) {//判断是文件还是文件夹
				String info = getInfo(file);//调用getinfo方法，返回文件的名字大小修改时间
				JCheckBox jCheckBox = new JCheckBox("0-" + info);//复创建复选框对象，显示的文本时上一行的信息
				ef.getList().add(file);//将文件对象添加到ef集合中
				ej.getList().add(jCheckBox);//将复选框对象添加到ej集合中
			} 
			    else {//文件夹
				File[] files = file.listFiles();//文件夹中的对象添加到文件集合中
				int count = 0;
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						String info = getInfo(files[i]);
						JCheckBox jCheckBox = new JCheckBox("" + count + "-" + info);//创建复选框对象，显示
						ef.getList().add(files[i]);
						ej.getList().add(jCheckBox);
						count++;
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(pshow, "文件不存在");
		}
		showJB(plist, pshow, pcard, card);//将复选框展示至窗口显示
	}

	public static void showJB(JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//卡片
		card.show(pcard, "pshow");//
		card.show(pcard, "plist");//刷新复选框
		plist.removeAll();//移除容器中所有内容，防止累加
		JPanel jp = new JPanel(new GridLayout(ej.getList().size(), 1));//新建JP，网格布局，行=复选框的数量，1列
		JScrollPane scrollPanep;//创建滑动对象（bug）
		for (JCheckBox box : ej.getList()) {//从复选框集合拿出每一个复选框，添加到容器中显示
			jp.add(box);//
		}
		scrollPanep = new JScrollPane(jp);//创建滑动
		plist.add(jp);//添加到容器中
		plist.repaint();//重绘容器
	}

	public static String getInfo(File file) {
		String name = file.getName();//得到文件名
		long length = file.length();//得到文件长度
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss");//文件最后修改时间，更改为习惯的格式
		Calendar cal = Calendar.getInstance();//文件最后修改时间，更改为习惯的格式
		cal.setTimeInMillis(file.lastModified());//文件最后修改时间，更改为习惯的格式
		String time = sdf.format(cal.getTime());//文件最后修改时间，更改为习惯的格式
		return name + "-" + length + "-" + time;//返回那些信息

	}

	public static void sort(JPanel plist, JPanel pshow, JPanel pcard, CardLayout card) {//排序
		for (int i = 0; i < ef.getList().size(); i++) {//
			for (int j = i + 1; j < ef.getList().size(); j++) {//
				if (ef.getList().get(i).length() < ef.getList().get(j).length()) {//
					File file1 = ef.getList().get(i);//
					File file2 = ef.getList().get(j);//
					ef.getList().remove(file1);//
					ef.getList().remove(file2);//
					ef.getList().add(i, file2);//
					ef.getList().add(j, file1);//冒泡排序


				}
			}

		}
		ej.getList().clear();//清除，
		for (int i = 0; i < ef.getList().size(); i++) {//
			if (ef.getList().get(i).isFile()) {//
				String info = getInfo(ef.getList().get(i));//
				JCheckBox jCheckBox = new JCheckBox("" + i + "-" + info);//
				ej.getList().add(jCheckBox);//跟随文件更新位置，使其与复选框与文件一一对应
			}
		}
		showJB(plist, pshow, pcard, card);//将复选框展示至窗口显示

	}

	public static void show(JTextArea jta, JPanel pupdate) {//将文件内容展示到文本域中
		List<String> sd = getSd();//获得我选中的复选框中文件信息的位置下标（位置就是文件的索引）
		if (sd.size() > 1) {//
			JOptionPane.showMessageDialog(pupdate, "每次只能查看一个");//
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
			JOptionPane.showMessageDialog(pupdate, "每次只能修改一个");
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
			file.delete();//把file这个文件对象像对应的文件从磁盘上删除
			ef.getList().remove(file);
		}

		ej.getList().clear();
		for (int i = 0; i < ef.getList().size(); i++) {
			if (ef.getList().get(i).isFile()) {
				String info = getInfo(ef.getList().get(i));
				JCheckBox jCheckBox = new JCheckBox("" + i + "-" + info);
				ej.getList().add(jCheckBox);
			}//一一对应
		}

		showJB(plist, pshow, pcard, card);
	}

}
