import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Kulki
{
    static final private int SCREEN_WIDTH=600;
    static final private int SCREEN_HEIGHT=800;

    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Gra w kulki!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Panel());
        frame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));

        frame.pack();
        frame.setVisible(true);
    }

}


class Panel extends JPanel
{

    private int size=20;
    Event listener = new Event();
    private ArrayList<Kula> listaKul;
    Timer timer;
    final int DELAY=33;

    public Panel()
    {
        listaKul=new ArrayList<>();
        setBackground(Color.BLACK);
        addMouseListener(listener);
        addMouseWheelListener(listener);
        timer=new Timer(DELAY, listener);
        timer.start ();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.setColor(Color.YELLOW);
        // g.drawString(String.valueOf(size), 40, 40);
        g.drawString("Liczba kul: " + listaKul.size(),30,40);
        g.drawString("Rozmiar: " + size,30,60);

        for (Kula k: listaKul)
        {
            g.setColor(k.color);
            g.drawOval(k.x,k.y,k.size,k.size);
        }

    }


    class Event implements MouseListener, ActionListener, MouseWheelListener
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            listaKul.add(new Kula(e.getX(), e.getY(), size));
            repaint();

            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            for (Kula k: listaKul)
            {
                k.update();
            }
            repaint();
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e)
        {
            if(size+e.getWheelRotation()*4>=0)
            {
                size+=e.getWheelRotation()*4;
                System.out.println("Rozmiar: "+ size + "  | Wartość z kółka: " + e.getWheelRotation());
            }
            else
            {
                System.out.println("Zbyt mala wielkosc kolka!");
            }

            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private class Kula
    {

        public int x, y, size, xspeed, yspeed;
        public Color color;
        private final int MAX_SPEED=5;
        static private int randSpeed;

        public Kula(int x,int y,int size)
        {
            this.x=x;
            this.y=y;
            this.size = size;
            color = new Color((float) Math.random(),(float) Math.random(),(float) Math.random());

            do
            {
                randSpeed=(int) ((Math.random())*MAX_SPEED* 2-MAX_SPEED);
            }
            while(randSpeed==0);
            xspeed=randSpeed;

            do
            {
                randSpeed=(int) ((Math.random())*MAX_SPEED*2-MAX_SPEED);
            }
            while(randSpeed==0);
            yspeed=randSpeed;
        }

        public void update()
        {
            x+=xspeed;
            y+=yspeed;
            /*if(x>getWidth())
            {
                System.out.println("Jestem poza ekranem!");
            }*/

            if (x<=0 || x>=getWidth()-size && xspeed>0)
            {
                xspeed= -xspeed;
            }
            if (y<=0 || y>=getHeight()-size && yspeed>0)
            {
                yspeed= -yspeed;
            }
        }
    }
}