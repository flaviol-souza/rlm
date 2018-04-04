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
package org.reaction.rlm.nxt.navigator;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

import org.reaction.rlm.nxt.comm.CommunicationChannelRobot;
import org.reaction.rlm.nxt.motor.MotorNxt;
import org.reaction.rlm.nxt.motor.observer.ObserverMotor;
import org.reaction.rlm.nxt.navigator.behavior.MCLBehavior;
import org.reaction.rlm.nxt.navigator.behavior.NearbyObstacleBehavior;
import org.reaction.rlm.nxt.navigator.behavior.WalkBehavior;

/**
 * @author Flavio Souza
 *
 */
public class ControlNavigator extends Thread {
	
	public static final double TRAVEL_DISTANCE = 1.0; //CM
	
	public static long begaviorIndex = NearbyObstacleBehavior.serialVersionUID;
	public static int angleScannerMCL = 0;
	
	private MotorNxt motorNxt;
	private TouchSensor touchSensor;
	private CommunicationChannelRobot comm;
	private UltrasonicSensor ultrasonicSensor;
	private ObserverMotor observerMotor;
	
	/**
	 * @param dataShared 
	 * 
	 */
	public ControlNavigator(CommunicationChannelRobot comm) {
		this.comm = comm;
		this.motorNxt = new MotorNxt();
		this.touchSensor = new TouchSensor(SensorPort.S2);
		this.ultrasonicSensor = new UltrasonicSensor(SensorPort.S1);
		this.observerMotor = new ObserverMotor();
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		// 
		/*
		Behavior b1 = new WalkBehavior(this.motorNxt, this.comm);
		Behavior b2 = new NearbyObstacleBehavior(this.motorNxt, this.ultrasonicSensor, this.comm, this.observerMotor);
		Behavior b3 = new CollisionBehavior(this.motorNxt, this.touchSensor, this.comm);
		
		Behavior behaviors[] = {b1, b2, b3};
		*/
		
		//Behavior b1 = new ScannerBehavior(this.comm, this.observerMotor, this.ultrasonicSensor);
		//Behavior b1 = new MCLBehavior(this.comm, this.observerMotor, this.ultrasonicSensor, this.motorNxt);
		Behavior b1 = new NearbyObstacleBehavior(this.motorNxt, this.ultrasonicSensor, this.comm, this.observerMotor);
		Behavior b2 = new MCLBehavior(this.comm, this.observerMotor, this.ultrasonicSensor, this.motorNxt);
		Behavior b3 = new WalkBehavior(this.motorNxt, this.comm);
		
		Behavior behaviors[] = {b1, b2, b3};
		
		Arbitrator arbitrator = new Arbitrator(behaviors);
		arbitrator.start();
	}
}
