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
package org.reaction.rlm.nxt.navigator.behavior;

import java.io.Serializable;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Behavior;

import org.reaction.rlm.nxt.comm.CommunicationChannelRobot;
import org.reaction.rlm.nxt.motor.MotorNxt;
import org.reaction.rlm.nxt.motor.observer.ObserverMotor;
import org.reaction.rlm.nxt.navigator.ControlNavigator;
import org.reaction.rlm.nxt.navigator.behavior.realtime.ObserverMotorRealTime;
import org.reaction.rlm.nxt.navigator.behavior.realtime.SonarCollectorRealTime;

/**
 * @author Flavio Souza
 *
 */
public class NearbyObstacleBehavior implements Behavior, Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = -1070527051270063062L;
	
	private boolean mclCollectior;
	private MotorNxt motorNxt;
	private ObserverMotor observerMotor;
	private CommunicationChannelRobot comm;
	private UltrasonicSensor ultrasonicSensor;
	
	/**
	 * @param observerMotor 
	 * @param motorNxt
	 * @param ultrasonicSensor
	 * @param comm
	 */
	public NearbyObstacleBehavior(MotorNxt motorNxt, UltrasonicSensor ultrasonicSensor, CommunicationChannelRobot comm, ObserverMotor observerMotor) {
		this.comm = comm;
		this.motorNxt = motorNxt;
		this.mclCollectior = true;
		this.observerMotor = observerMotor;
		this.ultrasonicSensor = ultrasonicSensor;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#takeControl()
	 */
	@Override
	public boolean takeControl() {
		//return this.ultrasonicSensor.getDistance() <= SensorUtil.ULTRA_DIST_MIN;
		return ControlNavigator.begaviorIndex == NearbyObstacleBehavior.serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#action()
	 */
	@Override
	public void action() {
		this.motorNxt.stop();
		//this.comm.addPoint(TypeData.OBSTACLE.ordinal(), this.motorNxt.getPosition(), this.ultrasonicSensor.getDistance());
		
		ObserverMotorRealTime observerMotorMoving = new ObserverMotorRealTime(observerMotor);
		SonarCollectorRealTime sonarCollector = new SonarCollectorRealTime(observerMotor, ultrasonicSensor);
		
		//this.collectorDistanceToMCL(TypeOrientation.FRONT);
		
		observerMotorMoving.zeroDegree();
		
		//this.collectorDistanceToMCL(TypeOrientation.RIGHT);
		
		observerMotorMoving.start();
		sonarCollector.start();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			
		}

		//this.collectorDistanceToMCL(TypeOrientation.LEFT);
		
		observerMotorMoving.zeroDegree();
		//this.motorNxt.backward();
		
		try {
			Thread.sleep(450);
		} catch (InterruptedException e) {
			
		}
		
		this.motorNxt.stop();
		
		System.out.println(sonarCollector.getMaxAngle());
		
		this.motorNxt.rotate(-1 * sonarCollector.getMaxAngle());
		
		ControlNavigator.angleScannerMCL = sonarCollector.getMaxAngle(); 
		ControlNavigator.begaviorIndex = MCLBehavior.serialVersionUID;
	}

	/* (non-Javadoc)
	 * @see lejos.robotics.subsumption.Behavior#suppress()
	 */
	@Override
	public void suppress() {
		this.motorNxt.stop();		
	}
	

}
