/*CO225-Project
  E/16/319  */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import java.lang.*;

//inherit the Mandelbrotset  from the Jpanel
public class Mandelbrotset extends JPanel{
	//declare the parameters
	public static int MAX_ITERATIONS ;
    private static  double Real_Low;
    private static  double Real_Up;
	private static  double Img_Low;
    private static  double Img_Up;

    //mandelbrotset method settinf the value to parameters
    public Mandelbrotset(int max_iterations,double real_low,double real_up,double img_low,double img_up) {
        setPreferredSize(new Dimension(800, 800));
        MAX_ITERATIONS=max_iterations;
        Real_Up=real_up;
        Real_Low=real_low;
        Img_Up=img_up;
        Img_Low=img_low;
    }

    void drawMandelbrotset(Graphics2D g) {
    	//get the size to plot
        int w = getWidth();
        int h = getHeight();
        //create BiffereImage for drawing the set
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //doing throught the every point in the canvas
        IntStream.range(0, h).parallel().forEach(y -> {
            IntStream.range(0, w).parallel().forEach(x -> {
            	//get the complex real part acording the cordination of the point 
            	double complex_real = ((x - w/2)*(Real_Up - Real_Low)/w)+(Real_Low + Real_Up)/2;
            	//get the complex img part acording the cordination of the point 
                double complex_img = ((y - h/2)*(Img_Up - Img_Low)/h)+(Img_Low + Img_Up)/2;
                //set the Zo to 0
                double zx = 0, zy = 0;
                float iterations = 0;
                //try iteration until unsatisfy
                while (zx*zx+zy*zy < 4 && iterations < MAX_ITERATIONS) {
                    double tmp = zx*zx-zy*zy+complex_real;
                    zy = 2*zx*zy+complex_img;
                    zx = tmp;
                    iterations++;
                } 
                int c;
                //setting the collor according to the iteration completed or not
                if (iterations<MAX_ITERATIONS) c = Color.HSBtoRGB(1,0,1);
                else  c= 0;
                image.setRGB(x,y,c);
            });
        });
        g.drawImage(image, 0, 0, null);

    }
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawMandelbrotset(g);
    }


}
