/*
Michael Nguyen
Final Project MainApp
*/
import java.util.*;
import java.text.DecimalFormat;
class MainAppTest{
   //this program will ask the user for 4 polynomial and do mathmatical operation with them. 
   public static void main(String [] args){
   
      Scanner scan = new Scanner(System.in);
      System.out.println("This program handles Quadratic Polynomials and allow you to perform\ndifferent mathematical operation on them. ");
      System.out.println("\nFirst, we want to enter information for four polynomials of type \nax^2 + bx + c . ");
      int polyOne, polyTwo;
      double coA, coB, coC, xValue;
      
      /*
      Setting up array of objects with exactly 4 objects
      entering a loop to make sure user only pass in numerical values
      input mismatch will throw message to re-enter correct value
      */
      Polynomials[] poly = new Polynomials [4];
      double[] getArrayPoly;
      
         for(int i = 0; i<poly.length; i++) {
               System.out.println("Enter a, b and c for Polynomial " + (i+1));
               getArrayPoly = getDouble(3);
               coA = getArrayPoly[0];
               coB = getArrayPoly[1];
               coC = getArrayPoly[2];
               poly[i] = new Polynomials(coA, coB, coC);

         
         }
      // after successfully getting the values message will confirm.
      System.out.println("Great! Below are your options.\n");
      boolean flag = true;
      
      /*
      entering a loop to keep looping until the user decide to exit
      first the menu will print and will get user int and enter into a 
      switch block. 
      */
      while (flag){
         printMenu();
         int menuSelection = getMenuInt();
         switch(menuSelection){
            
            /*
            selection 1 will just display the polynomials using the "toString method".. 
            I also created a static method that display the polynomial call printUserPoly
            checking to make sure polynomials are not null or has no value
            */
            case 1:  
               for(int i = 0; i<poly.length; i++){
                  if (poly[i] != null){   
                  System.out.println((i+1) + ") - " + poly[i].toString());
                  } else{
                     System.out.println(poly[i]);
                  }
               }
               break;
            /*
            case 2 will update which ever polynomial user wishes to then it will
            call the method to display the polynomials
            then assigning an int to polyOne after calling the getPolySeat method,
            assinging new doubles to each coefficient
            and finally passing in new coefficient
            */   
            case 2: 
               printUsersPoly(poly);      
               System.out.println("Which Polynomial would you like to update?");
               polyOne = getPolySeat(poly); 
               System.out.println("Enter new values for a, b, and c.");
               getArrayPoly = getDouble(3);
               coA = getArrayPoly[0];
               coB = getArrayPoly[1];
               coC = getArrayPoly[2];
               poly[polyOne-1] = new Polynomials(coA, coB, coC);

               break;
               
            //using the add method to add two polynomials and displaying it to a new Polynomial call answer. 
            case 3: 
               printUsersPoly(poly);            
               System.out.println("Which two polynomials would you like to add? Please enter first selection:");
               polyOne = getPolySeat(poly);
               System.out.println("Please enter Second selection:");
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
               DecimalFormat df = new DecimalFormat("0.00");
               while(checkPoly){
                  System.out.println("Which polynomial would you like to factor?");
                  polyOne = getPolySeat(poly);
                  Polynomials zeroCheck = poly[polyOne-1];
                  double zero = 0;
                  if(zeroCheck.getCoA()== zero){
                     System.out.println("You Coefficient 'a' is a zero and cannot be factored");
                     checkPoly = false;
                  } else if(zeroCheck.findDisc()<0){
                     System.out.println("Sorry your polynomial cannot be factored");
                     checkPoly = false;
                  } else{
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
               double[] case5Double = getDouble(1);
               xValue = case5Double[0];
               System.out.println("Your x value is " + xValue);
               for(int i = 0; i <4;i++){
                  double xAnswer = poly[i].enterX(xValue);
                  System.out.println((i+1)+ ".) " + poly[i] + " = " + xAnswer);
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
                     if (poly[i].equals(poly[j])){
                        System.out.println("Polynomials " + (j+1) + " and " + (i+1) + " match: " + poly[i]);
                     } else{
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
            //Exit the menu   
            case 8: 
               System.out.println("GoodBye - hopefuly the try-catch is good for extra credit for my crappy\nlast exam grade (-_-)\nP.S. Can you send me some projects to do lol");
               flag = false;
               break; 
            //defualt 
            default: System.out.println("Enter a value between 1 and 8");
               break;
                    
         }
      }
      

   }
   //Print menu*******************************************************
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
   //Static method to print users polynomials
   public static void printUsersPoly(Polynomials[] poly){
     System.out.println("Your polynomials are:");
     for(int i = 0; i<poly.length; i++){
       System.out.println((i+1) + ") - " + poly[i]);
     }
   }

   // static method to find a integer between 1 and 8***************************************
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
            } else {
               flag = false;
            }
         } catch (InputMismatchException e){
            System.out.println("Must be a Whole Number");
            printMenu();
            kb.next();
         } catch (Exception e){
            System.out.println(e.getMessage());
         }
         
      }
      return selection;
   }
   
   
   
   // static method to find a seat between 1 and 4 and must be an integer and catch everthing else
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
            
            
         } catch (InputMismatchException e){
            System.out.println("Must be a Whole Number");
            printUsersPoly(originalPoly);

            kb.next();
         } catch (Exception e){
            System.out.println(e.getMessage());
            printUsersPoly(originalPoly);

         }
         
      }
      return selection;
   }
   // static method to get user's numerical value if not will ask for a double only
   public static double[] getDouble(int number_of_expected_values){
      Scanner kb = new Scanner(System.in);
      double[] input = new double[number_of_expected_values];
      
      for (int i = 0; i<input.length; i++){
         try{
            input[i] = kb.nextDouble();
         }
         catch(InputMismatchException e){
            System.out.println("Enter a numerical value only"); 
            kb.nextLine();
            i--;
         }
      }
      return input;
   }
}