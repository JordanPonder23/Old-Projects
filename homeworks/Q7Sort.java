package com.homework;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Q7Sort {

//	Q7. Sort two employees based on their name, department, and age using the
//	Comparator interface.
	
	public static void main(String[] args) {
		
		ComparatorClass cmp = new ComparatorClass(); 
			Employee emp1 = new Employee(); 
			Employee emp2 = new Employee(); 
			Employee emp3 = new Employee(); 
			Employee emp4 = new Employee(); 
			
			emp1.name= "Bill";
			emp1.dep = "Administration";
			emp1.age = 82;
			
			emp2.name = "Little Jordy JP";
			emp2.dep = "Information Technology";
			emp2.age = 12; 		
			
			emp3.name = "Hater";
			emp3.dep = "Hate";
			emp3.age = 33; 
			
			emp4.name ="Jordan";
			emp4.dep = "Department";
			emp4.age = 16; 
			
			ArrayList<Employee> al = new ArrayList<Employee>();
			al.add(emp4); 
			al.add(emp3);
			al.add(emp2);
			al.add(emp1);
			
			
			Iterator<Employee> custIter=  al.iterator();
			int index = 0; 
			while(index <4) {
			for(int i =0; i<4; i++) {
					cmp.compare(al.get(i), al.get(i)); //returns negative 1 or 0
					
				}
				index += 1; 
			}
		//	cmp.compare(al, emp2);
			
	}
}

 class Employee 
 {
	String name; 
	String dep; 
	int age;
		
}
 
 class ComparatorClass implements Comparator<Employee>{	

	@Override
	public int compare(Employee o1, Employee o2) {
		int o1O = o1.age;  
		int o2O = o2.age; 
		
		if(o1O > o2O)
			return 1; 
		else if(o1O < o2O)
			return -1; 
		else
			return 0; 		
	}  
 }
 
 