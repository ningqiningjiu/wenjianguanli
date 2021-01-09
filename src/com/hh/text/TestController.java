package com.hh.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class TestController {

	@Test
	public void testShow() throws IOException, FileNotFoundException {
		BufferedReader buff = new BufferedReader(new InputStreamReader(new FileInputStream("d:\\hello\\b.txt"),"gbk"));
		String line="";
		while((line=buff.readLine())!=null) {
			System.out.println(line);
		}
	}
	
	@Test
	public void testUpdate() throws IOException, FileNotFoundException {
		FileWriter fw = new FileWriter("d:\\hello\\b.txt");
		fw.write("测试");
	}
	
	@Test
	public void testDelete() throws IOException, FileNotFoundException {
		File file = new File("d:\\hello\\b.txt");
		file.delete();
	}
}
