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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Flavio Souza
 * 
 */
public class DistanceScanner extends Particle {

	public static final int RESOLUTION_SCANNER = 15;
	
	private List<Double> distances;
	private int degreeScanner;
	private double distance;

	/**
	 * 
	 */
	public DistanceScanner() {
		this.distances = new ArrayList<Double>();
	}

	public DistanceScanner(int degreeScanner) {
		this();
		this.degreeScanner = degreeScanner;
	}

	/**
	 * @return the degreeScanner
	 */
	public int getDegreeScanner() {
		return degreeScanner;
	}

	/**
	 * @param degreeScanner
	 *            the degreeScanner to set
	 */
	public void setDegreeScanner(int degreeScanner) {
		this.degreeScanner = degreeScanner;
	}

	/**
	 * @return the distances
	 */
	public List<Double> getDistances() {
		return distances;
	}

	/**
	 * @param distances
	 *            the distances to set
	 */
	public void setDistances(List<Double> distances) {
		this.distances = distances;
	}

	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String toString = "Degree Scanner = " + this.degreeScanner;

		for (int i = 0; i < 360 / this.degreeScanner; i++) {
			toString += "Dist. in " + (this.degreeScanner * (i + 1)) + " = " + this.distances.get(i);
		}

		return toString;
	}

}
