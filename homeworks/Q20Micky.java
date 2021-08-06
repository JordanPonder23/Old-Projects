package com.homework;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Q20Micky {

	public static void main(String[] args) {
		
		String filename = "./Data.txt";
		
		String person = "Mickey:Mouse:35:Arizona\r\n" + 
				"Hulk:Hogan:50:Virginia\r\n" + 
				"Roger:Rabbit:22:California\r\n" + 
				"Wonder:Woman:18:Montana";
		
		readObject(filename);
		
	}


	static String readObject(String filename)
	{
			String str ="";
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))

		{
			Object obj = ois.readObject();  // de-serialization
			 str = obj.toString(); 
			System.out.println(obj);
			
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		return str; 
	}
}






