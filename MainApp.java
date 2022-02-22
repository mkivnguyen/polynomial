/*
Michael Nguyen
Final Project MainApp
this program will ask the user for 4 polynomial and do mathmatical operation with them. 
*/
import java.util.*;
import java.text.DecimalFormat;
class MainApp{
   /*
   Will display explanation to the user
   setting up variables 
   and formating decimalformat.
   */
   public static void main(String [] args){
      Scanner scan = new Scanner(System.in);
      System.out.println("This program handles Quadratic Polynomials and allow you to perform\ndifferent mathematical operation on them. ");
      System.out.println("\nFirst, we want to enter information for four polynomials of type \nax^2 + bx + c . ");
      int polyOne, polyTwo;
      double coA, coB, coC;
      DecimalFormat df = new DecimalFormat("0.00");
      /*
      Setting up array of objects with exactly 4 objects
      entering a loop to make sure user only pass in numerical values
      input mismatch will throw message to re-enter correct value
      once the values are entered and the object are filled it will 
      exit the loop 
      */
      Polynomials[] poly = new Polynomials [4];
      boolean check = true;
      while(check){
         for(int i = 0; i<poly.length; i++) {
            try{
               System.out.println("Enter a, b and c for Polynomial " + (i+1));
               coA = scan.nextDouble();
               coB = scan.nextDouble();
               coC = scan.nextDouble();
               poly[i] = new Polynomials(coA, coB, coC);
            } 
            catch(InputMismatchException e){
               System.out.println("Please enter numbers only - try again");
               scan.next();
               i--;
            }
         check = false;
         }
      }
      /* 
      after successfully getting the values message will confirm.
      */
      System.out.println("Great! Below are your options.\n");
      /*
      entering a loop to keep looping until the user decide to exit
      first the menu will print and will get user int and enter into a 
      switch block. 
      */
      boolean flag = true;
      while(flag){
         printMenu();
         int menuSelection = getMenuInt();
         switch(menuSelection){
            /*
            selection 1 will just display the polynomials using the "toString method".. 
            I also created a static method that display the polynomial call printUserPoly
            this block also is checking to make sure polynomials are not null or has no value
            */
            case 1:  
               for(int i = 0; i<poly.length; i++){
                  if(poly[i] != null){   
                     System.out.println((i+1) + ") - " + poly[i].toString());
                  }else{
                     System.out.println(poly[i]);
                  }
               }
               break;
            /*
            case 2 will update which ever polynomial user wishes to then it will
            call the method to display the polynomials
            then assigning an int to polyOne after calling the getPolySeat method,
            entering a loop to make sure that each value is a numerical value and then
            assinging new doubles to each coefficient
            and finally passing in new coefficient
            */   
            case 2: 
               printUsersPoly(poly);      
               System.out.println("Which Polynomial would you like to update?");
               polyOne = getPolySeat(poly); 
               System.out.println("Enter new values for a, b, and c.");
               try{
                  coA = scan.nextDouble();
                  coB = scan.nextDouble();
                  coC = scan.nextDouble();
                  poly[polyOne-1] = new Polynomials(coA, coB, coC);
                  System.out.println("Polynomials Successfully updated!");
               }catch(InputMismatchException e){
                  System.out.println("Please use numerical values only");
                  scan.nextLine();
               }
               break;
            /*
            using the add method to add two polynomials and displaying it to a new Polynomial call answer.
            couldve made this code better by puting it in a loop to reDisplaying to the user to get the FIRST selection
            once condition is met then exit and take the next.. but i got lazy AND you didn't specify! (-_-)
            */
            case 3: 
               printUsersPoly(poly);            
               System.out.println("Which two polynomials would you like to add? Please enter FIRST selection:");
               polyOne = getPolySeat(poly);
               System.out.println("Please enter SECOND selection:");
               polyTwo = getPolySeat(poly);
               Polynomials answer = poly[polyOne-1].add(poly[polyTwo-1]);
               System.out.println("The answer is :" + answer.toString());
               break;
               
            /*
            This block of code will enter a loop and first check to see if the first coefficient is zero if not then 
            it will check for the discriminant to see if the polynomial is factorable. If it is then it will continue to 
            go through the code and run the findPlus and findMinus. If not then it will tell the user that the polynomial   
            can not factor. 
            */   
            case 4: 
               boolean checkPoly = true;
               while(checkPoly){
                  System.out.println("Which polynomial would you like to factor?");
                  printUsersPoly(poly);
                  polyOne = getPolySeat(poly);
                  Polynomials zeroCheck = poly[polyOne-1];
                  double zero = 0;
                  if(zeroCheck.getCoA()== zero){
                     System.out.println("You Coefficient 'A' is a zero and cannot be factored");
                     checkPoly = false;
                  }else if(zeroCheck.findDisc()<0){
                     System.out.println("Sorry your polynomial cannot be factored");
                     checkPoly = false;
                  }else{
                     System.out.println("Answer are: " + df.format(zeroCheck.findPlus()) + " and " + df.format(zeroCheck.findMinus()));
                     checkPoly = false;
                  } 
               }             
               break;
            /*
            THis block of code will ask the user for an X value then it will plug it in to the polynomial
            and solve for that equation.
            */   
            case 5: 
               System.out.println("What is X");
               double xValue = getDouble();
               System.out.println("Your x value is " + xValue);
               for(int i = 0; i <poly.length;i++){
                  double xAnswer = poly[i].enterX(xValue);
                  System.out.println((i+1)+ ".) " + poly[i] + " = " + df.format(xAnswer));
               }
               break;
            /*
            Case 6 will go through the array and check to see if there is any matching polynomials
            if there is it will return the 2 matching polynomials position and display the polynomial 
            if there is no matching polynomial it will also tell the user that there is no matching 
            polynomials. 
            */   
            case 6: 
               int matchCheck = 0;
               for(int i = 0; i<poly.length;i++){
                  for(int j = 0; j<i;j++){
                     if(poly[i].equals(poly[j])){
                        System.out.println("Polynomials " + (j+1) + " and " + (i+1) + " match: " + poly[i]);
                     }else{
                     matchCheck ++;
                     }
                  }
               }
               if(matchCheck >5){
                  System.out.println("You have no matching Polynomials");
               }
               break;
            //This block will set all the array to null   
            case 7:                
               for(int i = 0; i<poly.length;i++){
                  poly[i] = null;
               }
               System.out.println("All polynomials have been cleared.");
               break;
            //Exit the menu by turning the flag to false
            case 8: 
               System.out.println("GoodBye - hopefuly the try-catch is good for extra credit for my crappy\nlast exam grade (-_-)\nP.S. Can you send me some projects to do lol");
               flag = false;
               break; 
            //defualt print
            default: System.out.println("Enter a value between 1 and 8");
               break;    
         }
      }
   }
   /*
   A static method call printMenu that will print when it gets called on
   */ 
   public static void printMenu(){
      System.out.println();
      System.out.println("Select an option:");
      System.out.println("1. Print all Polynomial");
      System.out.println("2. Update a Polynomial");
      System.out.println("3. Add two polynomials");
      System.out.println("4. Factor Using Quadraic Formula");
      System.out.println("5. Enter X and Solve");
      System.out.println("6. Compare polynomials");
      System.out.println("7. Clear ALL polynomials");
      System.out.println("8. Exit the Program");
   }
   /*
   Static method to print users array of polynomials when it gets called on
   */
   public static void printUsersPoly(Polynomials[] poly){
      System.out.println("Your polynomials are:");
      for(int i = 0; i<poly.length; i++){
      System.out.println((i+1) + ") - " + poly[i]);
      }
   }
   /*
   static method to find a integer between 1 and 8
   */
   public static int getMenuInt(){
      Scanner kb = new Scanner(System.in);
      boolean flag = true;
      int selection = 0;
      while(flag){
         try{
            selection = kb.nextInt();
            if(selection < 1 || selection > 8){
               System.out.println("Value Must be between 1 and 8. Try again.");
               printMenu();
            }else{
               flag = false;
            }
         }catch(InputMismatchException e){
            System.out.println("Try again - Please enter a NUMERICAL value");
            printMenu();
            kb.next();
         }catch(Exception e){
            System.out.println(e.getMessage());
         }
      }
      return selection;
   }
   /* 
   static method to find a seat between 1 and 4 and must be an integer and catch everthing else
   */
   public static int getPolySeat(Polynomials[] originalPoly){
      Scanner kb = new Scanner(System.in);
      boolean flag = true;
      int selection = 0;
      while(flag){
         try{
            selection = kb.nextInt();
            if(selection < 1 || selection > 4){
               System.out.println("Value Must be between 1 and 4. Try again.");
               printUsersPoly(originalPoly);
            }else{
               flag = false;
            }  
         }catch(InputMismatchException e){
            System.out.println("Try again - Please enter a NUMERICAL value");
            printUsersPoly(originalPoly);
            kb.next();
         }catch(Exception e){
            System.out.println(e.getMessage());
            printUsersPoly(originalPoly);
         } 
      }
      return selection;
   }
   /*
   static method to check if  user is entering a numerical value.
   */
   public static double getDouble(){
      Scanner kb = new Scanner(System.in);
      boolean flag = true;
      double input = 0;
      while(flag){
         try{
            input = kb.nextDouble();
            flag = false;
         }catch(InputMismatchException e){
            System.out.println("Try again - Enter a NUMERICAL value only");
            kb.next();
         }
      }
      return input;
   }
}