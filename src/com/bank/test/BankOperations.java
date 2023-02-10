package com.bank.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BankOperations {

	public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("==============================================================================");
		System.out.println("=============================== WELCOME TO ABC BANK ==========================");
		System.out.println("==============================================================================");
		

		System.out.println("1  --->   Customer");
		System.out.println("2  --->   Admin");
		System.out.println("==============================================================================");
		System.out.println("\t\t Enter your choice:\n\n"); //escape sequence \t,\n
		int choice=Integer.parseInt(br.readLine());
		
		
		if(choice==1)
		{
			System.out.println("==============================================================================");
			System.out.println("==========================  ENTER LOGIN DETAILS ==============================");
			System.out.println("\t\t Enter your username:");
			String userName=br.readLine();
			System.out.println("\t\t Enter your password:");
			String userPassword=br.readLine();
			System.out.println("==============================================================================");
			
			Connection conn=MysqlConnection.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from accounts where userName=?");
			ps.setString(1,userName);
			ResultSet result=ps.executeQuery();
			String password=null;
				
				while(result.next())
				{
					password=result.getString("userPassword");
				}
			
			
			if(userPassword.equals(password))
			{
				System.out.println("==============================================================================");
				System.out.println("You have successfully logged in!!");
				System.out.println("==============================================================================");
				boolean login=true;
				do
				{
				
				System.out.println("==============================================================================");
				System.out.println("==========================  WELCOME " + userName.toUpperCase() + " ==============================");
				System.out.println("==============================================================================");
				System.out.println("1  --->   Deposit");
				System.out.println("2  --->   Withdraw");
				System.out.println("3  --->   Fund Transfer");
				System.out.println("4  --->   Balance Check");
				System.out.println("5  --->   Acc. Info Check");
				System.out.println("6  --->   Change Password");
				System.out.println("7  --->   Transaction History");
				System.out.println("8  --->   Exit / Logout");
				System.out.println("==============================================================================");				
				System.out.println("\t\t Enter your choice:\n\n"); 
				System.out.println("==============================================================================");
				int operationNumber=Integer.parseInt(br.readLine());
				
				String status=null;
				
				switch(operationNumber)
				{
					case 1: System.out.println("Enter deposit amount:");
							double depositAmount=Double.parseDouble(br.readLine());
							
							if(depositAmount>0)
							{
								conn=MysqlConnection.getConnection();
								ps=conn.prepareStatement("select accBalance from accounts where userName=?");
								ps.setString(1, userName);
								result=ps.executeQuery();
								
								double balance=0.0;
								while(result.next())
								{
									balance=result.getDouble("accBalance");
								}
								
								balance=balance+depositAmount;
								
								ps=conn.prepareStatement("update accounts set accBalance=? where userName=?");
								ps.setDouble(1, balance);
								ps.setString(2, userName);
								
								if(ps.executeUpdate()>0)
								{
									System.out.println("Balance Updated!!");
									System.out.println("New Balance: "+balance);
								}
								else
								{
									System.out.println("Something went wrong!!");
								}
								
							}
							
							System.out.println("Do you want to continue??(Y/N)");
							 status=br.readLine();
							
							if(status.equals("n") || status.equals("N"))
							{
								login=false;
							}
							
							break;
					case 2:	 System.out.println("Enter Withdrawal amount:");
					 double withdrawalAmount=Double.parseDouble(br.readLine());
					 if(withdrawalAmount>0)
					 {
					    conn=MysqlConnection.getConnection();
						ps=conn.prepareStatement("select accBalance from accounts where userName=?");
						ps.setString(1, userName);
						result=ps.executeQuery();
						
						double balance=0.0;
						while(result.next())
						{
							balance=result.getDouble("accBalance");
						}
						
						
						if(balance>withdrawalAmount)
						{
							balance=balance-withdrawalAmount;
							ps=conn.prepareStatement("update accounts set accBalance=? where userName=?");
							ps.setDouble(1, balance);
							ps.setString(2, userName);
							
							if(ps.executeUpdate()>0)
							{
								System.out.println("Balance Updated!!");
								System.out.println("New Balance: "+balance);
							}
							else
							{
								System.out.println("Something went wrong!!");
							}
						}
						else
						{
							System.out.println("Insufficient Balance!!");
						}

					 }
					 System.out.println("Do you want to continue??(Y/N)");
					 status=br.readLine();
						
						if(status.equals("n") || status.equals("N"))
						{
							login=false;
						}
					 	
					 	break;
					 	
					 	
					case 4:	conn=MysqlConnection.getConnection();
							ps=conn.prepareStatement("select accBalance from accounts where userName=?");
							ps.setString(1, userName);
							result=ps.executeQuery();
							
							double balance=0.0;
							while(result.next())
							{
								balance=result.getDouble("accBalance");
							}
							System.out.println("==============================================================================");				
							System.out.println("Current Available Balance:"+balance);
							System.out.println("==============================================================================");				

							 System.out.println("Do you want to continue??(Y/N)");
							 status=br.readLine();
								
								if(status.equals("n") || status.equals("N"))
								{
									login=false;
								}
							 	
							 	break;
							

				}
				
				
				
			}
				while(login);
				System.out.println("Bye.");
				System.out.println("Have a nice day!!");
			}
			else
			{
				System.out.println("Wrong username/password!!");
			}
			
			
		}
		else if(choice==2)
		{
			
		}
		else
		{
			System.out.println("Wrong Choice..");
		}
		
		
		
		
		

	}

}

