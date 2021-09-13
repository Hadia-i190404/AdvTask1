
import java.io.IOException;
import java.util.*;
import java.util.Random;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s= new Scanner(System.in);
		int input;
		String name;
		String numb; 
		String bnktype;
		int Acc;
		System.out.println("DO YOU WANT TO OPEN AN ACCOUNT OR CLOSE ONE?");
		System.out.println("Enter 1 for Open and 2 for Close");
		System.out.println("If you want to Login to an account Enter 3");
		System.out.println("Enter 4 to perform bank functions");
		System.out.println("Enter 5 to calculate Interest on all savings accounts");
		System.out.println("Enter 6 for account details");
		System.out.println("Enter 7 for deduction history");
		input= s.nextInt();
		if(input == 1)
		{
		
			System.out.println("Enter your name");
			name=s.next();
			System.out.println("Enter your phone numb");
			numb=s.next();
			System.out.println("Enter SAVE for savings account and CHECK for checkings account");
			bnktype=s.next();
			Random rand=new Random();
			Acc=rand.nextInt(100);
			System.out.println("Your Account number is");
			System.out.println(Acc);
			Customer obj=new Customer(Acc,name,numb,bnktype);
			obj.NEWBACCOUNT();
			
		}
		Customer obj=new Customer();
		if(input==2)
		{
			String accno;
			System.out.println("Enter your account number");
			accno=s.next();
			try {
				obj.deletion(accno);
			}
			catch (IOException ioe)
			{
				
			}
		}
		
		if(input==3)
		{
			try {
				obj.login();
				}
				catch(IOException ioe)
				{
				//	ioe.printStrackTrace();
				}
			obj.option();
		}
		if(input==4)
		{
			obj.option();
		}
		if(input==5)
		{
			obj.interest();
		}
		ACCOUNTS acc=new ACCOUNTS();
		if(input==6)
		{
			int accno;
			System.out.println("Enter your account number");
			accno=s.nextInt();
			acc.detail(accno);
			System.out.println("Bank Owner name is Khalid Javed");
		}
		if(input==7)
		{
			int accno;
			System.out.println("Enter your account number");
			accno=s.nextInt();
			acc.alldeductions(accno);
		}
	}

}


