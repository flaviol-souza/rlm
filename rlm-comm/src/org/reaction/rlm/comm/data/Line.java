package org.reaction.rlm.comm.data;


public class Line {
	private Point startPoint;
	private Point endPoint;
	
	Line(){
		startPoint = new Point();
		endPoint = new Point();
	}
	
	public Line(Point newStartPoint, Point newEndPoint){
		startPoint = newStartPoint;
		endPoint = newEndPoint;
	}
	
	Line(float x1, float y1, float x2, float y2){
		startPoint = new Point(x1,y1);
		endPoint = new Point(x2,y2);
	}
	
	public Point getStartPoint(){
		return startPoint;
	}
	
	public Point getEndPoint(){
		return endPoint;
	}
	
	public void setStartPoint(Point newStartPoint){
		startPoint = newStartPoint;
	}
	
	public void setStartPoint(float x, float y){
		startPoint = new Point(x,y);
	}

	public void setEndPoint(Point newEndPoint){
		endPoint = newEndPoint;
	}
	
	public void setEndPoint(float x, float y){
		endPoint = new Point(x,y);
	}
	
	public Point[] toArray(){
		return new Point[] {this.startPoint, this.endPoint};
	}
}
