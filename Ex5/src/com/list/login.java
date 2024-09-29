package com.list;

public class login {

	
	public boolean checkUser(User u) 
	{
		if(u.getU_name().equals("Nandhini"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
