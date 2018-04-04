package org.reaction.rlm.comm.data;

public class Point {
	private float x;
	private float y;
	private TypeData type;

	public Point() {
		x = 0;
		y = 0;
	}

	public Point(float newX, float newY) {
		x = newX;
		y = newY;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float newX) {
		x = newX;
	}

	public void setY(float newY) {
		y = newY;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "X=" + this.x + " Y=" + this.y;
	}

	/**
	 * @return the type
	 */
	public TypeData getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TypeData type) {
		this.type = type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Point))
			return false;
		else if (((Point) obj).getX() == this.x && ((Point) obj).getY() == this.y)
			return true;
		else
			return false;
	}
}
