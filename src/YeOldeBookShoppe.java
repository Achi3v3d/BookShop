/* Name: Maduabuchi Udokwu
Course: CNT 4714 � Spring 2021
Assignment title: Program 1 � Event-driven Programming
Date: Sunday feb 4, 2021
*/
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.io.*;
import java.lang.*;
import java.util.*;
import java.nio.*;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ArrayList;
public class YeOldeBookShoppe extends JFrame{
	/*try {
		File inv = new File("C:\\Users\\13528\\OneDrive\\redo\\p1\\inventrory.txt");
		
	}*/
	//current order numer tracker
	//File Expo = new File("C:\\Users\\13528\\OneDrive\\redo\\p1\\Export.txt");
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DD/MM/YY, HH:MM:SS a z");
	static DateTimeFormatter ftf = DateTimeFormatter.ofPattern("DDMMYYHHMM");
	File file = new File("C:\\Users\\13528\\OneDrive\\redo\\p1\\transactions.txt");
	int con = 1;
	static int Snumitems= 0;
	int Negcon = 1;
	static float subTotal=0;
	public String tru = " true";
	public String nottru = " false";
	public static int TheOnum = 0;
	static DecimalFormat numberFormat = new DecimalFormat("#.00");
	public static String OrderString[] = new String[0];
	public static String OrderString2[] = new String[0];
	int percent;
	String Transactions = "Transactions.txt";
	// text fields
	JTextField numOrder = new JTextField(20);
	JTextField bookID = new JTextField(20);
	JTextField quantity = new JTextField(20);
	JTextField itemInfo = new JTextField(20);
	JTextField subtotal = new JTextField(20);
	// buttons
	JButton ProcessItem = new JButton("Process Item #1");
	JButton ConfirmItem = new JButton("Confirm Item #1");
	JButton ViewOrder = new JButton("View Order");
	JButton FinishOrder = new JButton("Finish Order");
	JButton NewOrder = new JButton("New Order");
	JButton Exit = new JButton("Exit");
	// labels all noted with order
	JLabel ordernum = new JLabel("Enter number of items in this order: ");
	JLabel orderbook = new JLabel("Enter Book ID for Item(s): ");
	JLabel orderquant = new JLabel("Enter quantity for Item(s): ");
	JLabel orderinfo = new JLabel("Item #1 info: ");
	JLabel ordersub = new JLabel("Order subtotal for 0 item(s): ");
	public void ShopComponts(final Container pane) {
		JPanel order = new JPanel();
		
		
		order.setLayout(new GridLayout(5,2));
		//order.add(new  Label("Enter number of items in this order: "));
		order.add(ordernum);
		order.add(numOrder);
		order.add(orderbook);
		order.add(bookID);
		order.add(orderquant);
		order.add(quantity);
		order.add(orderinfo);
		order.add(itemInfo);
		order.add(ordersub);
		order.add(subtotal);
		
		
		
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(1,5));
		controls.add(ProcessItem);
		controls.add(ConfirmItem);
		controls.add(ViewOrder);
		controls.add(FinishOrder);
		controls.add(NewOrder);
		controls.add(Exit);
		
		// set initial objects off
		ConfirmItem.setEnabled(false);
		ViewOrder.setEnabled(false);
		FinishOrder.setEnabled(false);
		subtotal.setEditable(false);;
		itemInfo.setEditable(false);;
		
		//
		ViewOrder.addActionListener(new View());
		Exit.addActionListener(new CloseListener());
		ProcessItem.addActionListener(new process());
		ConfirmItem.addActionListener(new Confirm());
		FinishOrder.addActionListener(new Finish());
		NewOrder.addActionListener(new clear());
		pane.add(order, BorderLayout.NORTH);
		pane.add(controls, BorderLayout.SOUTH);
		
		
		
	}
	
	
	public class process implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int Aflag= 0;
			try {
				File inv = new File("C:\\Users\\13528\\OneDrive\\redo\\p1\\inventory.txt");
				Scanner sc = new Scanner(inv);
				int Flag=0;
				//int FlagCheck = 1;
				// get number of order
				if(numOrder.getText().isEmpty() || bookID.getText().isEmpty() || quantity.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Must have proper inputs");
				/*	numOrder.setText("enter number here");
					bookID.setText("input Book ID here");
					quantity.setText("set quantity here");*/
					}
				else {
					try {
				Integer Onum = Integer.valueOf(numOrder.getText());
				Snumitems = Onum;
				String Bnum = bookID.getText();
				Integer Qnum = Integer.valueOf(quantity.getText());
				if(Onum == 0 || Qnum == 0){
					JOptionPane.showMessageDialog(null, "orders has to be greater than 0");
					}
				else {
					
				

					while(Onum != 0 && Aflag == 0 ) {
					
							//JOptionPane.showMessageDialog(null, "Book ID " + Bnum + " not in file");
							//while(Flag!= FlagCheck) {
								while(sc.hasNextLine() && Aflag==0) {
									
									String info = sc.nextLine();
									String delims = "[,]+";
									String[] tokens = info.split(delims);
									String key = tokens[0];
										if(Bnum.equals(key) && tru.equals(tokens[2])){
											itemInfo.setText(info);
											// only goes up one if items match
											Flag++;
											numOrder.setEditable(false);
											ConfirmItem.setEnabled(true);
											ProcessItem.setEnabled(false);
											orderinfo.setText("Item #" + con + ": ");
											con++;
											Aflag=1;
										}
										if(Bnum.equals(key) && nottru.equals(tokens[2])) {
											JOptionPane.showMessageDialog(null, "Book ID " + Bnum + " not in Stock");
										}
										//itemInfo.setText(t);
									}
								sc.close();
								if(Flag ==0) {
									JOptionPane.showMessageDialog(null, "Book ID " + Bnum + " not in file");
								
								}
								
							
							//}
							// resets the situation
							Onum--;
							TheOnum = Onum;
						}
					}
					}
					catch(Exception d){
						JOptionPane.showMessageDialog(null, "invalid format Please enter a valid format");
					      ///d.printStackTrace();
					}
				}
				
			} catch(FileNotFoundException e1) {
			      System.out.println("An error occurred.");
			      e1.printStackTrace();
			    }
			// TODO Auto-generated method stub
			
 		}
	}
	
	
	
	public class Confirm implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String outputString = itemInfo.getText();
			JOptionPane.showMessageDialog(null, "Item #" + (con -1) + " accepted");
			String delims = "[,]+";
			String[] tokens = outputString.split(delims);
			float key = Float.valueOf(tokens[3]);
			
			Integer Qnum = Integer.valueOf(quantity.getText());
			if(Qnum<=4) {
				itemInfo.setText(outputString+" 0% $" + numberFormat.format(Qnum * key));
				subTotal += (Qnum * key);
				percent = 0;
			}
			else if(Qnum <=9){
				itemInfo.setText(outputString+" 10% $" + numberFormat.format(Qnum * key *.9));
				subTotal += (Qnum * key *.9);
				percent = 10;
			}else if(Qnum>=10){
				itemInfo.setText(outputString+" 15% $" + numberFormat.format(Qnum * key *.85));
				subTotal += (Qnum * key *.85);
				percent = 15;
			}
			
			subtotal.setText("$"+subTotal);
			try {
				FileWriter writer = new FileWriter(file);
				ZonedDateTime now = ZonedDateTime.now();
				String Time = dtf.format(now);
				writer.write(Time + "," + outputString + numberFormat.format(Qnum * key) + "\n" );
				writer.close();
				ArrayList<String> orderList = new ArrayList<String>(Arrays.asList(OrderString));
				ArrayList<String> orderList2 = new ArrayList<String>(Arrays.asList(OrderString));
				orderList.add(con-1 + ". " + tokens[0] + ", " + tokens[1] + ", " + tokens[3] + ", "+ percent +"%, " + numberFormat.format(Qnum * key)+ "\n");
				orderList2.add(con-1 + ". " + tokens[0] + ", " + tokens[1] + ", " + tokens[3] + ", "+ percent +"%, " + numberFormat.format(Qnum * key));

				OrderString = orderList.toArray(OrderString);
				OrderString2 = orderList2.toArray(OrderString2);
				//rderString[con] = output String
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//button clears and set text
			Negcon = con -1;
			ordernum.setText("Enter number of items left in this order: ");
			orderbook.setText("Enter item ID for Item #" + con + ": ");
			orderquant.setText("Enter quantity for Item #" + con + ": ");
			orderinfo.setText("Item #" + Negcon + " info: ");
			ordersub.setText("Order subtotal for " + Negcon + "item(s): ");
			ProcessItem.setText("Process Item #" + con);
			ConfirmItem.setText("Confirm Item #" + con);
			bookID.setText("");
			quantity.setText("");
			if(TheOnum==0){
				ProcessItem.setEnabled(false);
				ConfirmItem.setEnabled(false);
				orderbook.setText("");
				orderquant.setText("");
			}
			else {
				ProcessItem.setEnabled(true);
				ConfirmItem.setEnabled(false);
			}
			
			FinishOrder.setEnabled(true);
			ViewOrder.setEnabled(true);
		}
	}
	
	public static class View implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, Arrays.toString(OrderString));
			// TODO Auto-generated method stub

		}
	}
	// close button
	public static class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
			// TODO Auto-generated method stub

		}
	}
	public static class Finish implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ZonedDateTime now = ZonedDateTime.now();
			String Time = dtf.format(now);
			JOptionPane.showMessageDialog(null, " Date: " + Time + 
					"\n \n Number of items: " + Snumitems + 
					"\n \n Item# / ID / Title / Price / Qty / Disc % / Subtotal: " +
					"\n \n "+ Arrays.toString(OrderString) + 
					"\n \n Order subtotal: " + numberFormat.format(subTotal) +
					"\n \n Tax rate 6% \n" +
					"\n \n Tax amount " + numberFormat.format(subTotal*.06) +
					"\n \n Order total: " + numberFormat.format(subTotal*1.06) +
					"\n \n Thanks for shopping at Nile Dot Com!"
					);
		try {
			FileWriter writer = new FileWriter("C:\\Users\\13528\\OneDrive\\redo\\p1\\transactions.txt");
			String kime = ftf.format(now);
		      int len = OrderString2.length;
		      for (int i = 0; i < len; i++) {
		         writer.write(kime + ", " + OrderString2[i] + "," + Time + "\n");
		      }
		      writer.close();
			
		}
		catch (Exception d1) {
			// TODO Auto-generated catch block
			d1.printStackTrace();
		}
			// TODO Auto-generated method stub

		}
		

	}
	public  class clear implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			con = 1;
			subTotal = 0;
			numOrder.setText(""); 
			bookID.setText("") ;
			quantity.setText(""); 
			itemInfo.setText("");
			subtotal.setText("");
			numOrder.setEditable(true);
			
			// TODO Auto-generated method stub

		}
	}
	
	

	
    private static void createAndShowGUI() {
        
        //Create and set up the window.
        YeOldeBookShoppe frame = new YeOldeBookShoppe("Ye Olde Book Shoppe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.ShopComponts(frame.getContentPane());
        //Use the content pane's default BorderLayout. No need for
        //setLayout(new BorderLayout());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

	public YeOldeBookShoppe(String name) {
		super(name);
		setResizable(true);
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 /* Use an appropriate Look and Feel */
        try {
        	//File inv = new File("C:\\Users\\13528\\OneDrive\\redo\\p1\\inventrory.txt");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        /* Turn off metal's use bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
         
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
        
	}
	

}
