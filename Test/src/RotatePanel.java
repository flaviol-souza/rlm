import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;

public class RotatePanel extends JPanel {
    private Image image;
    private double currentAngle;
 
    public RotatePanel()
    {}
 
    public RotatePanel(Image image) {
        this.image = image;
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(image, 0);
        try {
            mt.waitForID(0);
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
 
    public void setImage(Image image)
    {
       this.image = image;
       MediaTracker mt = new MediaTracker(this);
       mt.addImage(image, 0);
       try {
           mt.waitForID(0);
       }
       catch (Exception e) {
           e.printStackTrace(System.err);
       }
    }
 
    public void rotate() {
        //rotate 2 degrees at a time
        currentAngle-=2.0;
        if (currentAngle >= 360.0) {
            currentAngle = 0;
        }
        repaint();
    }
 
    public void rotateWithParam(int rotateDegree) {
        //rotate base on rotateDegree parameter degrees at a time
        currentAngle = rotateDegree;
        if (currentAngle >= 360.0) {
            currentAngle = 0;
        }
        repaint();
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        AffineTransform origXform = g2d.getTransform();
        AffineTransform newXform = (AffineTransform)(origXform.clone());
        //center of rotation is center of the panel
        int xRot = this.getWidth()/2;
        int yRot = this.getHeight()/2;
        newXform.rotate(Math.toRadians(currentAngle), xRot, yRot);
        g2d.setTransform(newXform);
        //draw image centered in panel
        int x = (getWidth() - image.getWidth(this))/2;
        int y = (getHeight() - image.getHeight(this))/2;
        g2d.drawImage(image, x, y, this);
        g2d.setTransform(origXform);
    }

}//end of class

