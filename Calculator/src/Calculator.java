package calculator;

import java.awt.* ;
import javax.swing.* ;
import java.awt.event.* ;
/**
 *
 * @author Carlos
 */
public class Calculator extends JFrame implements ActionListener
{
        JPanel[] row = new JPanel[5] ;
        JButton[] button = new JButton[19] ;
        
        //List of buttons
        String [] buttonString = {"7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3","*", ".", "/","C", "√", "+/-", "=","0"} ;
        
        //Lists of widths and heights of buttons
        int[] dimW = {300, 45, 100, 90} ;
        int[] dimH = {35, 40} ;
        
        Dimension displayDemension = new Dimension(dimW[0], dimH[0]) ;
        Dimension regularDimension = new Dimension(dimW[1], dimH[1]) ;
        Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]) ;
        Dimension zeroButDimension = new Dimension(dimW[3], dimH[1]) ;
        
        //Used for add, sub, mult, div
        boolean[] function = new boolean[4] ;
        
        //Used in calculations
        double[] temp = {0, 0} ;
        
        //Display
        JTextArea display = new JTextArea(1, 20) ;
        Font font = new Font("Times New Roman", Font.BOLD, 14) ;
        
    public static void main(String[] args) 
    {
        //Declarations
        Calculator c = new Calculator() ;
       
        
    }
Calculator()
{
    super("My Calculator") ;
    
    setDesign() ;
    
    setSize(380, 250) ;
    
    setResizable(false) ;
    
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    GridLayout grid = new GridLayout(5, 5) ;
    
    setLayout(grid) ;
    
    for(int i = 0 ; i < 4 ; i++)
    {
        function[i] = false ;
    }
    
    //Row 1
    FlowLayout f1 = new FlowLayout(FlowLayout.CENTER, 1, 1) ;
    
    //Rest of Rows
    FlowLayout f2 = new FlowLayout(FlowLayout.CENTER, 1,1) ;

    for(int i = 0 ; i < 5 ; i++)
    {
        row[i] = new JPanel() ;
    }
    
    //First row = first Flowlayout
    row[0].setLayout(f1);
    
    //Done for rest of rows
    for(int i = 0 ; i < 5 ; i++)
    {
        row[i].setLayout(f2);
    }

    for(int i = 0 ; i < 19 ; i++)
    {
        button[i] = new JButton() ;
        button[i].setText(buttonString[i]);
        button[i].setFont(font);
        button[i].addActionListener(this);
    }
    
    display.setFont(font);
    display.setEditable(false);
    display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT) ;
    display.setPreferredSize(displayDemension);
    
    //Setting up the buttons
    for(int i = 0 ; i < 14 ; i++)
    {
        button[i].setPreferredSize(regularDimension);
    }
    for(int i = 14 ; i < 18 ; i++)
    {
        button[i].setPreferredSize(rColumnDimension);
    }
    button[18].setPreferredSize(zeroButDimension);
    
    //Adding row 1 to panel
    row[0].add(display) ;
    add(row[0]);
    
    //Adding row 2
    for(int i = 0 ; i < 4 ; i++)
    {
        row[1].add(button[i]) ;
    }
    
         row[1].add(button[14]) ;
        add(row[1]) ;
    
    //Adding row 3
    for(int i = 4 ; i < 8 ; i++)
    {
        row[2].add(button[i]) ;
    }
    
        row[2].add(button[15]) ;
        add(row[2]) ;
        
    //Adding row 4
    for(int i = 8 ; i < 12 ; i++)
    {
        row[3].add(button[i]) ;
    }
    
        row[3].add(button[16]) ;
        add(row[3]) ;
        
    //Adding row 5
    row[4].add(button[18]) ;
    
    for(int i = 12 ; i < 14 ; i++)
    {
        row[4].add(button[i]) ;
    }
    
    row[4].add(button[17]) ;
    add(row[4]) ;
    
    setVisible(true);
    
}

public final void setDesign()
{
    try
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    }
    catch(Exception e)
    {
        
    }
}
    
    public void clear()
    {
        try
        {
            //Blanks display
            display.setText("");
            
            for(int i = 0 ; i < 4 ; i++)
            {
                function[i] = false ; //Sets functions back to false
            }
            
            for(int i = 0 ; i < 2 ;  i++)
            {
                temp[i] = 0 ; //Sets temp vars back to 0
            }
        }
        catch(NullPointerException e)
        {
            
        }
    }
    
    public void getSqrt()
    {
        try
        {
            double value = Math.sqrt(Double.parseDouble(display.getText())) ; //Squares Value
            display.setText(Double.toString(value)); //Displays value
        }
        catch(NumberFormatException e)
        {
            
        }
    }
    
    public void getPosNeg()
    {
        try
        {
            double value = Double.parseDouble(display.getText()) ; //Gets value in display
            
            if(value != 0)
            {
                value = value * (-1) ;
                display.setText(Double.toString(value)); //Displays new val
            }
        }
        catch(NumberFormatException e)
        {
            
        }
    }
    
    public void getResult()
    {
        double result = 0 ;
        
        temp[1] = Double.parseDouble(display.getText()) ;
        
        String temp0 = Double.toString(temp[0]) ; 
        String temp1 = Double.toString(temp[1]) ;
        
        try
        {
            if(temp0.contains("-"))
            {
                String[] temp00 = temp0.split("-", 2) ;// Splits the into 2 strings at -
                temp[0] = (Double.parseDouble(temp00[1]) * -1) ; //Puts string back as a double with its true val\
            }
            
            if(temp1.contains("-"))
            {
                String[] temp11 = temp1.split("-", 2) ;
                temp[1] = (Double.parseDouble(temp11[1]) * -1) ;
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            
        }
        
        try
        {
            //Multiplication
            if(function[2] == true)
            {
                result = temp[0] * temp[1] ;
            }
            //Division
            else if(function[3] == true)
            {
                result = temp[0] / temp[1] ;
            }
            //Addition
            else if(function[0] == true)
            {
                result = temp[0] + temp[1] ;
            }
            //Substitution
            else if(function[1] == true)
            {
                result = temp[0] - temp[1] ;
            }
            display.setText(Double.toString(result));
            
            for(int i = 0 ; i < 4 ; i++)
            {
                function[i] = false ; //Sets functions back to false
            }
        }
        catch(NumberFormatException e)
        {
            
        }
    }
   
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource() == button[0])
        {
            display.append("7") ;
        }
        if(ae.getSource() == button[1])
        {
            display.append("8") ;
        }
        if(ae.getSource() == button[2])
        {
            display.append("9") ;
        }
        //Function 0
        if(ae.getSource() == button[3])
        {
            temp[0] = Double.parseDouble(display.getText()) ;
            function[0] = true ;
            display.setText("");
        }
        if(ae.getSource() == button[4]) 
        {
            display.append("4");
        }
       if(ae.getSource() == button[5])
       {
           display.append("5");
       }
       if(ae.getSource() == button[6])
       {
           display.append("6");
       }
       //Function 1
       if(ae.getSource() == button[7])
       {
           temp[0] = Double.parseDouble(display.getText()) ;
           function[1] = true ;
           display.setText("");
       }
       if(ae.getSource() == button[8])
       {
           display.append("1") ;
       }
       if(ae.getSource() == button[9])
       {
           display.append("2");
       }
       if(ae.getSource() == button[10])
       {
           display.append("3") ;
       }
       //Function 2
       if(ae.getSource() == button[11])
       {
           temp[0] = Double.parseDouble(display.getText()) ;
           function[2] = true ;
           display.setText("");
       }
       if(ae.getSource() == button[12])
       {
           display.append(".");
       }
       //Function 3
       if(ae.getSource() == button[13])
       {
           temp[0] = Double.parseDouble(display.getText()) ;
           function[3] = true ;
           display.setText("");
       }
       if(ae.getSource() == button[14])
       {
           clear() ;
       }
       if(ae.getSource() == button[15])
       {
           getSqrt() ; 
       }
       if(ae.getSource() == button[16])
       {
           getPosNeg() ;
       }
       if(ae.getSource() == button[17])
       {
           getResult() ;
       }
       if(ae.getSource() == button[18])
       {
           display.append("0");
       }
    }
}
