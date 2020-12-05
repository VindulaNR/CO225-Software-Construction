/*CO225-Project
  E/16/319  */

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.IntStream;
import java.lang.*;
 
//inherit class the Jpanel to JuliaSet 
public class JuliaSet extends JPanel {
    //define the parameters
    public static int MAX_ITERATIONS ;
    private static double COMPLEX_X ;
    private static double COMPLEX_Y ;

    //method of julia set
    public JuliaSet(int max_iterations,double cx,double cy) {
        //set the dimmentin of the ploting six=ze to 800*800 pixel
        setPreferredSize(new Dimension(800, 800));
        setBackground(Color.white);
        //Give the pass values to the parameters
        MAX_ITERATIONS=max_iterations;
        COMPLEX_X=cx;
        COMPLEX_Y=cy;
    }
    
    //method to draw julia set
    void drawJuliaSet(Graphics2D g) {
        //geeting the widht and height values
        int w = getWidth();
        int h = getHeight();
        //create BiffereImage for drawing the set
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //doing throught the every point in the canvas
        IntStream.range(0, w).parallel().forEach(x -> {
            IntStream.range(0, h).parallel().forEach(y -> {
                //get cordinates of x,y plane according the point
                double zx = (+x -w / 2)/(0.5*w);
                double zy = (-y + h / 2)/(0.5*h);
                float i = MAX_ITERATIONS;
                //do the Zn+1=Zn^2+C 
                //for the condition ABS(Zn>2)
                while (zx * zx + zy * zy < 4 && i > 0) {
                    double tmp = zx * zx - zy * zy + COMPLEX_X;
                    zy = 2.0 * zx * zy + COMPLEX_Y;
                    //put the tmp to zx
                    zx = tmp;
                    i--;
                }
                int c;
                //get the white colour if not iterate for 1000 times
                if (i>0) c = Color.HSBtoRGB(1,0,1);
                else  c= 0;             //if succes 1000 iteration black
                //give the colour to the corresponding cordinates
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
        drawJuliaSet(g);
    }
 
}
 