package com.ericsson.v1.util;

/**
* Java program to demonstrate What is nested static and non static class.
* How to create instance of static and non static class and How to call
* methods of nested static and Inner class in Java. Overall comparison of
* static vs non static class.
*/
class Outer {
	
   private static String message = "HelloWorld";

   // Static nested class
   private static class MessagePrinter{
       //Only static member of Outer class is directly accessible in nested static class
	   private String s;
	   
	   
       public String getS() {
		return s;
	}


	public void setS(String s) {
		this.s = s;
	}


	@Override
	public String toString() {
		return "MessagePrinter [s=" + s + "]";
	}


	public void printMessage(){
           // Compile time error if message field is not static
           System.out.println("Message from nested static class : " + message);
       }
   }

  /* //non static nested class - also called Inner class
   private class Inner{
    
       // Both static and non static member of Outer class is accessible in this Inner class
       public void display(){
           System.out.println(" Message from non static nested or Inner class : " + message);
       }
   }*/


   private void method(MessagePrinter p) {
	   p.setS("HOSAIN");
   }
   
   // How to create instance of static and non static nested class
   public static void main(String... args){
    
       // creating instance of nested Static class
       Outer.MessagePrinter printer = new Outer.MessagePrinter();
    
       //calling non static method of nested static class
       printer.printMessage();
       
       printer.setS("IQBAL");
       System.out.println("before printer " +printer);
       
       new Outer().method(printer);
       
       System.out.println("printer " +printer);
       
    
       // creating instance of non static nested class or Inner class
    
       // In order to create instance of Inner class you need an Outer class instance
    
      /* Outer outer = new Outer(); //outer class instance for creating non static nested class
    
       Outer.Inner inner  = outer.new Inner();
    
       //calling non static method of Inner class
       inner.display();
    
       // we can also combine above steps in one step to create instance of Inner class
       Outer.Inner nonStaticIner = new Outer().new Inner();
    
       // similarly you can now call Inner class method
       nonStaticIner.display();*/
   }

}

