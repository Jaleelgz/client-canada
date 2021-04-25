import java.util.Scanner;
class SumOfDigits{
	int sum=0;
	public static void main(String args[])
	{
		int num=0;
		while(num==0)
		{
		try
		{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter an integer number:");
		num=sc.nextInt();
		}
		catch(Exception e)
		{
		System.out.println("Input Error!");
		}
	}
		SumOfDigits obj=new SumOfDigits();
		int result=obj.sumf(num);
		System.out.println("Result: "+result);
		System.out.println("End of Processing...");
		}
	int sumf(int num)
	{
		sum=num%10;
		if(num==0)
		{
			return 0;
		}
		else{
			return sum+sumf(num/10);
		}
	}
		
		
}
