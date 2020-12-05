/*CO225-Project
  E/16/319  */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import java.lang.*;
 
//Fractal class
//Take the command line arguments
//include the main method
class Fractal{
	//main method
	public static void main(String[] args) {
		//the first args which represent the type of the set
        String set_type1=args[0];	

        //checking for the julia set	
        if (set_type1.equals("julia")){
        	//set the othe three argument for julia
        	Double in1=Double.parseDouble(args[1]);
        	Double in2=Double.parseDouble(args[2]);
        	int max_iterations1= Integer.parseInt(args[3]);
        	SwingUtilities.invokeLater(() -> {
        		//create the new JFrame frame
	            JFrame frame = new JFrame();
	            //set the close button as exit
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            //title of the frame
	            frame.setTitle("Julia Set");
	            frame.setResizable(false);
	            //add the jullia set inside  the fram
	        	frame.add(new JuliaSet(max_iterations1,in1,in2), BorderLayout.CENTER);
	            frame.pack();
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	            frame.setBackground(Color.white);		//set background to white
        	});
        }
        //check for the mandelbrot set
        if (set_type1.equals("Mandelbrot")){
        	//get the othe three argument
        	Double in1=Double.parseDouble(args[1]);
        	Double in2=Double.parseDouble(args[2]);
        	Double in3=Double.parseDouble(args[3]);
        	Double in4=Double.parseDouble(args[4]);
        	int max_iterations= Integer.parseInt(args[5]);
        	SwingUtilities.invokeLater(() -> {
        		//create the new frame 
	            JFrame frame = new JFrame();
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            //tite of the frame 
	            frame.setTitle("Mandelbrot Set");
	            frame.setResizable(false);
	            //add new mandelset pannel inside the frame
	        	frame.add(new Mandelbrotset(max_iterations,in1,in2,in3,in4));
	            frame.pack();
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	            frame.setBackground(Color.white);
         	});
   		}
    }
}