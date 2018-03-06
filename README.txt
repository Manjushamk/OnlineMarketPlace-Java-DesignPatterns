Author : Mani Manjusha Kottala
Assignment 3

1. Download all the files and place the contents into your directory on Tesla. Open an instance of putty.

2. Log on to tesla.

3. Run RMI registry on port 2525 using following command :
	% rmiregistry 2525&

4. run the make file MakeControllerServer.sh using command :
	% sh MakeControllerServer.sh
	
			--- or ---
	% javac *.java
	% java -Djava.security.policy=policy MarketPlaceController

5. Open a second instance of putty.

6. run the make file MakeClientView.sh using command :
	% sh MakeClientView.sh
	
			--- or ---
	% java -Djava.security.policy=policy  MarketPlaceClientController
   
7. In the second instance, you will get a menu for selecting 1. User or 2. Admin
		> For 1.User - Enter Id: mkottala
					   Enter password : mkottala
		> For 2.Admin - Enter Id: manju
					    Enter password : manju
				> For the admin a menu appears to select the actions 1.Add Items
																	 2.Delete Items
																	 3.Update Items
																	 4.Browse Items
				 Enter an option.

9. terminate RMI registry by entering command in server instance of putty:
 % fg
 kill using : ctrl + c
