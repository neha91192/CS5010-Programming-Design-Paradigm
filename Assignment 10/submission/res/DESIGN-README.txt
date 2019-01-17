How to Invest for Dummies application:

Required Features for Assignment 8:
1. Allow a user to create one or more portfolios and examine its composition.
2. Buy shares of some stock in a portfolio worth a certain amount at a certain date.
Buying should be allowed only in working hours.
3. Determine the total cost basis and total value of a portfolio at a certain date.

Design Considerations:

1. This application data is limited to User session. Whenever a user starts application,
he/she can indefinitely interact with the application which includes create as many
portfolios as possible, add stocks to the portfolios, and view details of each.
But as soon as user inputs "quit" or "q"(ignoring case), the application ends and refreshes
all the interaction data.

----------------------------------------------------------------------------------------------------
Controller Design:

To integrate different options, Command pattern has been used. If in future any of the features
need to extended or added, changes will be restricted to limited number of class as handling model
interaction for user features is assigned to different classes all implementing
StockInvestmentCommand Interface.
Following are the classes implementing "StockInvestmentCommand": "CreatePortfolio", "BuyStock",
"DetermineCostAndValue", "ViewPortfolioComposition".
Depending upon user input and respective classes stored values in the form of Funtion object
in commands map of InvestmentController, these classes will be instantiated.


----------------------------------------------------------------------------------------------------

Model Design:

1. Common Interface for this managing investment is "InvestmentStrategies". This interface serves
purpose of managing all kinds of investments such as creating a common portfolio and viewing
its details. "InvestmentStrategiesModel" is its implementation class.

For this assignment, we have added another interface "StockInvestmentStrategies" which is extending
"InvestmentStrategies". This investment is specific to stocks and therefore includes method relating
to buying of stocks.

2. InvestmentStrategiesModel contains many portfolios of type "IPortfolio" and Portfolio contains
many objects of type of "IInvestment". For this assignment, we have used subclass version of it
called "Stock" which is of type "IInvestment".

3. Business logic is kept in the class "TradingInformation". If any other type of trading related
validation is required, a new class can extend "ITradingInformation".
4.To interact with different endpoints, we have added adapters. Adapters are singleton instance and
can be used in the application whenever stock data is required. "LocalStockAdapterImpl" fetches
data from local files and "RemoteStockAdapterImpl" makes webapi call.

----------------------------------------------------------------------------------------------------

Stock Data:

We have used local data for this assignment. Stock data is available for 5 companies - GOOG, FB,
MSFT, SBUX, AAPL year 2014-2018.
User will be prompted to use the specified date range only. If user wishes to add more entries,
user may update the respective csv files for the company or if user wishes to add a new company,
he/she may create a new csv file with the symbol name ending with ".csv" extension. In adding stock
data for local setup, it is expected that user will not want to try buying stocks for all the
companies and therefore, the application can switch to remote api call to fetch data in later
versions.
For buying stock, the lowest price in the csv file for the past date would be considered. For
calculating value for the portfolio, closing price for the date would be considered.


----------------------------------------------------------------------------------------------------
How to Invest for Dummies application v2: Changes and new features supported for Assignment 9:

1. Allowed user to invest a fixed amount in a portfolio which can add multiple stocks and amount of
this investment will be divided between these stocks as per the weights specified by user.
2. Added support to let user choose if they want to go with equal weights and in that case the
weights will be assigned equally amongst all the stocks.
3. Support for High level investment strategies: Currently, Dollar cost averaging has been added as
a new feature where user can select the strategy and start investing in that way.
Additionally, improved flexibility for the user to add flexible options to build request data that
needs to be sent to the model to process the same.
4. User can start recurring investment and so at any time user checks the value of the portfolio
supporting recurring investments, it will return the calculated value for the number of transactions
that could happen in the provided date.
5. Added support for taking commission fees from user. It is taken as a percentage number. User may
choose to skip putting commission fees at any stage.
6. User may choose to directly buy the stock as before but with added option of putting commission
fees in the request.
7. Added flexibility to switch between remote API and local data. In case data is not available at
remote location, application would fetch data from local setup.
8. Added a new view interface for better MVC pattern approach.


Design changes:
1. Added a new IPortfolio implementation called RecurringPortfolio to support recurring investment
activity and store the data configured by user to operate on the same in every interval of purchase.
Whenever user initiates any High level investment strategy, a new portfolio of this type is created.

Advantage and reason for this change:
The advantage of creating a new implementation can be seen in a situation where user wants to check
the value and cost of investment in a portfolio. The investment model stores collection of
portfolios of type IPortfolio which is an interface. So whenever user queries to check the value,
it invokes the respective implementation and the value is returned dynamically from the new
implementation class.

At the controller level, added a new option (5) to start high level investment. To add this option,
added a new implementation of StockInvestmentCommand interface. The list of high level investments
would be displayed where user may choose the one and go ahead with that strategy.
User would then get this option from the view to input configuration details to create this type
of investment portfolio.

2. To support user choose equal stocks weights, asked user to go with this option and then stocks
would be set to equal weights depending upon the number of stocks given. To support this change,
for configuring weights, supporting multiple stocks at creation, taking commission fees, a new
data object called Transaction has been added. To flexibly set values in this object,
TransactionBuilder has been added. So every details related to investment transaction is added there
and passed to the model.

Advantage and reason for this change:
This provides flexibility to support any future enhancements. If any further data needs to be
provided to the model, a new field can be added in the Transaction object. For example, a change
like commission fees handling can be easily supported without changing the method definition in the
model.

3. To support different High Level Investment Strategy in future, a new Interface for the same
has been added in the model - HighLevelInvestmentStrategy. Any strategy class implementing this
interface has to override initiate method which takes Transaction details and sets up the portfolio
for the same. The current version supports DollarCostInvestmentStrategy, but in future, if in case
a new strategy needs to be added, a new implementation can be added for HighLevelInvestmentStrategy.
At InvestmentStrategies Interface level, new method has been added in the interface to list these
strategies fetched from model, and whenever selects the option, depending upon that, HighLevelInvest
mentStrategyFactory would return implementation for that strategy type.

Advantage:
Applying strategy pattern to support this feature helped in plugging new InvestmentStrategy without
any major changes. Whenever a new strategy needs to be added, only a new implementation is required
to be added which goes with the SOLID principle - open for extension, closed for modification.

4. To let user check current value, no major changes required in this method. This method calls
method of portfolio interface to return calculated values. Since a new portfolio implementation was
added for the same interface, the new implementation handled the computation required for recurring
investments. If the commission has been added, the cost value would updated to reflect these
changes.


5. To support commission fees in the system, added a new method in the StockInvestmentStrategies
Interface to take Transaction object and buy stocks with the commission fees. Controller notified
these changes and depending upon if user has given the input from the console about the commission,
this method would be called. In fact, this method can easily support new changes and can be called
for any new improvements in the model.

Advantage:
Reduced the overhead of changing the existing method to buy stocks. It makes sense to not play with
the existing working code which has been tested at jUnit level as well. Changing a method definition
would have caused a lot more as it would have led to changes in the test cases for the same, all the
controllers where this method was called. The existing implementation can easily plug between these
two exposed methods now depending upon the need from the controller.

6. At controller level, added changes in buy stock where user can now input if he/she has to take
any commission input. If yes, it will be taken in the input, otherwise not and would go ahead with
the plain old buy stock implementation. The new method addition has been explained in 5th point.
This ensures adding a new commission feature support with minimum changes.

7. This feature was already supported in the previous assignment. To support RemoteStockAdapterImpl,
a call to this implementation was made. Whenever a request is incomplete through this means, a call
to local adapter will be made which stores the replicated data.

8. Added a new View Interface to keep methods specific to Read and Write functions. This change
helped in segregating view logic from controller and ensured easy configuration of some new type of
interface for this application.

----------------------------------------------------------------------------------------------------
Change log:

How to Invest for Dummies application v3: Changes and new features supported for Assignment 10:

1. The ability to save a portfolio to file.
2. The ability to retrieve a portfolio from a file.
3. The ability to save an investment strategy to file.
4. The ability to retrieve an investment strategy from a file and apply it to a portfolio.

We added an option to save the current portfolios created by user in a file known as "user".
This file stores data in json format which includes details about portfolios and list of investments
made in the same. In order to be able to add any new data in this file and be able to see in the
view, please add details corresponding to stocks in "investments list" and details corresponding
to portfolios in "data".

To implement this feature, we created a new interface called Persistable with provides this
enhancement. InvestmentStrategiesModel interface extends this Interface to provide additional
capability of save and restore methods in the model implementation class. Whenever a request to
save data is received from controller, model saves this in a file mentioned in the request.

In the save and restore logic of the model, we implemented strategy pattern to decide which
persistence implementation needs to be called. For this assignment, we are implementing
FileStrategy which provides the way to handle data to and fro from file to model.

Investment Strategy can be saved when user saves the data from view. At model level, we added
save feature to store recurring portfolio which also resides in model class. So whenever user wants
to see past investment strategy such as "dollar cost", user can ask to restore this strategy from
the file and see the current value in the check cost basis and value option from the view.


New GUI view:
1. The ability to create a new portfolio
2. The ability to add stocks to an existing portfolio
3. The ability to buy stocks by specifying actual amount, and date (with or without commission fees)
4. The ability to buy stocks by specifying an amount, date and weight distribution (with or
without commission fees)
5. The ability to create an investment strategy and apply it to a portfolio
6. The ability to query the cost basis and value of a portfolio at a certain date
7. The ability to save and retrieve portfolios and strategies from files

To add a new view, we created a new interface, IInvestmentGUIView which contains methods and
features specific to GUI view (interface segregation). The common view interface IInvestmentView is
shared amongst TextBased view and GUI view.

At view level, user can click on save to persist data at any time.
To retrieve high level strategy created by user in the past, we added Retrieve option in Determine
cost basis and value in view. User will get recurring portfolios in the result box and then user
can choose the portfolio name for which cost and value needs to be determined.

Use of external library:

To support saving of model data in a file in JSON format. We used the following jar imported in
maven dependencies:
jackson-databind-2.9.7.jar
jackson-core-2.9.7.jar
jackson-annotations-2.9.7.jar

