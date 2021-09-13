
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.Month;
import java.time.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ACCOUNTS {
	
	private int blnc;
	private String name;
	private String phn;
	private String bnktype;
	String Acc_date;
	private int Accno;
	int transactions=0;
	public ACCOUNTS()
	{
		this.blnc=500;
		this.name=null;
		this.phn=null;
		this.Acc_date=null;
		this.Accno=0;
		this.bnktype=null;
	}
	public ACCOUNTS(int bln,String nme,String ph,String dte,int Acc,String bnk)
	{
		this.blnc=bln;
		this.name=nme;
		this.phn=ph;
		this.Acc_date=dte;
		this.Accno=Acc;
		this.bnktype=bnk;
	}
	public void setblnc(int bln)
	{
		blnc=bln;
	}
	public int getblnc()
	{
		return blnc;
	}
	public void setname(String nme)
	{
		name=nme;
	}
	public String getname()
	{
		return name;
	}
	public void setphn(String phne)
	{
		phn=phne;
	}
	public String getphn()
	{
		return phn;
	}
	public void setAcc_date(String dte)
	{
		Acc_date=dte;
	}
	public String getAcc_date()
	{
		return Acc_date;
	}
	public void setAccno(int no)
	{
		Accno=no;
	}
	public int getAccno()
	{
		return Accno;
	}
	public void setbnktype(String bnk)
	{
		bnktype=bnk;
	}
	public String getbnktype()
	{
		return bnktype;
	}
	
	public void Display()
	{
		System.out.println(name);
		System.out.println(Accno);
		System.out.println(phn);
		System.out.println(blnc);
		System.out.println(Acc_date);
	}
	public void filecheck(int accno) throws IOException
	{
		String s;
		String acc="";
		boolean chck=false;
		try {
			File info=new File("Details.txt");
			FileReader fr=new FileReader(info);
			BufferedReader br = new BufferedReader(fr);
			while((s=br.readLine())!=null)
			{
				StringTokenizer tok=new StringTokenizer(s," ");
				acc=tok.nextToken();
				if(accno==Integer.parseInt(acc))
				{
					System.out.println("Account exists");
					Accno=accno;
					name=tok.nextToken();
					phn=tok.nextToken();
					Acc_date=tok.nextToken();
					blnc=Integer.parseInt(tok.nextToken());
					bnktype=tok.nextToken();
					chck=true;
					break;
				}
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("No Data inserted");
			
		}
		if(chck==false)
		{
			System.out.print("No such Account exists");
		}
	}
	public void makedeposit(int accno,int amount)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		blnc+=amount;
		System.out.println("New Balance is "+blnc);
	}
	public void swithdrawal(int accno,int amount)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		if(amount > blnc)
		{
			System.out.println("Amount exceeds the balance");
		}
		else
		{
			blnc=blnc-amount;
			System.out.println("Withdrawal Succesful, Current Balance is " + blnc);
		}
	}
	public void cwithdrawal(int accno,int amount)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		if(blnc<amount)
		{
			System.out.println("Amount exceeds the balance");
		}
		else
		{
			
			if(transactions>2)
			{
				System.out.println("Transaction fee of Rs 10 will be charged as well");
				blnc=blnc-amount;
				blnc=blnc-10;
				System.out.println("Withdrawal Succesful, Current Balance is " + blnc);
			}
			else
			{
				blnc=blnc-amount;
				System.out.println("Withdrawal Succesful, Current Balance is " + blnc);
				transactions++;
			}
		}
	}
	public void checkbalance(int accno)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		System.out.println(name);
		System.out.println(blnc);
	}
	public void printstatement(int accno,int amount)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		if(amount>blnc)
		{
			System.out.println("Exceeds current balance");
		}
		else
		{
		System.out.println("Account number is "+Accno);
		System.out.println("Name of client is "+name);
		System.out.println("Contact Information is "+phn);
		System.out.println("Date of creation is "+Acc_date);
		LocalDate date=LocalDate.now();
		System.out.println("Date of Transaction "+date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		System.out.println("Transaction Amount is "+ amount);
		if(bnktype=="CHECK")
		{
			if(transactions>2)
		{
			blnc=blnc-amount;
			blnc=blnc-10;
		}
		else
		{
			blnc=blnc-amount;
			transactions++;
		}
		}
		else
		{
			blnc=blnc-amount;
		}
		
		System.out.println("Remaining Balance is "+blnc);
	
		}
	}	
	public void transferamount(int accno,int accno1,int amount)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		if(amount>blnc)
		{
			System.out.println("Exceeds current balance");
		}
		else
		{
			try {
				
				filecheck(accno1);
			}
			catch(IOException ioe)
			{
				
			}
			System.out.println("Amount transferred");
			blnc=blnc-amount;
			System.out.println("Your Current balance is"+ blnc);
		}
	}
	public void zakaat(int accno)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		if(bnktype=="SAVE")
		{
			if(blnc>20000)
			{
				System.out.println((blnc*2.5)/100);
			}
			else
			{
				System.out.println("Zakat not applicable");
			}	
		}
		else
		{
			System.out.println("Zakat is not applicable for Checkings Account");
		}
		
	}
	public void calculateinterest(int accno) throws ParseException
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
/*		DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date=LocalDate.now();
		String date2=date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalDate dte1=LocalDate.parse(Acc_date,dtf);
		LocalDate dte2=LocalDate.parse(date2,dtf);
		long daysBetween=Duration.between(dte1, dte2).toDays();
		System.out.println("Days: "+daysBetween);
	*/
		int P=500;
		int t= 1;
		int r=8;
		System.out.println("Interest is "+ P*(1+r*t));
		
	}
	public void alldeductions(int accno)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		System.out.println("Account number is "+Accno);
		System.out.println("Name of client is "+name);
		System.out.println("Contact Information is "+phn);
		System.out.println("Date of creation is "+Acc_date);
		zakaat(accno);
		try {
			calculateinterest(accno);
		}
		catch(ParseException e)
		{
			
		}
		System.out.println("Balance is "+ blnc);
	}
	
	public void detail(int accno)
	{
		try {
			
			filecheck(accno);
		}
		catch(IOException ioe)
		{
			
		}
		System.out.println("Account number is "+Accno);
		System.out.println("Name of client is "+name);
		System.out.println("Contact Information is "+phn);
		System.out.println("Date of creation is "+Acc_date);
		System.out.println("Balance in account is "+blnc);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}



