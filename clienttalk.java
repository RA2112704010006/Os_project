import java.io.*;
import java.net.*;
import java.util.Scanner;
public class clienttalk{


	public static void main(String args[]) throws Exception{
		Scanner sc=new Scanner(System.in);
		System.out.println("enter 1 for chatting and 2 for sending file using aes encyption and 3 for des encyprion");
		int option=sc.nextInt();
			Client c=new Client();
		switch(option){	
		case 2:	
			c.file_aes();
			break;
		case 1:	
			c.clt();
			break;

		case 3:
		
			c.file_des();
			break;	
		}
	}
}