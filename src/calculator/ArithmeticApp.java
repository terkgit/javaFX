package calculator;

import java.util.Scanner;
import java.util.Vector;

public class ArithmeticApp {
	public static void main_old(String[] args) {
		System.out.println("Please enter expression: \n");
		Scanner input= new Scanner (System.in);	
		String str=input.nextLine().replaceAll("\\s","");
		input.close();
		System.out.println("The value of expression "+str+ " is: = "+ String.format("%.2f", evaluate(str,0)) );
	}

	public static Double evaluate(String str, int d) {
		Vector<Double> nums = new Vector<>();
		Vector<String> ops = new Vector<>();
		double num;
		int op = findNextOP(str);
		if ((op==0)&&(str.charAt(op)=='-'))
				op = 1+findNextOP(str.substring(op+1));
		while(op!=-1) {
			if (str.charAt(op)=='(') {
				int endop=findendop(str.substring(op+1));
				num=evaluate(str.substring(op+1,endop+1),d+1);
				nums.add(num);
				str=str.substring(endop+2);
				if(str.length()==0) return calc(nums,ops);
			}
			else {
				num=Double.valueOf(str.substring(0,op));
				nums.add(num);
			}
				String c=str.substring(op,op+1);
				ops.add(c);
				str=str.substring(op+1);
				op=findNextOP(str);
			
		}
		nums.add(Double.valueOf(str));
		
		return calc(nums,ops);
	}

	private static int findendop(String str) {
		int count=1; // counts open '(' 
		int i=0;
		while(count>=1) {
			if (str.charAt(i)=='(')
				count++;
			if (str.charAt(i)==')')
				count--;
			i++;
		}
		return i-1;
	}

	private static Double calc(Vector<Double> nums, Vector<String> ops) {	

		while(ops.contains("*") || ops.contains("/"))
		{
			int i=0;
			int op1=ops.indexOf("*");
			int op2=ops.indexOf("/");
			if(op1==-1)
				i=op2;
			else if(op2==-1)
				i=op1;
			else if(op1<op2)
				i=op1;
			else
				i=op2;
			if(ops.get(i).equals("*")) {
				double res = nums.get(i)*nums.get(i+1);
				nums.remove(i);
				nums.remove(i);
				nums.add(i,res);
			}
			else {// =="/"
				double res = nums.get(i)/nums.get(i+1);
				nums.remove(i);
				nums.remove(i);
				nums.add(i,res);
			}
			ops.remove(i);
		}
		while(ops.contains("+") || ops.contains("-"))
		{
			int i=0;
			int op1=ops.indexOf("+");
			int op2=ops.indexOf("-");
			if(op1==-1)
				i=op2;
			else if(op2==-1)
				i=op1;
			else if(op1<op2)
				i=op1;
			else
				i=op2;
			if(ops.get(i).equals("+")) {
				double res = nums.get(i)+nums.get(i+1);
				nums.remove(i);
				nums.remove(i);
				nums.add(i,res);
			}
			else {// =="-"
				double res = nums.get(i)-nums.get(i+1);
				nums.remove(i);
				nums.remove(i);
				nums.add(i,res);
			}
			ops.remove(i);
		}
		
		return nums.get(0);
	}

	private static int findNextOP(String str) {
		int op=-1;	
		int i=0;
		i=str.indexOf("+");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		i=str.indexOf("(");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		i=str.indexOf(")");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		i=str.indexOf("-");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		i=str.indexOf("*");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		i=str.indexOf("/");
		if( i>=0 ) {
			if( op<0 || i<op) {
				op=i;
			}
		}
		return op;
	}
}