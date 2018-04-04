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
package org.reaction.rlm.pc.location;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.reaction.rlm.comm.data.Line;
import org.reaction.rlm.comm.data.Point;

/**
 * @author Flavio Souza
 * 
 */
public class MCLMath {

	public static double slopeRadius(double radius) {
		return Math.tan(radius);
	}

	public static double slopeDegree(double degree) {
		return slopeRadius(Math.toRadians(degree));
	}

	public static Point equationLineX(float x0, float y0, double slope, float x) {
		return equationLineX(new Point(x0, y0), slope, x);
	}

	public static Point equationLineX(Point p, double slope, float x) {
		Point pointLine = new Point();
		pointLine.setX(x);

		slope = slopeDegree(slope);

		// y = mx + (y0 - mx0)
		//float y = (float) ((slope * x) + (p.getY() - (slope * p.getX())));
		// y = m (x – x0) + y0 
		float y = (float) (slope * (x - p.getX())) + p.getY();
		
		pointLine.setY(y);

		return pointLine;
	}

	public static Point equationLineY(float x0, float y0, double slope, float y) {
		return equationLineY(new Point(x0, y0), slope, y);
	}
	
	public static int logicalOperatorSin(double degree){
		int operator = 1;
		
		if(degree > 180 && degree < 360){
			operator *= -1;
		}
		
		return operator;
	}
	
	public static int logicalOperatorCos(double degree){
		int operator = 1;
		
		if(degree < 90 && degree < 240){
			operator *= -1;
		}
		
		return operator;
	}

	public static Point equationLineY(Point p, double slope, float y) {
		Point pointLine = new Point();
		pointLine.setY(y);

		slope = slopeDegree(slope);

		// y = mx + (y0 - mx0)
		//float x = (float) ((p.getY() - (slope * p.getX()) - y) / slope);
		// x = ((y – y0) / m) + x0  
		float x = (float) ((y - p.getY()) / slope) + p.getX();
		
		pointLine.setX(x);

		return pointLine;
	}

	public static List<Point> calculatePointLineAtX(Point p, double slope,
			float x) {
		List<Point> points = new ArrayList<Point>();

		for (int i = 0; i <= x - p.getX(); i++) {
			points.add(equationLineX(p, slope, i));
		}
		return points;
	}

	public static List<Point> calculatePointLineAtY(Point p, double slope,
			float y) {
		List<Point> points = new ArrayList<Point>();

		for (int i = 0; i <= y - p.getY(); i++) {
			points.add(new Point(p.getX() + i, p.getY() + i));
		}

		return points;
	}

	public static boolean findPointInLine(Line l, Point p) {
		double find = distancePointInLine(l, p);

		if (find <= 0.09)
			return true;
		else
			return false;
	}

	public static double distanceBetweenTwoPoints(Point p1, Point p2) {
		return Point2D.distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	public static double distancePointInLine(Line l, Point p) {
		return Line2D.ptSegDist(l.getStartPoint().getY(), l.getStartPoint().getY(), l.getEndPoint().getX(), l.getEndPoint().getY(), p.getX(), p.getY());
	}

	public static Point intersectionPointOfTwoLine(Point pStart1, Point pEnd1, Point pStart2, Point pEnd2) {
		return intersectionPointOfTwoLine(pStart1.getX(), pStart1.getY(), pEnd1.getX(), pEnd1.getY(), pStart2.getX(), pStart2.getY(), pEnd2.getX(), pEnd2.getY());
	}
	
	public static Point intersectionPointOfTwoLine(float x1, float y1, float x2, float y2, float x3, float y3, float x4, float y4) {
		/*
		double dyline1, dxline1;
	    double dyline2, dxline2, e, f;
	    
	    if ((x1==x3) && (y1==y3))
	    {
	      return (new Point( (int) x1, (int) y1));
	    }
	    if ((x1==x4) && (y1==y4))
	    {
	      return (new Point( (int) x1, (int) y1));
	    }
	    if ((x2==x3) && (y2==y3))
	    {
	      return (new Point( (int) x2, (int) y2));
	    }
	    if ((x2==x4) && (y2==y4))
	    {
	      return (new Point( (int) x2, (int) y2));
	    }

	    dyline1 = -( y2 - y1 );
	    dxline1 = x2 - x1;

	    dyline2 = -( y4 - y3 );
	    dxline2 = x4 - x3;

	    e = -(dyline1 * x1) - (dxline1 * y1);
	    f = -(dyline2 * x3) - (dxline2 * y3);


	    if( (dyline1 * dxline2 - dyline2 * dxline1) == 0 )
	    	return null;
	    
	    return (new Point((int) (-(e * dxline2 - dxline1 * f)/(dyline1 * dxline2 - dyline2 * dxline1)), 
	    				(int) (-(dyline1 * f - dyline2 * e)/(dyline1 * dxline2 - dyline2 * dxline1))));
	      */
		double d = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
		if (d == 0)
			return null;
		
		float xi = (float) (((x3 - x4) * (x1 * y2 - y1 * x2) - (x1 - x2) * (x3 * y4 - y3 * x4)) / d);
		float yi = (float) (((y3 - y4) * (x1 * y2 - y1 * x2) - (y1 - y2) * (x3 * y4 - y3 * x4)) / d);

		return new Point(xi, yi);
	}
}
