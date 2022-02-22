/*Michael Nguyen
Final Project - Polynomial class
This is a template class for the main app. 
*/
class Polynomials{
   //Private Members
   private double coA;
   private double coB;
   private double coC;
   
   //Constructors that takes no Parameter and set default value
   public Polynomials(){
      coA = 1;
      coB = 1;
      coC = 1;
   }
   //Constructor that takes 3 Parameter 
   public Polynomials(double sideA, double sideB, double sideC){
      coA = sideA;
      coB = sideB;
      coC = sideC;
   }
   //getter-accessor
   public double getCoA(){
      return coA;
   }
   public double getCoB(){
      return coB;
   }
   public double getCoC(){
      return coC;
   }
   //setter - modifier 
   public void setCoA(double sideA){
      coA = sideA;
   }
   public void setCoB(double sideB){
      coB = sideB;
   }
   public void setCoC(double sideC){
      coC = sideC;
   }
   //toString()
   public String toString(){
      return coA + "x^2 + " + coB + "x + " + coC;
   }
   //public instance method
   public double findDisc(){
       double disc, coA, coB, coC;
       coA = this.getCoA();
       coB = this.getCoB();
       coC = this.getCoC();
       return disc = (coB*coB) - (4*coA*coC);
   }
   
   // instance method that return the positive portion of the quadratic equation
   public double findPlus(){
      double plus, coA, coB, coC;
      coA = this.getCoA();
      coB = this.getCoB();
      coC = this.getCoC();
      return plus = ((-coB) + (Math.sqrt(findDisc())))/(2*coA);
      
   }
   // instance method that return the negative  portion of the quadratic equation
   public double findMinus(){
      double minus, coA, coB, coC;
      coA = this.getCoA();
      coB = this.getCoB();
      coC = this.getCoC();
      return minus = ((-coB) - (Math.sqrt(findDisc())))/(2*coA);
      
   }
   // add function that adds the first poly to the second poly 
   public Polynomials add(Polynomials poly){
      double coA, coB, coC, coA1, coB1, coC1;
      coA = this.getCoA();
      coB = this.getCoB();
      coC = this.getCoC();
      coA1 = poly.getCoA();
      coB1 = poly.getCoB();
      coC1 = poly.getCoC();
      double newCoA = coA + coA1;
      double newCoB = coB + coB1;
      double newCoC = coC + coC1;
      Polynomials newPoly = new Polynomials(newCoA, newCoB, newCoC);
      return newPoly;
   }
   // replace x with users input
   public double enterX(double num){
      double numX, coA, coB, coC, answer;
      coA = this.getCoA();
      coB = this.getCoB();
      coC = this.getCoC();
      double newA = coA*Math.pow(num,2);
      double newB = coB*num;
      return answer = newA + newB + coC;
      
   }
   //compare two poly and return a boolean
   public boolean equals(Polynomials poly1){
      double coA, coB, coC, coA1, coB1, coC1;
      coA = this.getCoA();
      coB = this.getCoB();
      coC = this.getCoC();
      coA1 = poly1.getCoA();
      coB1 = poly1.getCoB();
      coC1 = poly1.getCoC();
      if(coA==coA1 && coB==coB1 && coC==coC1){
         return true;
      }
      return false;
   }
   
   
}