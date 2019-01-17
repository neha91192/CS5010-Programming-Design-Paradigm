Command to execute:

assignment8.jar is present in res folder. To run the application:

java -jar assignment8.jar



------------------------------------

User Interaction Details:
1. When user starts application, he/she gets 4 options -
1. Create Portfolio
2. Buy Stock for a Portfolio at a given date.
3. Find cost and value of a portfolio for a given date
4. View Portfolio composition
User has to input number designated to either of these 4 options to interact with the
application.

-------------------------------------
If user inputs 1:
User will be asked to input a portfolio name. If this portfolio is already
present, user will be asked to input a new portfolio name again. If this is a new portfolio, system
will create a new portfolio instance and returns the prompt indicating success of the operation.
If user wishes to go back to another menu, he/she can press #.

--------------------------------------
If user inputs 2:
User will be displayed an option to list portfolios by pressing "l" or continue with buying
if user is aware of which portfolio needs to be used, he can press "c" to continue.

--------------------------------------
If user enters l:
A list of names of portfolio will be displayed and then user will be asked to use any of
these portfolio.
After user selects portfolio, user will add company symbol, amount in usd, date in
MM-dd-YYYY HH:mm 24-Hrs format. If any of these inputs are invalid, user will be notified about
the error.
If the shares of stock were bought successfully, user will be notified about the successful
transaction and current details will be printed for the portfolio.
If user wishes to exit, he/she will have to
select option # to go back to main menu.

--------------------------------------
If user enters 3: User may check cost basis and value of the portfolio for a given date. If date
is invalid or portfolio is not found, appropriate message is given back to the user.
If the input is valid, the details are provided in the following format:
Total Portfolio Investment Cost: 2000.0 USD
Total Portfolio Investment Value: 1965.65 USD
If user inputs a date for which no investment was made the details will be provided in the
following format:
Total Portfolio Investment Cost: 0.00 USD
Total Portfolio Investment Value: 0.00 USD

--------------------------------------
If user enters 4: User will be able to view entire composition of any provided portfolio. If the
portfolio name is valid, the format of the output would be like this:
Current value of your portfolio:
Portfolio Name: A
1. FB:
	Investment Cost: 2000.0 USD
	Investment Value: 1929.26 USD
	Shares: 13.629548861932669
	Date of Purchase: Thu Nov 08 12:00:00 EST 2018
Total Portfolio Investment Cost: 2000.0 USD
Total Portfolio Investment Value: 1965.65 USD
Today's date: Thu Nov 15 13:00:12 EST 2018
If no stocks has been purchased in Portfolio then output will look like this:
Portfolio Name: A
Current Portfolio Investment Cost: 0.0 USD
Current Portfolio Investment Value: 0.0 USD
Today's date: Thu Nov 15 13:32:04 EST 2018
To go back to the main menu, user has to press "#".

--------------------------------------
To Quit:
User has to press "q" or "quit" to quit. Additionally, if user is in sub-menu,
user will have to press back option("#") before quitting.
