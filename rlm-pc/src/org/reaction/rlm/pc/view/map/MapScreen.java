/**
 **********************************************************************************
 *                   ------   RLM IA   ------    
 *
 * @category   IC / TCC
 * @author     Flavio Souza    <flavioluiz.ssouza@gmail.com>
 * @guiding    Rodrigo Malara  <rmalara.uniara@gmail.com>
 * @guiding    Rodrigo Bianchi <rodrigo.bianchi@gmail.com>
 * @copyright  Reation Team
 * @license    http://www.reationteam.com.br
 * @version    SVN: 2.0.0
 * @see        www.uniara.com.br
 * 
 * 
 * Purpose: This project was developed to obtensão the 
 * title of a Computer Engineer Flavio Luiz dos Santos de Souza
 * 
 * 
 * LICENSE: Permission is hereby granted, free of charge, to any person obtaining 
 * a copy of this solution to deal with the publication, use or customization of 
 * the Software without restriction to whom it is provided, subject to the following 
 * conditions:
 * 
 * The notice of Reaction Team and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING WITHOUT LIMITATION WARRANTIES OF MERCHANTABILITY FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER 
 * IN AN ACTION OF CONTRACT, OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH 
 * THE SOFTWARE OR THE USE OR OTHERS IN THE SOFTWARE.
 * 
 *	
 **********************************************************************************
 */
package org.reaction.rlm.pc.view.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.TextField;

import org.reaction.rlm.comm.data.DataShared;
import org.reaction.rlm.comm.data.Line;
import org.reaction.rlm.comm.data.Particle;
import org.reaction.rlm.comm.data.TypeData;

/**
 * @author Flavio Souza
 * 
 */
public class MapScreen extends Map {

	/**
	 * 
	 */
	private static final long serialVersionUID = -677687524288703181L;

	private static final int REDUCTION = 30;
	
	/**
	 * The robot path is drawn and updated on this object. <br>
	 * created by makeImage which is called by paint(); guarantees image always
	 * exists before used;
	 */
	Image offScreenImage;
	/**
	 * width of the dawing area;set by makeImage,used by clearImage
	 */
	int imageWidth;
	/**
	 * height of the dawing are; set by makeImage,used by clearImage
	 */
	int imageHeight;
	/**
	 * set by paint, clear; used by paint; indicates first plot of robot
	 * position
	 */
	boolean first = true;
	/**
	 * the graphics context of the image; set by makeImage, used by all methods
	 * that draw on the image
	 */
	private Graphics2D osGraphics;
	/**
	 * robot position ; used by checkContinuity, drawRobotPath
	 */
	private int robotPrevX = 50;
	/**
	 * robot position; used by checkContinuity, drawRobotPath
	 */
	private int robotPrevY = 400;
	int gridSpacing = 25;
	int orig = 75;
	int xOrig;
	boolean isSetPoint;
	float xOrigSimulate,  yOrigSimulate, hOrigSimulate;
	float xOrigReal,  yOrigReal;
	
	/**
	 * y origin in pixels
	 */
	private int y0;
	private int x0;
	/**
	 * node status - true if blocked; set by drawObstacle, used by drawRobotPath
	 */

	public TextField textX;
	public TextField textY;
	boolean block = false;

	/**
	 * simple constructor
	 */
	public MapScreen() {
		setBackground(Color.white);
		this.isSetPoint = false;
		System.out.println(" Mars map constructor ");
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#setPointSimulate(double, double)
	 */
	@Override
	public void setPointSimulate(float x, float y, float h) {
		this.xOrigSimulate = x;
		this.yOrigSimulate = y;
		this.hOrigSimulate = h;
		this.isSetPoint = true;
	}
	
	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#setPointReal(float, float)
	 */
	@Override
	public void setPointReal(float x, float y) {
		this.xOrigReal = x;
		this.yOrigReal = y;
	}
	
	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getIsPoint()
	 */
	@Override
	public boolean getIsPoint() {
		return this.isSetPoint;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getDistancesOrigin()
	 */
	@Override
	public Double[] getDistancesOrigin() {
		
		return this.getSimulator().getM().getDistancePointOrig(this.xOrigSimulate, this.yOrigSimulate, this.hOrigSimulate);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(this.excuteSimulator == true && g != null){
			g.fillOval(xpixel((int)this.xOrigSimulate), ypixel((int)this.yOrigSimulate, false), 10, 10);
		}else{
			//g.fillOval(xpixel(-5), ypixel(6, false), 10, 10);
			g.fillOval(xpixel((int)this.xOrigSimulate), ypixel((int)this.yOrigSimulate, false), 10, 10);
		}
		
		if(this.getSimulator().getM().getParticles() != null && this.getSimulator().getM().getParticles().size() > 0){
			this.printParticles(g);
		}
		
		/*
		if(!excuteSimulator){
			g.setColor(Color.blue);
			g.drawOval(xpixel(this.xOrigReal), ypixel(this.yOrigReal + 6, false), 12, 12);
		}
		*/
	}
	
	/**
	 * Initialize the off screen canvas<br>
	 * Create the offScreenImage, or make a new one if applet size has changed.
	 */
	public void makeImage() {
		if (offScreenImage == null || first) {

			imageWidth = getSize().width;
			imageHeight = getSize().height;
			y0 = 20 + imageHeight - orig;
			// x0 = imageWidth/2;
			xOrig = imageWidth / 2;
			System.out.println(imageWidth + " " + imageHeight);
			offScreenImage = createImage(imageWidth, imageHeight);
			osGraphics = (Graphics2D) offScreenImage.getGraphics();
			osGraphics.setColor(getBackground());
			osGraphics.fillRect(0, 0, imageWidth, imageHeight);// erase
																// everything
			drawGrid();
		}
	}

	/**
	 * Copy off screen canvas to the screen.
	 **/
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (offScreenImage == null)
			makeImage();
		
		g.drawImage(offScreenImage, 0, 0, this);
	}

	/**
	 * draws the grid with labels
	 */
	public void drawGrid() {

		osGraphics.setColor(Color.green); // Set the line color
		// for y axis
		for (float y = 0; y < 13; y += 0.5f) {
			if (y == 6)
				osGraphics.setColor(Color.blue);
			
			osGraphics.drawLine(xpixel(-8), ypixel(y, false), xpixel(8), ypixel(y, false));
			osGraphics.setColor(Color.green);
		}
		// x axis
		for (float x = -8; x < 8.1; x += 0.5f) {
			if (x == 0)
				osGraphics.setColor(Color.blue);
			
			osGraphics.drawLine(xpixel(x), ypixel(0, false), xpixel(x), ypixel(12.5f, false));
			osGraphics.setColor(Color.green);
		}

		osGraphics.setColor(Color.black); // set number color
		// for y axis
		for (int y = 0; y < 13; y++)
			osGraphics.drawString(y - 6 + "", xpixel(-8.3f), ypixel(y - 0.1f, false));
		// x axis
		for (int x = -8; x < 9; x++)
			osGraphics.drawString(x + "", xpixel(x - 0.1f), ypixel(-0.4f, false));

		robotPrevX = xpixel(0);
		robotPrevY = ypixel(9, true);

		int xmax = orig + 32 * gridSpacing;// pixels
		System.out.println("x0 " + x0 + " " + xOrig + " " + xmax);

		if(excuteSimulator)
			this.mapSimulator();

		this.repaint();
	}

	
	private void printParticles(Graphics g){
		g.setColor(Color.red);
		
		for (Particle p : simulator.getM().getParticles()) {
			g.drawOval(xpixel(p.getX()), ypixel(p.getY(), false), 5, 5);
		}
	}

	private void mapSimulator() {

		this.setSimulator(new MapSimulator(this));

		osGraphics.setColor(Color.black);
		for (Line p : simulator.getLines()) {
			osGraphics.drawLine(xpixel(p.getStartPoint().getX()),ypixel(p.getStartPoint().getY(), false), xpixel(p.getEndPoint().getX()),ypixel(p.getEndPoint().getY(), false));
		}
	}

	/**
	 * Obstacles shown as magenta dot
	 */
	public void drawObstacle(float xx, float yy) {
		int x = xpixel(xx); // coordinates of intersection
		int y = ypixel(yy, true);
		block = true;
		
		System.out.print("Point x=" + x + " y=" + y);
		
		if (x > 0 && y > 0) {
			osGraphics.setColor(Color.magenta);
			osGraphics.fillOval(x - 2, y - 2, 5, 5);// bounding rectangle is 10
													// x 10
		}
		this.repaint();
	}

	/**
	 * blue line connects current robot position to last position if adjacent to
	 * current position
	 */
	public void drawRobotPath(float xx, float yy) {
		int x = xpixel(xx);
		int y = ypixel(yy, true);

		if (x > 0 && y > 0)
			osGraphics.setColor(Color.blue);

		osGraphics.fillOval(x - 2, y - 2, 4, 4); // show robot position

		repaint();

		osGraphics.drawLine(robotPrevX, robotPrevY, x, y);
		robotPrevX = x;
		robotPrevY = y;
	}

	/**
	 * convert grid coordinates to pixels
	 */
	public int xpixel(double d) {
		return (int) (xOrig + (d * 2 * gridSpacing));
	}

	public int ypixel(double d, boolean coordinates) {
		if (coordinates)
			return (int) ((imageHeight / 2) - (d * 2 * gridSpacing));
		else
			return (int) (y0 - (d * 2 * gridSpacing));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.reaction.rlm.pc.view.map.Map#builder()
	 */
	@Override
	public void builder() {
		float x;
		float y;
		double angle;

		for (DataShared ds : this.getCommunicationChannel().getShareds()) {

			if (TypeData.WALKING.ordinal() == ds.getTypeData()) {
				this.drawRobotPath(ds.getPose().getX() / REDUCTION, ds.getPose().getY() / REDUCTION);
			} else if (TypeData.OBSTACLE.ordinal() == ds.getTypeData()) {
				angle = Math.toRadians(ds.getPose().getHeading());
				x = (float) (Math.cos(angle) * ds.getData()) + ds.getPose().getX();
				y = (float) (Math.sin(angle) * ds.getData()) + ds.getPose().getY();
				this.drawObstacle(x / REDUCTION, y / REDUCTION);
			}
		}
		
		this.updateEnvironment();
		this.repaint();
		
	}
	
	private void updateEnvironment(){
		float xStart, yStart, xEnd, yEnd;
		
		this.simulator.setLines(this.simulator.getM().readDataEnvironment(this.getCommunicationChannel().getSharedsScanner()));
		this.setSimulator(new MapSimulator(this));

		osGraphics.setColor(Color.black);
		for (Line p : this.simulator.getM().readDataEnvironment(this.getCommunicationChannel().getSharedsScanner())) {
			xStart = p.getStartPoint().getX() / REDUCTION;
			yStart = p.getStartPoint().getY() / REDUCTION;
			xEnd = p.getEndPoint().getX() / REDUCTION;
			yEnd = p.getEndPoint().getY() / REDUCTION;
			
			osGraphics.drawLine(xpixel(xStart), ypixel(yStart, false), xpixel(xEnd), ypixel(yEnd, false));
		}
	}
	

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getHOrig()
	 */
	@Override
	public float getHOrig() {
		return this.hOrigSimulate;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#setHeading(float)
	 */
	@Override
	public void setHOrig(float heading) {
		this.hOrigSimulate = heading;
	}
	
	
	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getYOrig()
	 */
	@Override
	public double getYOrig() {
		return this.yOrigSimulate;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.pc.view.map.Map#getXOrig()
	 */
	@Override
	public double getXOrig() {
		return this.xOrigSimulate;
	}

	/**
	 * @return the xOrigReal
	 */
	public float getXOrigReal() {
		return this.xOrigReal;
	}

	/**
	 * @return the yOrigReal
	 */
	public float getYOrigReal() {
		return this.yOrigReal;
	}

	
	

}
