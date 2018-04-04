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
package org.reaction.rlm.nxt.navigator.behavior.realtime;

import lejos.nxt.UltrasonicSensor;

import org.reaction.rlm.nxt.motor.observer.ObserverMotor;

/**
 * @author Flavio Souza
 * 
 */
public class SonarCollectorRealTime extends Thread {

	private int maxDistance;
	private int minDistance;
	private int maxAngle;
	private int minAngle;

	private ObserverMotor observerMotor;
	private UltrasonicSensor ultrasonicSensor;

	/**
	 * @param observerMotor
	 * @param ultrasonicSensor
	 */
	public SonarCollectorRealTime(ObserverMotor observerMotor, UltrasonicSensor ultrasonicSensor) {
		this.observerMotor = observerMotor;
		this.ultrasonicSensor = ultrasonicSensor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (observerMotor.isMoving()) {
			if (this.ultrasonicSensor.getDistance() > this.maxDistance) {
				this.maxDistance = this.ultrasonicSensor.getDistance();
				this.maxAngle = this.observerMotor.getTachoCount();
			}

			if (this.ultrasonicSensor.getDistance() < this.minDistance) {
				this.minDistance = this.ultrasonicSensor.getDistance();
				this.minAngle = this.observerMotor.getTachoCount();
			}
		}
	}

	/**
	 * @return the maxDistance
	 */
	public int getMaxDistance() {
		return maxDistance;
	}

	/**
	 * @param maxDistance
	 *            the maxDistance to set
	 */
	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	/**
	 * @return the minDistance
	 */
	public int getMinDistance() {
		return minDistance;
	}

	/**
	 * @param minDistance
	 *            the minDistance to set
	 */
	public void setMinDistance(int minDistance) {
		this.minDistance = minDistance;
	}

	/**
	 * @return the maxAngle
	 */
	public int getMaxAngle() {
		return maxAngle;
	}

	/**
	 * @param maxAngle
	 *            the maxAngle to set
	 */
	public void setMaxAngle(int maxAngle) {
		this.maxAngle = maxAngle;
	}

	/**
	 * @return the minAngle
	 */
	public int getMinAngle() {
		return minAngle;
	}

	/**
	 * @param minAngle
	 *            the minAngle to set
	 */
	public void setMinAngle(int minAngle) {
		this.minAngle = minAngle;
	}

}
