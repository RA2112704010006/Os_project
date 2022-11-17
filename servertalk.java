import java.io.*;
import java.net.*;
import java.util.Scanner;

public class servertalk{
	
	public static void main(String args[])throws Exception{
	Scanner sc=new Scanner(System.in);
	System.out.println("enter 1 for chatting and 2 for sending file using aes encyption and 3 for des encyprion");
		int option= sc.nextInt();
			Server s=new Server();
		switch(option){	
		case 2:	
			s.file_aes();
			break;
		case 1:	
			s.ser();
			break;

		case 3:
			s.file_des();
			break;	
}
}
}