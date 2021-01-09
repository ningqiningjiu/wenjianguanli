package com.hh.initframe;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public abstract class InitFrame extends JFrame {

	public void init(int width, int height, String title, String iconPath) {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Dimension screen = tool.getScreenSize();
		int screenX = (int) screen.getWidth();
		int screenY = (int) screen.getHeight();//让窗口剧中显示

		this.setBounds((screenX - width) / 2, (screenY - height) / 2, width, height);
		if (title != null) {
			this.setTitle(title);
		}
		if (iconPath != null) {
			InputStream is = InitFrame.class.getClassLoader().getSystemResourceAsStream(iconPath);
			try {//处理异常
				this.setIconImage(ImageIO.read(is));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.addPart();
		this.addLis();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	public abstract void addPart();//抽象方法

	public abstract void addLis();//

}
