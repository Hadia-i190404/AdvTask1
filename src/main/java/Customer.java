
import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
public class Customer {

	private String name;
	private int AccNo;
	private String PNumb;
	private String bnktype;
	private int blnc;
	ACCOUNTS accobj=new ACCOUNTS();
	public Customer( )
	{
		this.AccNo=0;
		this.name=null;
		this.PNumb=null;
		this.bnktype=null;
		this.blnc=500;
	}
	
	public Customer(int Acc, String Cname, String Phn,String bnk)
	{
		this.AccNo=Acc;
		this.name=Cname;
		this.PNumb=Phn;
		this.bnktype=bnk;
		this.blnc=500;
	}
	public void NEWACCOUNT () 
	{
		accobj.setname(name);
		accobj.setAccno(AccNo);
		accobj.setphn(PNumb);
		accobj.setblnc(500);
		accobj.setbnktype(bnktype);
		LocalDate date=LocalDate.now();
		accobj.setAcc_date(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		//File Handling
/*		File info=new File("Details.txt");
		FileWriter wfile=new FileWriter(info,true);
		Integer i= new Integer(AccNo);
		Integer n=new Integer(accobj.getblnc());
		wfile.write(name+","+String.valueOf(i)+","+PNumb+","+accobj.getAcc_date()+","+String.valueOf(n));
		wfile.close();
		
*/
	}
	public void FILEHANDLING () throws IOException
	{
		File info=new File("Details.txt");
		FileWriter wfile=new FileWriter(info,true);
		Integer i= new Integer(AccNo);
		Integer n=new Integer(accobj.getblnc());
		wfile.write(String.valueOf(i)+" "+name+" "+PNumb+" "+accobj.getAcc_date()+" "+String.valueOf(n)+" "+accobj.getbnktype()+"\n");
		wfile.close();
		System.out.println("Successful");
	}
	public void NEWBACCOUNT()
	{
		NEWACCOUNT();
		try {
		FILEHANDLING ();
		}
		catch(IOException ioe)
		{
		//	ioe.printStrackTrace();
		}
	}
	public void login() throws IOException
	{
	/*	try {
			File info=new File("Details.txt");
			Scanner myReader=new Scanner(info);
			while(myReader.hasNextLine())
			{
				String data = myReader.nextLine();
				System.out.println(data);
			}
			myReader.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("An error occured");
			e.printStackTrace();
		}
	*/
		
		Scanner sc= new Scanner(System.in);
		int input;
		System.out.println("Enter your Account No");
		input= sc.nextInt();
		File info=new File("Details.txt");
		String [] word=null;
		FileReader fr=new FileReader(info);
		BufferedReader br = new BufferedReader(fr);
		String s;
		
		Integer i=input;
		String str = i.toString();
		boolean chck=false;
		while((s=br.readLine())!=null)
		{
			word=s.split(" ");
			for(String wor : word)
			{
				if(wor.equals(str))
				{
					chck=true;
				}
			}
		}
		if (chck==false)
		{
			System.out.println("No such Account exists");
		}
		if (chck==true)
		{
			System.out.println("Login Successful");
		}
	}
	public void option()
	{
		Scanner sc= new Scanner(System.in);
		int input;
		System.out.println("Enter 1 for makedeposit");
		System.out.println("Enter 2 for makewithdrawal");
		System.out.println("Enter 3 for checkbalance");
		System.out.println("Enter 4 for printstatement");
		System.out.println("Enter 5 for transferamount");
		System.out.println("Enter 6 for calculating zakat");
		System.out.println("Enter 7 for displayalldeductions");
		System.out.println("Enter 8 for calculating interest");
		input= sc.nextInt();
		
		if(input==1)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			int amount;
			System.out.println("Enter amount you want to deposit");
			amount=sc.nextInt();
			accobj.makedeposit(accno,amount);
		}
		if(input==2)
		{
			int bnk;int amount; int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			System.out.println("Enter 1 if for Savings Account, 2 for Checking Account");
			bnk=sc.nextInt();
			System.out.println("Enter amount you want to withdraw");
			amount=sc.nextInt();
			
			if(bnk==1)
			{
				accobj.swithdrawal(accno,amount);
			}
			if(bnk==2)
			{
				accobj.cwithdrawal(accno,amount);
			}
		}
		if(input==3)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			accobj.checkbalance(accno);
		}
		if(input==4)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			int amount;
			System.out.println("Enter amount you want to transact");
			amount=sc.nextInt();
			accobj.printstatement(accno,amount);
		}
		if(input==5)
		{
			int accno;int accno1;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			System.out.println("Enter accno you want to transfer money to");
			accno1=sc.nextInt();
			int amount;
			System.out.println("Enter amount you want to transact");
			amount=sc.nextInt();
			accobj.transferamount(accno,accno1,amount);
		}
		if(input==6)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			accobj.zakaat(accno);
		}
		if(input==7)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			accobj.alldeductions(accno);
		}
		if(input==8)
		{
			int accno;
			System.out.println("Enter your accno");
			accno=sc.nextInt();
			try
			{
				accobj.calculateinterest(accno);
			}
			catch (ParseException e)
			{
				
			}
			
		}
	}
	public void deletion(String accno) throws IOException
	{
		System.out.println("Account Closed Successfully");
		PrintWriter pw=new PrintWriter("output.txt");
		BufferedReader br1=new BufferedReader(new FileReader("details.txt"));
		String line1=br1.readLine();
		while(line1!=null)
		{
			boolean chck=false;
		//	while(accno!=null)
			{
				if(line1.equals(accno))
				{
					chck=true;
					break;
				}
			}
		
		if(!chck)
		{
			pw.println(line1);
		}
		pw.flush();
		br1.close();
		pw.close();
		
		}
		
	}
	public void interest()
	{
		int blnc=500;
		int time=12;
		int sint=500;
		double interest= (sint*100)/(blnc *time);
		System.out.println("Interest rate of all saving accounts is "+interest);
	}
	public int getAccNo()
	{
		return AccNo;
	}
	public String getname()
	{
		return name;
	}
	public void Display()
	{
		System.out.println(name);
		System.out.println(AccNo);
		System.out.println(PNumb);
	}
	
	public static void main(String[] args) {
		/*Customer obj=new Customer(12,"Hadia",6401);
		obj.Display();
		ACCOUNTS chck= new ACCOUNTS(12,"Hadia",6401,1200);
		chck.checkbalance();
	*/}

}


