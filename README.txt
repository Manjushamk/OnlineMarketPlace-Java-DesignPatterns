Author : Mani Manjusha Kottala
Assignment 1

1. Download all the files () and place the contents into your directory on Tesla. Open an instance of putty.

2. Log on to tesla.

3. Run RMI registry on port 2525 using following command :
	% rmiregistry 2525&

4. run the make file MakeControllerServer.sh using command :
	% sh MakeControllerServer.sh
	
			--- or ---
	% javac MarketPlaceController.java MarketPlace.java MarketPlaceModel.java
	% java -Djava.security.policy=policy MarketPlaceController

5. Open a second instance of putty.

6. run the make file MakeClientView.sh using command :
	% sh MakeClientView.sh
	
			--- or ---
	% javac MarketPlaceView.java
	% java -Djava.security.policy=policy MarketPlaceView
   
7. In the second instance, you will get a menu for selecting 1. Login or 2. Register User
		> For 1.Login - Enter User Id: mkottala
							Enter password : mkottala
		> For 2.Register User - User Name: Manjusha
								User Id: manju
								Enter Password: manju

9. terminate RMI registry by entering command in server instance of putty:
 % fg
 kill using : ctrl + c