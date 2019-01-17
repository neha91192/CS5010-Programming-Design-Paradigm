package howtoinvestfordummies.view;

import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.SpinnerModel;
import javax.swing.JSpinner;
import javax.swing.WindowConstants;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.SpinnerDateModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Represents a class with Grapgical User Interface implementation. The main objective of this class
 * is to provide GUI to take inputs from user and call the controller respectively.
 */
public class InvestmentGUIView extends JFrame implements IInvestmentGUIView,
        ItemListener {

  private JPanel bodyPanel;

  private JLabel fileSaveDisplay;
  private JButton saveButton;

  // Create Portfolio
  private JTextField portfolioField1;
  private JButton createButton1;
  private JLabel display1;

  // Buy Stocks
  private JTextField portfolioField2;
  private JTextField companyField2;
  private JTextField amountField2;
  private JCheckBox commCheckBox2;
  private JLabel commLabel2;
  private JTextField commField2;
  private JSpinner dateSpinner2;
  private JButton buyButton2;
  private JLabel display2;

  // Determine Cost Basis and Value
  private JTextField portfolioField3;
  private JSpinner dateSpinner3;
  private JButton determineButton3;
  private JTextArea display3;

  // View Portfolio
  private JTextField portfolioField4;
  private JButton viewButton4;
  private JTextArea display4;
  private JButton retrieveButton4;

  // High level strategy
  private JTextField portfolioField5;
  private JTextField numberOfStocksField5;
  private JTextField companyField5;
  private JCheckBox weightsCheckBox5;
  private JLabel weightsLabel5;
  private JTextField weightsField5;
  private JTextField amountField5;
  private JCheckBox commCheckBox5;
  private JLabel commLabel5;
  private JTextField commField5;
  private JSpinner startDateSpinner5;
  private JCheckBox endDateCheckBox5;
  private JLabel endDateLabel5;
  private JSpinner endDateSpinner5;
  private JTextField frequencyField5;
  private JButton createStrategyButton5;
  private JTextArea display5;

  private SpinnerModel dateModel2;
  private SpinnerModel dateModel3;
  private SpinnerModel startDateModel5;
  private SpinnerModel endDateModel5;

  private CardLayout card = new CardLayout();

  /**
   * Creates new form InvestmentGUIView.
   *
   * @param caption in String.
   */
  public InvestmentGUIView(String caption) {
    super(caption);
    setSize(500, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   */
  private void initComponents() {

    JPanel mainPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    bodyPanel = new JPanel();

    JPanel createPortfolioPanel = new JPanel();
    JPanel buyStocksPanel = new JPanel();
    JPanel determineCostBasisValuePanel = new JPanel();
    JPanel viewPortfolioPanel = new JPanel();
    JPanel highLevelPanel = new JPanel();

    JButton createPortfolioButton = new JButton();
    JButton buyStocksButton = new JButton();
    JButton determineCostBasisValueButton = new JButton();
    JButton viewPortfolioButton = new JButton();
    JButton highLevelButton = new JButton();
    saveButton = new JButton();

    // Create Portfolio
    JLabel portfolioLabel1 = new JLabel();
    portfolioField1 = new JTextField();
    createButton1 = new JButton();
    createButton1.setActionCommand("create");
    display1 = new JLabel("To be displayed...");

    // Buy Stocks
    JLabel portfolioLabel2 = new JLabel();
    portfolioField2 = new JTextField();

    JLabel companyLabel2 = new JLabel();
    companyField2 = new JTextField();
    JLabel amountLabel2 = new JLabel();
    amountField2 = new JTextField();
    JLabel commCheckBoxLabel2 = new JLabel();
    commCheckBox2 = new JCheckBox();
    commLabel2 = new JLabel();
    commField2 = new JTextField();
    JLabel dateLabel2 = new JLabel();
    buyButton2 = new JButton();
    buyButton2.setActionCommand("buy");
    display2 = new JLabel("To be displayed...");

    // Determine Cost Basis and Value
    JLabel portfolioLabel3 = new JLabel();
    portfolioField3 = new JTextField();
    JLabel dateLabel3 = new JLabel();
    determineButton3 = new JButton();
    determineButton3.setActionCommand("determine");
    display3 = new JTextArea("To be displayed...");
    display3.setEditable(false);

    // View Portfolio
    JLabel portfolioLabel4 = new JLabel();
    portfolioField4 = new JTextField();
    viewButton4 = new JButton();
    viewButton4.setActionCommand("view");
    display4 = new JTextArea("To be displayed...");
    display4.setEditable(false);
    retrieveButton4 = new JButton();
    retrieveButton4.setActionCommand("retrieve");

    // High level strategy
    JLabel portfolioLabel5 = new JLabel();
    portfolioField5 = new JTextField();
    JLabel numberOfStocksLabel5 = new JLabel();
    numberOfStocksField5 = new JTextField();
    JLabel companyLabel5 = new JLabel();
    companyField5 = new JTextField();
    JLabel weightsCheckBoxLabel5 = new JLabel();
    weightsCheckBox5 = new JCheckBox();
    weightsLabel5 = new JLabel();
    weightsField5 = new JTextField();
    JLabel amountLabel5 = new JLabel();
    amountField5 = new JTextField();
    JLabel commCheckBoxLabel5 = new JLabel();
    commCheckBox5 = new JCheckBox();
    commLabel5 = new JLabel();
    commField5 = new JTextField();
    JLabel startDateLabel5 = new JLabel();
    JLabel endDateCheckBoxLabel5 = new JLabel();
    endDateCheckBox5 = new JCheckBox();
    endDateLabel5 = new JLabel();
    JLabel frequencyLabel5 = new JLabel();
    frequencyField5 = new JTextField();
    createStrategyButton5 = new JButton();
    createStrategyButton5.setActionCommand("highlevel");
    display5 = new JTextArea("To be displayed...");
    display5.setEditable(false);

    // Retrieve saved data


    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    mainPanel.setBackground(new Color(255, 204, 204));
    mainPanel.setSize(500, 300);
    menuPanel.setBackground(new Color(255, 204, 204));

    setTitle("Stocks Portfolio Manager");

    createPortfolioButton.setText("Create Portfolio");
    createPortfolioButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        card.show(bodyPanel, "1");
      }
    });

    buyStocksButton.setText("Buy Stocks");
    buyStocksButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        card.show(bodyPanel, "2");
      }
    });

    determineCostBasisValueButton.setText("Determine Cost Basis and Value");
    determineCostBasisValueButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        card.show(bodyPanel, "3");
      }
    });

    viewPortfolioButton.setText("View Portfolio");
    viewPortfolioButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        card.show(bodyPanel, "4");
      }
    });

    highLevelButton.setText("Create High level Strategy");
    highLevelButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        card.show(bodyPanel, "5");
      }
    });

    saveButton.setText("Save to file");
    saveButton.setActionCommand("save");
    fileSaveDisplay = new JLabel("");

    GroupLayout menuPanelLayout = new GroupLayout(menuPanel);
    menuPanel.setLayout(menuPanelLayout);
    menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(menuPanelLayout.createParallelGroup(GroupLayout
                                    .Alignment.LEADING)
                                    .addComponent(createPortfolioButton)
                                    .addComponent(buyStocksButton)
                                    .addComponent(determineCostBasisValueButton)
                                    .addComponent(viewPortfolioButton)
                                    .addComponent(highLevelButton)
                                    .addComponent(saveButton)
                                    .addComponent(fileSaveDisplay))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(createPortfolioButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buyStocksButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(determineCostBasisValueButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewPortfolioButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(highLevelButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveButton)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fileSaveDisplay)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    createPortfolioPanel.setBorder(BorderFactory.createBevelBorder(1, Color.white,
            Color.white, Color.white, Color.white));

    portfolioLabel1.setText("Portfolio Name");
    portfolioField1.setToolTipText("FANG");
    createButton1.setText("Create");

    GroupLayout createPortfolioPanelLayout = new GroupLayout(createPortfolioPanel);
    createPortfolioPanel.setLayout(createPortfolioPanelLayout);
    createPortfolioPanelLayout.setHorizontalGroup(
            createPortfolioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(createPortfolioPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(createPortfolioPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(createPortfolioPanelLayout
                                            .createSequentialGroup()
                                            .addComponent(portfolioLabel1)
                                            .addGap(45, 45, 45)
                                            .addComponent(portfolioField1, GroupLayout
                                                    .PREFERRED_SIZE, 240, GroupLayout
                                                    .PREFERRED_SIZE))
                                    .addComponent(createButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(display1))
                            .addContainerGap(205, Short.MAX_VALUE))
    );

    createPortfolioPanelLayout.setVerticalGroup(
            createPortfolioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(createPortfolioPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(createPortfolioPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(portfolioLabel1)
                                    .addComponent(portfolioField1, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(createButton1)
                            .addGap(18, 18, 18)
                            .addComponent(display1)
                            .addContainerGap(450, Short.MAX_VALUE))
    );

    buyStocksPanel.setBorder(BorderFactory.createBevelBorder(1, Color.white,
            Color.white, Color.white, Color.white));

    portfolioLabel2.setText("Portfolio Name");

    companyLabel2.setText("Company Symbol");
    companyField2.setToolTipText("GOOG");

    amountLabel2.setText("Amount (USD)");
    amountField2.setToolTipText("1000.0");

    commCheckBoxLabel2.setText("Add Commission?");
    commCheckBox2.setText("Yes");
    commCheckBox2.setSelected(false);
    commCheckBox2.setActionCommand("commissionCB2");
    commCheckBox2.addItemListener(this);

    commLabel2.setText("Commission (USD)");
    commLabel2.setForeground(Color.GRAY);
    commField2.setText("0.0");
    commField2.setToolTipText("10.0");
    commField2.setEditable(false);

    Calendar cal = Calendar.getInstance();
    Date now = cal.getTime();
    cal.add(Calendar.YEAR, -50);
    Date startDate = cal.getTime();
    cal.add(Calendar.YEAR, 100);
    Date endDate = cal.getTime();
    dateModel2 = new SpinnerDateModel(now, startDate, endDate, Calendar
            .YEAR);
    dateModel3 = new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);
    startDateModel5 = new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);
    endDateModel5 = new SpinnerDateModel(now, startDate, endDate, Calendar.YEAR);

    dateLabel2.setText("Date");
    dateSpinner2 = new JSpinner(dateModel2);

    buyButton2.setText("Buy");

    GroupLayout buyStocksPanelLayout = new GroupLayout(buyStocksPanel);
    buyStocksPanel.setLayout(buyStocksPanelLayout);
    buyStocksPanelLayout.setHorizontalGroup(
            buyStocksPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(buyStocksPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(buyButton2)
                                    .addGap(18, 18, 18)
                                    .addComponent(display2)
                                    .addGroup(buyStocksPanelLayout
                                            .createParallelGroup(GroupLayout.Alignment.TRAILING,
                                                    false)
                                            .addGroup(GroupLayout.Alignment.LEADING,
                                                    buyStocksPanelLayout
                                                            .createSequentialGroup()
                                                            .addGroup(buyStocksPanelLayout
                                                                    .createParallelGroup(GroupLayout
                                                                                    .Alignment
                                                                                    .LEADING,
                                                                            false)
                                                                    .addComponent(portfolioLabel2)
                                                                    .addComponent(companyLabel2)
                                                                    .addComponent(amountLabel2)
                                                                    .addComponent(
                                                                            commCheckBoxLabel2)
                                                                    .addComponent(commLabel2)
                                                                    .addComponent(dateLabel2))
                                                            .addPreferredGap(LayoutStyle
                                                                    .ComponentPlacement.UNRELATED)
                                                            .addGroup(buyStocksPanelLayout
                                                                    .createParallelGroup(GroupLayout
                                                                                    .Alignment
                                                                                    .LEADING,
                                                                            false)
                                                                    .addComponent(portfolioField2)
                                                                    .addComponent(companyField2)
                                                                    .addComponent(amountField2)
                                                                    .addComponent(commField2)
                                                                    .addComponent(dateSpinner2)
                                                                    .addComponent(commCheckBox2)))))
                            .addContainerGap(250, Short.MAX_VALUE))
    );

    buyStocksPanelLayout.setVerticalGroup(
            buyStocksPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(buyStocksPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(portfolioLabel2)
                                    .addComponent(portfolioField2, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(companyLabel2)
                                    .addComponent(companyField2, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(amountLabel2)
                                    .addComponent(amountField2, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(commCheckBoxLabel2)
                                    .addComponent(commCheckBox2))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(commLabel2)
                                    .addComponent(commField2, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(buyStocksPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(dateLabel2, GroupLayout
                                            .PREFERRED_SIZE, 26, GroupLayout
                                            .PREFERRED_SIZE)
                                    .addComponent(dateSpinner2, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(80, 80, 80)
                            .addComponent(buyButton2)
                            .addGap(18, 18, 18)
                            .addComponent(display2)
                            .addGap(40, 40, 40))
    );

    determineCostBasisValuePanel.setBorder(BorderFactory.createBevelBorder(1, Color.white,
            Color.white, Color.white, Color.white));

    portfolioLabel3.setText("Portfolio Name");
    dateLabel3.setText("Date");
    dateSpinner3 = new JSpinner(dateModel3);

    determineButton3.setText("Calculate");

    GroupLayout determineCostBasisValuePanelLayout = new GroupLayout(determineCostBasisValuePanel);
    determineCostBasisValuePanel.setLayout(determineCostBasisValuePanelLayout);
    determineCostBasisValuePanelLayout.setHorizontalGroup(
            determineCostBasisValuePanelLayout.createParallelGroup(GroupLayout
                    .Alignment.LEADING)
                    .addGroup(determineCostBasisValuePanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(determineCostBasisValuePanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(determineCostBasisValuePanelLayout
                                            .createSequentialGroup()
                                            .addGroup(determineCostBasisValuePanelLayout
                                                    .createParallelGroup(GroupLayout
                                                                    .Alignment
                                                                    .TRAILING,
                                                            false)
                                                    .addComponent(portfolioLabel3)
                                                    .addComponent(dateLabel3))
                                            .addPreferredGap(LayoutStyle
                                                    .ComponentPlacement.UNRELATED)
                                            .addGroup(determineCostBasisValuePanelLayout
                                                    .createParallelGroup(GroupLayout
                                                                    .Alignment
                                                                    .LEADING,
                                                            false)
                                                    .addComponent(dateSpinner3,
                                                            GroupLayout.DEFAULT_SIZE,
                                                            250, Short.MAX_VALUE)
                                                    .addComponent(portfolioField3)))
                                    .addComponent(determineButton3)
                                    .addGap(18, 18, 18)
                                    .addComponent(display3))
                            .addContainerGap(250, Short.MAX_VALUE))
    );

    determineCostBasisValuePanelLayout.setVerticalGroup(
            determineCostBasisValuePanelLayout.createParallelGroup(GroupLayout
                    .Alignment.LEADING)
                    .addGroup(determineCostBasisValuePanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(determineCostBasisValuePanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(portfolioLabel3)
                                    .addComponent(portfolioField3, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(determineCostBasisValuePanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(dateLabel3)
                                    .addComponent(dateSpinner3, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                    250, Short.MAX_VALUE)
                            .addComponent(determineButton3)
                            .addGap(18, 18, 18)
                            .addComponent(display3)
                            .addGap(40, 40, 40))
    );

    bodyPanel.add(determineCostBasisValuePanel, "card2");

    viewPortfolioPanel.setBorder(BorderFactory.createBevelBorder(1, Color.white,
            Color.white, Color.white, Color.white));

    portfolioLabel4.setText("Portfolio Name");
    viewButton4.setText("View");
    retrieveButton4.setText("Retrieve saved Portfolios from file");

    GroupLayout viewPortfolioPanelLayout = new GroupLayout(viewPortfolioPanel);
    viewPortfolioPanel.setLayout(viewPortfolioPanelLayout);
    viewPortfolioPanelLayout.setHorizontalGroup(
            viewPortfolioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(viewPortfolioPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(viewPortfolioPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(portfolioLabel4)
                                    .addComponent(viewButton4)
                                    .addGap(25, 25, 25)
                                    .addComponent(retrieveButton4)
                                    .addGap(18, 18, 18)
                                    .addComponent(display4))
                            .addGap(33, 33, 33)
                            .addComponent(portfolioField4, GroupLayout.DEFAULT_SIZE,
                                    250, Short.MAX_VALUE)
                            .addGap(205, 205, 205))
    );

    viewPortfolioPanelLayout.setVerticalGroup(
            viewPortfolioPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(viewPortfolioPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(viewPortfolioPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(portfolioLabel4)
                                    .addComponent(portfolioField4, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(viewButton4)
                            .addGap(25, 25, 25)
                            .addComponent(retrieveButton4)
                            .addGap(18, 18, 18)
                            .addComponent(display4)
                            .addContainerGap(450, Short.MAX_VALUE))
    );

    highLevelPanel.setBorder(BorderFactory.createBevelBorder(1, Color.white,
            Color.white, Color.white, Color.white));

    portfolioLabel5.setText("Portfolio Name");

    numberOfStocksLabel5.setText("Number of Stocks");
    numberOfStocksField5.setToolTipText("4");

    companyLabel5.setText("Company Symbols (comma separated)");
    companyField5.setToolTipText("GOOG,FB,AAPL,MSFT");

    weightsCheckBoxLabel5.setText("Modify preset Equal weights?");
    weightsCheckBox5.setSelected(false);
    weightsCheckBox5.setActionCommand("weightsCB5");
    weightsCheckBox5.setText("Yes");
    weightsCheckBox5.addItemListener(this);

    weightsLabel5.setText("Weights (comma separated)");
    weightsLabel5.setForeground(Color.GRAY);
    weightsField5.setToolTipText("30");
    weightsField5.setEditable(false);

    amountLabel5.setText("Amount (USD)");
    amountField5.setToolTipText("4000.0");

    commCheckBoxLabel5.setText("Add Commission?");
    commCheckBox5.setSelected(false);
    commCheckBox5.setActionCommand("commissionCB5");
    commCheckBox5.setText("Yes");
    commCheckBox5.addItemListener(this);

    commLabel5.setText("Commission (comma separated)");
    commLabel5.setForeground(Color.GRAY);
    commField5.setText("0.0");
    commField5.setToolTipText("10.0");
    commField5.setEditable(false);

    startDateLabel5.setText("Start Date");
    startDateSpinner5 = new JSpinner(startDateModel5);

    endDateCheckBoxLabel5.setText("Provide End date? (Default: Ongoing)");
    endDateCheckBox5.setText("Yes");
    endDateCheckBox5.setSelected(false);
    endDateCheckBox5.setActionCommand("enddateCB5");
    endDateCheckBox5.addItemListener(this);

    endDateLabel5.setText("End Date");
    endDateLabel5.setForeground(Color.GRAY);
    endDateSpinner5 = new JSpinner(endDateModel5);
    endDateSpinner5.setEnabled(false);

    frequencyLabel5.setText("Frequency");
    frequencyField5.setToolTipText("30");

    createStrategyButton5.setText("Create Strategy");

    GroupLayout highLevelPanelLayout = new GroupLayout(highLevelPanel);
    highLevelPanel.setLayout(highLevelPanelLayout);

    highLevelPanelLayout.setHorizontalGroup(
            highLevelPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(highLevelPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(portfolioLabel5)
                                    .addComponent(numberOfStocksLabel5)
                                    .addComponent(companyLabel5)
                                    .addComponent(weightsCheckBoxLabel5)
                                    .addComponent(weightsLabel5)
                                    .addComponent(amountLabel5)
                                    .addComponent(commCheckBoxLabel5)
                                    .addComponent(commLabel5)
                                    .addComponent(startDateLabel5)
                                    .addComponent(endDateCheckBoxLabel5)
                                    .addComponent(endDateLabel5)
                                    .addComponent(frequencyLabel5)
                                    .addComponent(createStrategyButton5)
                                    .addGap(30, 30, 30)
                                    .addComponent(display5))
                            .addContainerGap()
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(portfolioField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(numberOfStocksField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(companyField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(weightsCheckBox5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(weightsField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(amountField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(commCheckBox5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(commField5, GroupLayout.DEFAULT_SIZE,
                                            200, Short.MAX_VALUE)
                                    .addComponent(startDateSpinner5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(endDateCheckBox5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(endDateSpinner5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addComponent(frequencyField5, GroupLayout
                                            .DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                    .addGap(205, 205, 205)))
    );

    highLevelPanelLayout.setVerticalGroup(
            highLevelPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(highLevelPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(portfolioLabel5)
                                    .addComponent(portfolioField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(numberOfStocksLabel5)
                                    .addComponent(numberOfStocksField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(companyLabel5)
                                    .addComponent(companyField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(weightsCheckBoxLabel5)
                                    .addComponent(weightsCheckBox5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(weightsLabel5)
                                    .addComponent(weightsField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(amountLabel5)
                                    .addComponent(amountField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(commCheckBoxLabel5)
                                    .addComponent(commCheckBox5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(commLabel5)
                                    .addComponent(commField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(startDateLabel5)
                                    .addComponent(startDateSpinner5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(endDateCheckBoxLabel5)
                                    .addComponent(endDateCheckBox5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(endDateLabel5)
                                    .addComponent(endDateSpinner5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGroup(highLevelPanelLayout
                                    .createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(frequencyLabel5)
                                    .addComponent(frequencyField5, GroupLayout
                                                    .PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(createStrategyButton5)
                            .addGap(25, 25, 25)
                            .addComponent(display5)
                            .addContainerGap(450, Short.MAX_VALUE))
    );

    GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
    mainPanel.setLayout(mainPanelLayout);
    mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(menuPanel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, GroupLayout.Alignment.TRAILING,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short
                                    .MAX_VALUE)
                    .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout
                            .DEFAULT_SIZE, Short.MAX_VALUE)
    );

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout
                    .createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
    );

    bodyPanel.setLayout(card);

    bodyPanel.add(createPortfolioPanel, "1");
    bodyPanel.add(buyStocksPanel, "2");
    bodyPanel.add(determineCostBasisValuePanel, "3");
    bodyPanel.add(viewPortfolioPanel, "4");
    bodyPanel.add(highLevelPanel, "5");
    card.show(bodyPanel, "1");
    add(bodyPanel);
    pack();
  }

  @Override
  public void transmitMessage(String message) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String read() {
    return null;
  }

  @Override
  public boolean hasNextInput() {
    return false;
  }

  @Override
  public void setListener(ActionListener listener) {
    createButton1.addActionListener(listener);
    buyButton2.addActionListener(listener);
    determineButton3.addActionListener(listener);
    viewButton4.addActionListener(listener);
    retrieveButton4.addActionListener(listener);
    createStrategyButton5.addActionListener(listener);
    saveButton.addActionListener(listener);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    String who = ((JCheckBox) e.getItemSelectable()).getActionCommand();
    switch (who) {
      case "commissionCB2":
        if (e.getStateChange() == ItemEvent.SELECTED) {
          commLabel2.setForeground(Color.BLACK);
          commField2.setEditable(true);
          commField2.setToolTipText("10.0");
        } else {
          commLabel2.setForeground(Color.GRAY);
          commField2.setText("0.0");
          commField2.setEditable(false);
        }
        break;
      case "weightsCB5":
        if (e.getStateChange() == ItemEvent.SELECTED) {
          weightsLabel5.setForeground(Color.BLACK);
          weightsField5.setEditable(true);
          weightsField5.setToolTipText("30");
        } else {
          weightsLabel5.setForeground(Color.GRAY);
          weightsField5.setText("");
          weightsField5.setEditable(false);
        }
        break;
      case "commissionCB5":
        if (e.getStateChange() == ItemEvent.SELECTED) {
          commLabel5.setForeground(Color.BLACK);
          commField5.setEditable(true);
          commField5.setToolTipText("10.0");
        } else {
          commLabel5.setForeground(Color.GRAY);
          commField5.setText("0.0");
          commField5.setEditable(false);
        }
        break;
      case "enddateCB5":
        if (e.getStateChange() == ItemEvent.SELECTED) {
          endDateLabel5.setForeground(Color.BLACK);
          endDateSpinner5.setEnabled(true);
        } else {
          endDateLabel5.setForeground(Color.GRAY);
          endDateSpinner5.setEnabled(false);
        }
        break;
      default:
        break;
    }
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCreatePortfolioDisplay(String s) {
    display1.setText(s);
  }

  @Override
  public String getCreatePortfolioInputString() {
    return portfolioField1.getText();
  }

  @Override
  public void clearCreatePortfolioInputString() {
    portfolioField1.setText("");
  }

  @Override
  public void setBuyStockDisplay(String s) {
    display2.setText(s);
  }

  @Override
  public List<String> getBuyStockInputString() {
    List<String> inputs = new ArrayList<>();
    String portfolioName = portfolioField2.getText();
    String companySymbol = companyField2.getText();
    String amount = amountField2.getText();
    Boolean addCommission = commCheckBox2.isSelected();
    String addComm = addCommission ? "true" : "false";
    String commission = commField2.getText();
    Date date = (Date) dateSpinner2.getValue();

    inputs.add(portfolioName);
    inputs.add(companySymbol);
    inputs.add(amount);
    inputs.add(addComm);
    inputs.add(commission);
    inputs.add(date.toString());
    return inputs;
  }

  @Override
  public Date getBuyDate() {
    return (Date) dateSpinner2.getValue();
  }

  @Override
  public void clearBuyStockInputString() {
    portfolioField2.setText("");
    companyField2.setText("");
    amountField2.setText("");
    commCheckBox2.setSelected(false);
    commField2.setText("");
    dateSpinner2 = new JSpinner(dateModel2);
  }

  @Override
  public void setDetermineCostBasisDisplay(String s) {
    display3.setText(s);
  }

  @Override
  public String getDetermineCostBasisPortfolio() {
    return portfolioField3.getText();
  }

  @Override
  public Date getDetermineDate() {
    return (Date) dateSpinner3.getValue();
  }

  @Override
  public void clearDetermineCostBasisInputString() {
    portfolioField3.setText("");
    dateSpinner3 = new JSpinner(dateModel3);
  }

  @Override
  public void setViewDisplay(String s) {
    display4.setText(s);
  }

  @Override
  public String getViewInputString() {
    return portfolioField4.getText();
  }

  @Override
  public void clearViewInputString() {
    portfolioField4.setText("");
  }

  @Override
  public void setCreateStrategyDisplay(String s) {
    display5.setText(s);
  }

  @Override
  public List<String> getCreateStrategyInputString() {
    List<String> inputs = new ArrayList<>();
    String portfolioName = portfolioField5.getText();
    String numberOfStocks = numberOfStocksField5.getText();
    String symbols = companyField5.getText();
    Boolean addWeights = weightsCheckBox5.isSelected();
    String addW = addWeights ? "true" : "false";
    String weights = weightsField5.getText();
    String amount = amountField5.getText();
    Boolean addCommission = commCheckBox5.isSelected();
    String addComm = addCommission ? "true" : "false";
    String commission = commField5.getText();
    Boolean setEndDate = endDateCheckBox5.isSelected();
    String setEnd = setEndDate ? "true" : "false";
    String frequency = frequencyField5.getText();

    inputs.add(portfolioName);
    inputs.add(numberOfStocks);
    inputs.add(symbols);
    inputs.add(addW);
    inputs.add(weights);
    inputs.add(amount);
    inputs.add(addComm);
    inputs.add(commission);
    inputs.add(setEnd);
    inputs.add(frequency);
    return inputs;
  }

  @Override
  public Date getStartDate() {
    return (Date) startDateSpinner5.getValue();
  }

  @Override
  public Date getEndDate() {
    return (Date) endDateSpinner5.getValue();
  }

  @Override
  public void clearCreateStrategyInputString() {
    portfolioField5.setText("");
    numberOfStocksField5.setText("");
    companyField5.setText("");
    weightsCheckBox5.setSelected(false);
    weightsField5.setText("");
    amountField5.setText("");
    commCheckBox5.setSelected(false);
    commField5.setText("");
    startDateSpinner5 = new JSpinner(startDateModel5);
    endDateCheckBox5.setSelected(false);
    endDateSpinner5 = new JSpinner(endDateModel5);
    frequencyField5.setText("");
  }

  @Override
  public void setSaveFileName() {
    fileSaveDisplay.setText("Saved!");
  }
}
