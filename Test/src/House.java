import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class House extends JPanel {

    MyCanvas canvas;
    JSlider sliderRotateTheta;
    double transX = 0.0;
    double transY = 0.0;
    double rotateTheta = 0.0;
    double rotateX = 150.0;
    double rotateY = 150.0;
    double scaleX = 1.0;
    double scaleY = 1.0;
    float width = 1.0f;

    public House() {
        super(new BorderLayout());
        JPanel controlPanel = new JPanel(new GridLayout(3, 3));
        add(controlPanel, BorderLayout.NORTH);
        controlPanel.add(new JLabel("Translate(dx,dy): "));
        // To control rotation
        controlPanel.add(new JLabel("Rotate(Theta,ox,oy): "));
        sliderRotateTheta = setSlider(controlPanel, JSlider.HORIZONTAL, 0, 360, 0, 90, 45);
        canvas = new MyCanvas();
        add(canvas, "Center");
    }

    public JSlider setSlider(JPanel panel, int orientation, int minimumValue,int maximumValue, int initValue, int majorTickSpacing, int minorTickSpacing) {
        JSlider slider = new JSlider(orientation, minimumValue, maximumValue, initValue);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                JSlider tempSlider = (JSlider) e.getSource();

                if (tempSlider.equals(sliderRotateTheta)) {
                    rotateTheta = sliderRotateTheta.getValue() * Math.PI / 180;
                    canvas.repaint();
                } 
            }
        });
        panel.add(slider);

        return slider;
    }

    class MyCanvas extends Canvas {

        public void paint(Graphics g) {
            Graphics2D g2D = (Graphics2D) g;

            g2D.rotate(rotateTheta, rotateX, rotateY);

        }

        public void drawHome(Graphics2D g2D) {
            Line2D line1 = new Line2D.Float(100f, 200f, 200f, 200f);
            Line2D line2 = new Line2D.Float(100f, 200f, 100f, 100f);
            Line2D line3 = new Line2D.Float(100f, 100f, 200f, 100f);
            Line2D line5 = new Line2D.Float(200f, 100f, 200f, 200f);

            g2D.draw(line1);
            g2D.draw(line2);
            g2D.draw(line3);
            g2D.draw(line5);
        }
    }

    public static void main(String[] a) {
        JFrame f = new JFrame();
        f.getContentPane().add(new House());
        f.setDefaultCloseOperation(1);
        f.setSize(700, 550);
        f.setVisible(true);
    }
}