
import java.awt.event.*;
import java.awt.*;
import java.text.NumberFormat;
import javax.swing.event.*;
import javax.swing.*;
/**
 *
 * @author jigssharma
 */
public class TaxCalculator
{


    public static void main(String[] args)
    {
        Program program = new Program();
        program.runProgram();
    }
    
}











class Program
{
    private JFrame frame;
    private JFrame calculatorFrame;
    private JPanel menuPane;
    private JPanel calculatorPane;
    private JButton businessButton;
    private JButton personalButton;
    private JTextField totalIncome;
    private JTextField totalExpense;
    private JButton calculateButton;
    private JRadioButton residentButton;
    private JRadioButton nonResidentButton;
    private JRadioButton workingHolidayButton;
    private ButtonGroup taxPayerGroup;
    private JLabel errorMessage;
    
    
    
    
    public void runProgram()
    {
        frame = new JFrame("Tax Calculator");
        menuPane = new JPanel();
        
        
        businessButton = new JButton();
        businessButton.setText("Business Tax Calculator");
        menuPane.add(businessButton);
        
        businessButton.addActionListener((ActionEvent e) -> {
            selectionBusinessButtonPressed();
        });
        
        
        personalButton = new JButton();
        personalButton.setText("Personal Tax Calculator");
        menuPane.add(personalButton);
        
        personalButton.addActionListener((ActionEvent e) -> {
            selectionPersonalButtonPressed();
        });
        
        
        frame.getContentPane().add(menuPane);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 100));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    
    public Boolean isValidDouble(String value)
    {
        try 
        {
            Double.parseDouble(value);
            return true;
        } 
        catch (NumberFormatException ex)
        {
            return false;
        }
    }
    
    
    
    public void selectionBusinessButtonPressed()
    {
        calculatorFrame = new JFrame("Business Tax Calculator");
        
        calculatorPane = new JPanel();
        calculatorPane.setLayout(null);
        
        
        JLabel incomeLabel = new JLabel("Total Income");
        incomeLabel.setBounds(10, 10, 200, 30);
        calculatorPane.add(incomeLabel);
        
        
        totalIncome = new JTextField();
        totalIncome.setBounds(220, 10, 200, 30);
        calculatorPane.add(totalIncome);
        
        
        JLabel expenseLabel = new JLabel("Total Expense");
        expenseLabel.setBounds(10, 40, 200, 30);
        calculatorPane.add(expenseLabel);
        
        
        totalExpense = new JTextField();
        totalExpense.setBounds(220, 40, 200, 30);
        calculatorPane.add(totalExpense);
        
        
        
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(10, 70, 200, 30);
        calculatorPane.add(calculateButton);
        
        calculateButton.addActionListener((ActionEvent e) -> {
            selectionCalculateBusinessButtonPressed();
        });
        
        
        calculatorFrame.getContentPane().add(calculatorPane);
        
        calculatorFrame.setPreferredSize(new Dimension(500, 200));
        calculatorFrame.pack();
        calculatorFrame.setLocationRelativeTo(null);
        calculatorFrame.setVisible(true);
    }
    
    
    
    
    
    public void selectionPersonalButtonPressed()
    {
        calculatorFrame = new JFrame("Business Tax Calculator");
        
        calculatorPane = new JPanel();
        calculatorPane.setLayout(null);
        
        JLabel incomeLabel = new JLabel("Total Income");
        incomeLabel.setBounds(10, 10, 200, 20);
        calculatorPane.add(incomeLabel);
        
        totalIncome = new JTextField();
        totalIncome.setBounds(220, 10, 200, 30);
        calculatorPane.add(totalIncome);
        
        
        residentButton = new JRadioButton();
        residentButton.setText("Resident Tax");
        residentButton.setSelected(true);
        residentButton.setBounds(10, 40, 200, 30);
        calculatorPane.add(residentButton);
        
        
        nonResidentButton = new JRadioButton();
        nonResidentButton.setText("Non-resident Tax");
        nonResidentButton.setBounds(220, 40, 200, 30);
        calculatorPane.add(nonResidentButton);
        
        
        workingHolidayButton = new JRadioButton();
        workingHolidayButton.setText("Working Holiday tax");
        workingHolidayButton.setBounds(10, 70, 200, 30);
        calculatorPane.add(workingHolidayButton);
        
        
        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(220, 70, 200, 30);
        calculatorPane.add(calculateButton);
        
        
        
        taxPayerGroup = new ButtonGroup();
        taxPayerGroup.add(residentButton);
        taxPayerGroup.add(nonResidentButton);
        taxPayerGroup.add(workingHolidayButton);
        
        
        calculateButton.addActionListener((ActionEvent e) -> {
            selectionCalculatePersonalButtonPressed();
        });
        
        errorMessage = new JLabel();
        errorMessage.setBounds(10, 110, 480, 40);
        errorMessage.setText("Enter total income and then press Calculate");
        calculatorPane.add(errorMessage);
        
        calculatorFrame.getContentPane().add(calculatorPane);
        
        calculatorFrame.setPreferredSize(new Dimension(500, 200));
        calculatorFrame.pack();
        calculatorFrame.setLocationRelativeTo(null);
        calculatorFrame.setVisible(true);
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public void selectionCalculateBusinessButtonPressed()
    {
        Boolean incomeValid = isValidDouble(totalIncome.getText());
        Boolean expenseValid = isValidDouble(totalExpense.getText());
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        JPanel messagePanel = new JPanel();
        
        if(!incomeValid)
        {
            errorMessage.setText("Entered income is not valid number or blank, re-enter");
            return;
        }
        
        if(!expenseValid)
        {
            errorMessage.setText("Entered income is not valid number or blank, re-enter");
            return;
        }
        
        double income = Double.parseDouble(totalIncome.getText());
        double expense = Double.parseDouble(totalExpense.getText());
        
        
        if(expense > income)
        {
            JOptionPane.showMessageDialog(messagePanel, "Business tax payable amount is A$0", "Business Tax", 1);
        }
        else
        {
            JOptionPane.showMessageDialog(messagePanel, "Business tax payable amount is A" + formatter.format(BusinessTax.getPayableTax(income, expense)), "Business Tax", 1);
        }
    }
    
    
    
    
    
    public void selectionCalculatePersonalButtonPressed()
    {
        Boolean incomeValid = isValidDouble(totalIncome.getText());
        
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        
        JPanel messagePanel = new JPanel();
        
        if (!incomeValid)
        {
            errorMessage.setText("Entered income is not valid number or blank, re-enter");
            return;
        }
        
        double income = Double.parseDouble(totalIncome.getText());
        
        if(residentButton.isSelected())
        {
            JOptionPane.showMessageDialog(messagePanel, "Tax payable amount is A" + formatter.format(ResidentTaxPayer.calctax(income)), "Resident Tax", 1);
        }
        
        if(nonResidentButton.isSelected())
        {
            JOptionPane.showMessageDialog(messagePanel, "Tax payable amount is A" + formatter.format(ForeignResidentTaxPayer.calctax(income)), "Non-Resident Tax", 1);
        }
        
        if(workingHolidayButton.isSelected())
        {
            JOptionPane.showMessageDialog(messagePanel, "Tax payable amount is A" + formatter.format(WorkingHolidayTaxPayer.calctax(income)), "Working Holiday Tax", 1);
        }
    }
}



















abstract class BusinessTax implements TaxProfile
{
    private int TFN;
    private String companyName;
    private double totalIncome;
    private double totalExpense;
    private Address address;
    
    
    public BusinessTax(int TFN, String companyName, double totalIncome, double totalExpense, Address address)
    {
        this.TFN = TFN;
        this.companyName = companyName;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.address = address;
    }
    
    
    
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public void setTotalIncome(double totalIncome)
    {
        this.totalIncome = totalIncome;
    }
    
    public void setTotalExpense(double totalExpense)
    {
        this.totalExpense = totalExpense;
    }
    
    public void setAddress(Address address)
    {
        this.address = address;
    }
    
    
    
    public int getTFN()
    {
        return TFN;
    }
    
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    public double getTotalIncome()
    {
        return totalIncome;
    }
    
    public double getTotalExpense()
    {
        return totalExpense;
    }
    
    public Address getAddress()
    {
        return address;
    }
            
            
            
    public String toString()
    {
        return "TFN: " + TFN;
    }
    
    public double calcTax(double tax)
    {
        tax = totalIncome * 0.3;
        
        return tax;
    }
    
    
        public static double getPayableTax(double income, double expense)
    {
     double payableTax = income - expense;
     
     return payableTax;
    }
}












abstract class TaxPayer
{
    private String TFN;
    private String firstName;
    private String lastName;
    private double income;
    private Address address;
    
    
    public TaxPayer(String TFN, String firstName, String lastName, double income, Address address)
    {
        this.TFN = TFN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.income = income;
        this.address = address;
    }
    
    
    public static double calctax(Double income)
    {
        return income;
    }
    
    
    public abstract double calctax();
    
    
    public void setFirstName(String fName)
    {
        this.firstName = fName;
    }
    
    
    public void setLastName(String lName)
    {
        this.lastName = lName;
    }
    
    
    public void setIncome(double income)
    {
        this.income = income;
    }
    
    
    public void setAddress(Address address)
    {
        this.address = address;
    }
    
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getFullName()
    {
        return firstName + " " + lastName;
    }
    
    public double getIncome()
    {
        return income;
    }
    
    public Address getAddress()
    {
        return address;
    }
    
    public String getTaxFileNumber()
    {
        return TFN;
    }
    
    
    public String toString()
    {
        return "Tax File Number: " + TFN + ", " + "First Name: " + firstName + ", " + "Last Name: " + lastName + ", " + "Income: " + income + ", " + "Address: " + address + ", " + "Tax Applied: " + calctax(income);
    }
    
    public double getPayableTax()
    {
        return calctax();
    }
}









class ResidentTaxPayer extends TaxPayer
{
      private State state;
    
    
    public ResidentTaxPayer(String TFN, String firstName, String lastName, double income, Address address, State state)
    {
        super(TFN, firstName, lastName, income, address);
        this.state = state;
    }
    
    
    public static double calctax(double income)
    {
        double tax;
        
        if(income > 18200 || income < 37001)
        {
            tax = 0.19 * (income - 18200);
        }
        
        else if(income > 37000 || income < 90001)
        {
            tax = 3572 + (0.325 * (income - 37000));
        }
        
        else if(income > 90000 || income < 180001)
        {
            tax = 20797 + (0.37 * (income - 90000));
        }
        
        else if(income > 180000)
        {
            tax = 54097 + (0.45 * (income * 180000));
        }
        
        else
        {
            tax = 0;
        }
        
        return tax;
    }
    
    public double calctax()
    {
         double tax;
        
        if(getIncome() > 18200 || getIncome() < 37001)
        {
            tax = 0.19 * (getIncome() - 18200);
        }
        
        else if(getIncome() > 37000 || getIncome() < 90001)
        {
            tax = 3572 + (0.325 * (getIncome() - 37000));
        }
        
        else if(getIncome() > 90000 || getIncome() < 180001)
        {
            tax = 20797 + (0.37 * (getIncome() - 90000));
        }
        
        else if(getIncome() > 180000)
        {
            tax = 54097 + (0.45 * (getIncome() * 180000));
        }
        
        else
        {
            tax = 0;
        }
        
        return tax; 
    }
    
    
    public void setState(State state)
    {
        this.state = state;
    }
    
    public State getState()
    {
        return state;
    }
    
    @Override
    public String toString()
    {
        return "Tax File Number: " + getTaxFileNumber() + " | " + "First Name: " + getFirstName() + " | " + "Last Name: " + getLastName() + " | " + "Income: " + getIncome() + " | " + getAddress() + " | " + "State: " + getState(); 
    }
    
    public double getPayableTax()
    {
        return calctax();
    }
}









class ForeignResidentTaxPayer extends TaxPayer
{
    private String countryOfOrigin;
    
    
    public ForeignResidentTaxPayer(String TFN, String firstName, String lastName, double income, Address address, String countryOfOrigin)
    {
        super(TFN, firstName, lastName, income, address);
        this.countryOfOrigin = countryOfOrigin;
    }
    
    
    public static double calctax(double income)
    {
        double tax;
        
        if(income < 91000)
        {
            tax = 0.325 * income;
        }
        
        else if(income > 90000 && income < 181000)
        {
            tax = 29250 + (0.37 * (income - 90000));
        }
        
        else
        {
            tax = 62550 + (0.45 * (income - 180000));
        }
        return tax;
    }
    
    public double calctax()
    {
        
         double tax;
        
        if(getIncome() < 91000)
        {
            tax = 0.325 * getIncome();
        }
        
        else if(getIncome() > 90000 && getIncome() < 181000)
        {
            tax = 29250 + (0.37 * (getIncome() - 90000));
        }
        
        else
        {
            tax = 62550 + (0.45 * (getIncome() - 180000));
        }
        return tax;
    }
    
    
    public void setCountry(String countryOfOrigin)
    {
        this.countryOfOrigin = countryOfOrigin;
    }
    
    public String getCountry()
    {
        return countryOfOrigin;
    }
    
    @Override
    public String toString()
    {
        return "Tax File Number: " + getTaxFileNumber() + " | " + "First Name: " + getFirstName() + " | " + "Last Name: " + getLastName() + " | " + "Income: " + getIncome() + " | " + getAddress() + " | " + "Country of Origin: " + getCountry(); 
    }
    
    public double getPayableTax()
    {
        return calctax();
    }
    
}








class WorkingHolidayTaxPayer extends ForeignResidentTaxPayer
{
     private String visaNo;
    
    
    public WorkingHolidayTaxPayer(String TFN, String firstName, String lastName, double income, Address address, String countryOfOrigin, String visaNo)
    {
        super(TFN, firstName, lastName, income, address, countryOfOrigin);
        this.visaNo = visaNo;
    }
    
    
    public static double calctax(double income)
    {
        double tax;
        
        if(income > 37000 || income < 90001)
        {
            tax = 5550 + (0.325 * (income - 37000));
        }
        
        else if(income > 90000 || income < 180001)
        {
            tax = 22775 + (0.37 * (income - 90000));
        }
        
        else if(income > 180000)
        {
            tax = 56075 + (0.45 * (income - 180000));
        }
        
        else
        {
            tax = 0.15 * income;
        }
        
        return tax;
    }
    
    public double calctax()
    {
                double tax;
        
        if(getIncome() > 37000 || getIncome() < 90001)
        {
            tax = 5550 + (0.325 * (getIncome() - 37000));
        }
        
        else if(getIncome() > 90000 || getIncome() < 180001)
        {
            tax = 22775 + (0.37 * (getIncome() - 90000));
        }
        
        else if(getIncome() > 180000)
        {
            tax = 56075 + (0.45 * (getIncome() - 180000));
        }
        
        else
        {
            tax = 0.15 * getIncome();
        }
        
        return tax;
    }
    
    
    public void setVisa(String visaNo)
    {
        this.visaNo = visaNo;
    }
    
    public String getVisa()
    {
        return visaNo;
    }
    
    
    @Override
    public String toString()
    {
        return "Tax File Number: " + getTaxFileNumber() + " | " + "First Name: " + getFirstName() + " | " + "Last Name: " + " | " + getLastName() + " | " + "Income: " + " | " + getIncome() + " | " + getAddress() + " | " + "Country of Origin: " + " | " + getCountry() + " | " + "Visa Number: " + getVisa();
    }
    
    public double getPayableTax()
    {
        return calctax();
    }
    
}









class Address
{
    private int streetNumber;
    private String streetName;
    private String suburb;
    private String city;
    private String state;
    private int postcode;
    
    
    public Address(int streetNumber, String streetName, String suburb, String city, String state, int postcode)
    {
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.suburb = suburb;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
    }
    
    public void setStreetNumber(int streetNumber)
    {
        this.streetNumber = streetNumber;
    }
    
    public void setStreetName(String streetName)
    {
        this.streetName = streetName;
    }
    
    public void setSuburb(String suburb)
    {
        this.suburb = suburb;
    }
    
    public void setCity(String city)
    {
        this.city = city;
    }
    
    public void setState(String state)
    {
        this.state = state;
    }
    
    public void setPostcode(int postcode)
    {
        this.postcode = postcode;
    }
    
    
    public int getStreetNumber()
    {
        return streetNumber;
    }
    
    public String getStreetName()
    {
        return streetName;
    }
    
    public String getSubrub()
    {
        return suburb;
    }
    
    public String getCity()
    {
        return city;
    }
    
    public String getState()
    {
        return state;
    }
    
    public int getPostcode()
    {
        return postcode;
    }
    
    
    @Override
    public String toString()
    {
        return "Address: " + streetNumber + " " + streetName + ", " + suburb + ", " + city + ", " + state + " " + postcode;
    }
}






enum State{NSW, QLD, SA, TAS, VIC, WA, JBT, NT, ACT};







 interface TaxProfile
{
   public double getPayableTax();
   
   public String getTaxID();
   
   public String getNameOfTaxPayer();

}




