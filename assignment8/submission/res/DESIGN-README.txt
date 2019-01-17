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

