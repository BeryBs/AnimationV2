
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.Random;
import javax.swing.JPanel;
public class AnimationV2 extends JFrame implements ActionListener
{
    private Can canvas=new Can();  //Instance of a canvas
    boolean mythread=true; //flag for controllıng stop-play conditions
    boolean speed=false;//flag for controlling the speed
    boolean add=false;//flag for controlling the speed
    boolean rotate=false;
    boolean scale=false;
    public AnimationV2()
    {

        addWindowListener(new MyFinishWindow());
        this.setSize(800,800); //Setting the width and height
        this.setLocation(500,200);//Setting the location
        this.setTitle("EVIL EYE BEADS "); //Setting the title
        canvas.setBackground(Color.green); //Green for inital background color

        canvas.setPreferredSize(new Dimension(800,500)); //Canvas size

        this.add(canvas);
        this.pack();
        this.setVisible(true);


        // Creating buttons
        Button button_s = new Button();
        button_s.setLabel("Play"); //First button
        button_s.addActionListener(this);

        Button button_b = new Button();
        button_b.addActionListener(this);
        button_b.setLabel("Stop"); //Second button

        Button buttonn = new Button();
        buttonn.addActionListener(this);
        buttonn.setLabel("Speed Up"); //Second button

        Button buttonp = new Button();
        buttonp.addActionListener(this);
        buttonp.setLabel("Change Backgroundcolor"); //Second button

        Button adb = new Button();
        adb.addActionListener(this);
        adb.setLabel("Add"); //Second button


        Button bb = new Button();
        bb.addActionListener(this);
        bb.setLabel("Rotate Yellow Rec"); //Second button


        Button vv = new Button();
        vv.addActionListener(this);
        vv.setLabel("Scale Pink Rec"); //Second button


        //Adding Buttons to the canvas
        canvas.add(button_s);
        canvas.add(button_b);
        canvas.add(buttonp);
        canvas.add(buttonn);
        canvas.add(adb);
        canvas.add(bb);
        canvas.add(vv);
        add(canvas);
        setLayout(null); //setting layout
        canvas.calldraw(); //Drawing canvas

    }
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String but = e.getActionCommand();

        if(but.equals("Stop")) { //Stop condition to stop the animation
            mythread=false;
        }
        else if(but.equals("Play")) {//Play condition to continue the animation
            mythread=true;
        }
        else if(but.equals("Speed Up")){ //Speed condition to speed up the animation
            speed=true;

        }
        else if(but.equals("Rotate Yellow Rec")){ //Speed condition to speed up the animation
            rotate=true;

        }
        else if(but.equals("Scale Pink Rec")){ //Speed condition to speed up the animation
            scale=true;

        }
        else if(but.equals("Change Backgroundcolor")) { //Generating random colors to execute by third button

            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();

            Color randomColor = new Color(r, g, b);
            canvas.setBackground(randomColor);  //Setting the color
            repaint();
        }
        else if(but.equals("Add")){
            add=true;
        }

    }
    public class MyFinishWindow extends WindowAdapter //Adapter controls the exit operation
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    }

    private class Can extends JPanel //Canvas class
    {
        public int x=30,y=30,vx=5,vy=5; //Animation position and velocity values

        public void calldraw()
        {
            Thread thh = new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(15);
                            repaint();
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            thh.start();
        }
        protected Point2D rotateAroundPoint(Point2D point, Point2D ref, double angle) {  //rotatearoundpoint takes related point and the reference with the angle
            float x_1 = (float) (point.getX() - ref.getX());
            float y_1 = (float) (point.getY() - ref.getY());
            float x_rot = (float) (Math.cos(angle)*x_1 - Math.sin(angle)*y_1);
            float y_rot = (float) (Math.sin(angle)*x_1 + Math.cos(angle)*y_1);
            Point2D result = new Point2D.Float((float) ref.getX() + x_rot, (float) ref.getY() + y_rot);
            return result;
        }
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            //2D objects for oval
            Graphics2D g2d = (Graphics2D) g;
            Graphics2D g2d2 = (Graphics2D) g;
            Graphics2D g2d3 = (Graphics2D) g;
            Graphics2D g2d4 = (Graphics2D) g;
            Graphics2D g2dso = (Graphics2D) g;
            Graphics2D oval= (Graphics2D) g;

            int[] xx = {500,700,700,500};
            int[] yy = {300,300,200,200};


            Rectangle boxx = new Rectangle(100, 100, 50, 70);
            Rectangle myBoxx = boxx;


            //  Rectangle yourb = new Rectangle(200, 200, 200, 200);
            // Rectangle myBox2 = yourb;
            if(scale){

                g.drawRect(myBoxx.x, myBoxx.y, myBoxx.width+10, myBoxx.height+10);
                g.setColor(Color.pink);
                g.fillRect(myBoxx.x, myBoxx.y, myBoxx.width+50, myBoxx.height+50);

            }
            else {
                g.drawRect(myBoxx.x, myBoxx.y, myBoxx.width, myBoxx.height);
                g.setColor(Color.pink);
                g.fillRect(myBoxx.x, myBoxx.y, myBoxx.width, myBoxx.height);

            }

            if(rotate){
                Point2D.Float pointanchor = new Point2D.Float(xx[3], yy[3]);  // Turn around left buttom in clockwise

                Point2D.Float pointmy = new Point2D.Float(xx[0], yy[0]); // First coordinates
                Point2D tResult = rotateAroundPoint(pointmy,pointanchor, Math.PI/2); // rotate with coordinate around pointanchor in 90 degrees
                xx[0] = (int) tResult.getX(); //assign new x point
                yy[0] = (int) tResult.getY();//assign new y point

                pointmy= new Point2D.Float(xx[1], yy[1]); // third coordinates
                tResult = rotateAroundPoint(pointmy,pointanchor, Math.PI/2); // rotate with coordinate around pointanchor in 90 degrees
                xx[1] = (int) tResult.getX();//assign new x point
                yy[1] = (int) tResult.getY();//assign new y point

                pointmy= new Point2D.Float(xx[2], yy[2]); // third coordinates
                tResult = rotateAroundPoint(pointmy,pointanchor, Math.PI/2); // rotate with coordinate around pointanchor in 90 degrees
                xx[2] = (int) tResult.getX();//assign new x point
                yy[2] = (int) tResult.getY();//assign new y point

                g.setColor(Color.yellow);
                g.fillPolygon(xx, yy, 4); //fill square
            }
            else{
                xx[0]=500;
                xx[1]=700;
                xx[2]=700;
                xx[3]=500;

                yy[0]=300;
                yy[1]=300;
                yy[2]=200;
                yy[3]=200;
                g.setColor(Color.yellow);
                g.fillPolygon(xx, yy, 4); //fill square
            }

            //Setting for renderigng
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d3.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2d4.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2dso.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);


            if(mythread) {  //Checking the flag conditions
                x += vx;
                y += vy;
                if(speed){  //Speed up if the user pressed Speed
                    x +=3*vx;
                    y += 3*vy;
                }

                if(add) {

                    Rectangle box = new Rectangle(50, 30, 20, 30);
                    Rectangle myBox = box;

                    g.drawRect(myBox.x, myBox.y, myBox.width, myBox.height);
                    g.setColor(Color.RED);
                    g.fillRect(50, 30, 20, 30);

                }

                //Checking x and y conditions
                if (x < 0) {
                    x = 10;
                    vx *= -1;
                }
                if (y < 0) {
                    y = 10;
                    vy *= -1;
                }
                if (x > getWidth()) {
                    x = getWidth() - 10;
                    vx *= -1;
                }
                if (y > getHeight()) {
                    y = getHeight() - 10;
                    vy *= -1;
                }

            }


            //Setting and filling the color and sizes

            g2d.setColor(Color.blue);
            g2d.fillOval(x-10,y-10,89,100);

            g2d2.setColor(Color.white);
            g2d2.fillOval(x+2,y+2,70,70);

            g2d2.setColor(Color.yellow);
            g2d2.fillOval(x+8,y+8,50,55);


            g2d4.setColor(Color.black);
            g2d4.fillOval(x+20,y+20,25,25);


        }
    }

    public static void main(String[] args) {
        AnimationV2 myAnim=new AnimationV2();  //Instance of Animation class

    }
}