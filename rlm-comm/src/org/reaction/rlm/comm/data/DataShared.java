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
package org.reaction.rlm.comm.data;

import lejos.robotics.navigation.Pose;

/**
 * @author Flavio Souza
 * 
 */
public class DataShared {

	private Pose pose;
	private int typeData;
	private float data;
	private int rotate;
	private int orientation;

	/**
	 * 
	 */
	public DataShared() {
		this.pose = new Pose();
	}

	/**
	 * @param pose
	 * @param type
	 */
	public DataShared(Pose pose, int type) {
		this.pose = pose;
		this.typeData = type;
	}

	/**
	 * @param pose
	 * @param type
	 * @param data
	 */
	public DataShared(Pose pose, int type, int data) {
		this.pose = pose;
		this.typeData = type;
		this.data = data;
	}

	/**
	 * @param type
	 * @param data2
	 * @param orientation
	 */
	public DataShared(int type, int data, int orientation) {
		this.orientation = orientation;
		this.typeData = type;
		this.data = data;
	}

	/**
	 * @return the pose
	 */
	public Pose getPose() {
		return pose;
	}

	/**
	 * @param pose
	 *            the pose to set
	 */
	public void setPose(Pose pose) {
		this.pose = pose;
	}

	/**
	 * @return the typeData
	 */
	public int getTypeData() {
		return typeData;
	}

	/**
	 * @param typeData
	 *            the typeData to set
	 */
	public void setTypeData(int typeData) {
		this.typeData = typeData;
	}

	/**
	 * @return the data
	 */
	public float getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(float data) {
		this.data = data;
	}

	/**
	 * @return the rotate
	 */
	public int getRotate() {
		return rotate;
	}

	/**
	 * @param rotate
	 *            the rotate to set
	 */
	public void setRotate(int rotate) {
		this.rotate = rotate;
	}

	/**
	 * @return the orientation
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String toString = "Type Data "+TypeData.values()[this.getTypeData()].name();
		
		if(TypeData.OBSTACLE.equals(TypeData.values()[this.getTypeData()])){
			toString += " X = " + this.getPose().getX() + " Y = " + this.getPose().getY() + " Heading = " + this.getPose().getHeading();
		}else if(TypeData.MCL.equals(TypeData.values()[this.getTypeData()])){
			toString += " Orientation = "+ TypeOrientation.values()[this.getOrientation()].name();
		}
		
		return toString += " Data = "+ this.getData();
	}

	
	
}
