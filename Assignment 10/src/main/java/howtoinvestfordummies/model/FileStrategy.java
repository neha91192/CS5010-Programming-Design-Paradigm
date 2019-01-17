package howtoinvestfordummies.model;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents File persistence strategy for the stock application. This class is responsible for
 * saving model in a file where data is represented in JSON. It leverages {@link ObjectMapper} to
 * write values in a file or to read and map in the class from the file.
 */
public class FileStrategy implements IPersistenceStrategy {

  private static final String PORTFOLIOS = "portfolios";
  private static final String NAME = "name";
  private static final String INVESTMENTS = "investments";
  private static final String PURCHASE_DATE = "purchaseDate";
  private static final String INVESTMENT_NAME = "investmentName";
  private static final String SHARES = "shares";
  private static final String INVESTMENT_COST = "investmentCost";
  private static final String COMMISSION_FEES = "commissionFees";

  /**
   * Takes name of the file and model to persist data in that file.
   *
   * @param model of type InvestmentStrategies.
   * @param name  of the file in String.
   */
  @Override
  public void save(InvestmentStrategies model, String name) {
    ObjectMapper mapper = new ObjectMapper();
    File file = new File(name);
    try {
      mapper.writeValue(file, model);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets JSON data from the file and maps it into collection of portfolios.
   *
   * @param name of the file in String.
   * @return map of portfolio.
   */
  @Override
  public Map<String, IPortfolio> restore(String name) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, List<LinkedHashMap>> data = new HashMap<>();
    Map<String, IPortfolio> actualData = new HashMap<>();
    try {
      byte[] mapData = Files.readAllBytes(Paths.get(name));
      mapper.configure(DeserializationFeature
              .ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT.FAIL_ON_UNKNOWN_PROPERTIES, false);
      data = mapper.readValue(mapData, data.getClass());
      actualData = convertFileData(data);
    } catch (Exception e) {
      return actualData;
    }
    return actualData;

  }

  /**
   * This method is responsible for converting file data into Map. If the type of portfolio is
   * Simple, it adds simple portfolio object in the resultMap, otherwise recurring portfolio. This
   * method helps in restoring data from the file.
   *
   * @param data containing data fetched from file.
   * @return data to be stored in model for initialization.
   */
  private Map<String, IPortfolio> convertFileData(Map<String, List<LinkedHashMap>> data) {
    Map<String, IPortfolio> resultMap = new HashMap<>();
    if (data != null) {
      List<LinkedHashMap> portfolios = data.get(PORTFOLIOS);
      for (Map map : portfolios) {
        String name = (String) map.get(NAME);
        List<LinkedHashMap> stocks = (List<LinkedHashMap>) map.get(INVESTMENTS);
        LinkedHashMap dataMap = (LinkedHashMap) map.get("data");
        IPortfolio portfolio;
        List<IInvestment> stockObjs = new ArrayList<>();
        for (Map investment : stocks) {
          Date purchaseDate = new Date((Long) investment.get(PURCHASE_DATE));
          IInvestment stockObj = new Stock((String) investment.get(INVESTMENT_NAME),
                  (double) investment.get(SHARES),
                  purchaseDate, (double) investment.get(INVESTMENT_COST));
          stockObj.setCommissionFees((double) investment.get(COMMISSION_FEES));
          stockObjs.add(stockObj);
        }
        if (dataMap.get("type").equals(PortfolioType.SIMPLE.name())) {
          portfolio = new Portfolio(name, stockObjs);
        } else {
          Integer frequency = (Integer) dataMap.get("frequencyValue");
          Date startDate = new Date((Long) dataMap.get("transactionStartDate"));
          Date endDate = null;
          if (dataMap.get("transactionEndDate") != null) {
            endDate = new Date((Long) dataMap.get("transactionEndDate"));
          }
          portfolio = new RecurringPortfolio(name, frequency, startDate, endDate, stockObjs);
        }
        resultMap.put(name, portfolio);
      }
    }
    return resultMap;
  }
}

