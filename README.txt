Author : Mani Manjusha Kottala
Assignment 6

1. Download all the files and place the contents into your directory on Tesla.

2. Machine - 10.234.136.55 acts as the Server and other 5 machines (10.234.136.56 ,10.234.136.57 ,10.234.136.58 ,10.234.136.59 ,10.234.136.60 ) can be clients. So log on to 10.234.136.55 for running the Server and others for client.

3. On Machine 10.234.136.55, run RMI registry on port 2526 using following command :
	% rmiregistry 2526&

4. On Machine 10.234.136.55 , run the make file MakeControllerServer.sh using command :
	For the Database connection, the makefile should contain the path for the jar file, So in the shell file MakeControllerServer.sh, add the path for the jar file and run the following command.
	% sh MakeControllerServer.sh
	
			--- or ---

	% javac -cp "/home/mkottala/OOAD/Assignment4/mysql-connector.jar" *.java
	% java -cp ".:/home/mkottala/OOAD/Assignment4/mysql-connector.jar" -Djava.security.policy=policy MarketPlaceController

5. Open a instance of putty for any client machines.

6. run the make file MakeClientView.sh using command :
	% sh MakeClientView.sh
	
			--- or ---
	% java -Djava.security.policy=policy  MarketPlaceClientController
   
7. In the CLient instances, you will get a menu for selecting 1. User or 2. Admin
		> For 1.User - Enter Id: mkottala
					   Enter password : mkottala
				> For the admin a menu appears to select the actions 1.Browse Items
																	 2.Checkout (Purchase cart Items)
																	 3.Add Item to Cart
																	 4.Display cart

				 Enter an options 1,2,3 or 4 for calling their respective functions, anything other than those options for exit
				 Respective methods are called from server using User role based access
		> For 2.Admin - Enter Id: manju
					    Enter password : manju
				> For the admin a menu appears to select the actions 1.Add Items
																	 2.Delete Items
																	 3.Update Items
																	 4.Browse Items
																	 5.Add Admin
																	 6.Add Customer
																	 7.Remove Customer

				 Enter an any of the options 1,2,3,4,5,6 or 7 for calling their respective functions, anything other than those options for exit
				 Respective methods are called from server using Admim role based access

9. terminate RMI registry by entering command in server instance of putty:
 % fg
 kill using : ctrl + c
