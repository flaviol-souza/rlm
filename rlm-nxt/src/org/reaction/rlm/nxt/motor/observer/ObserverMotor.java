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
package org.reaction.rlm.nxt.motor.observer;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

/**
 * @author Flavio Souza
 *
 */
public class ObserverMotor {

	private static final int ANGLE_LEFT = 90;
	private static final int ANGLE_RIGHT = -90;
	
	private static Boolean isRotateLeft;
	
	private NXTRegulatedMotor motor = Motor.B;;
	private TypeGearMotor lastTypeGearMotor;

	/**
	 * 
	 */
	public ObserverMotor() {
		this.lastTypeGearMotor = TypeGearMotor.ROTATE_FRONT;
		this.configureMotor();
	}
	
	private void configureMotor(){
		this.motor.setSpeed(200);
	}
	
	/**
	 * @param angle
	 */
	public void rotate(int angle) {
		this.motor.rotate(angle);
	}
	
	public void rotate(TypeGearMotor typeGear){
		if(TypeGearMotor.ROTATE_FRONT.equals(typeGear)){
			this.rotateFront();
		}else if(TypeGearMotor.ROTATE_LEFT.equals(typeGear)){
			this.rotateLeft();
		}else if(TypeGearMotor.ROTATE_RIGHT.equals(typeGear)){
			this.rotateRigth();
		}else if(TypeGearMotor.ROTATE_BACK.equals(typeGear)){
			this.rotateBack();
		}	
	}
	
	private void rotateFront(){
		if(TypeGearMotor.ROTATE_LEFT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_RIGHT);
		}else if(TypeGearMotor.ROTATE_RIGHT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_LEFT);
		}else if(TypeGearMotor.ROTATE_BACK.equals(this.lastTypeGearMotor)){
			if(this.isRotateLeft != null){
				// swiveled to the right
				if(this.isRotateLeft.equals(true)){
					this.motor.rotate(ANGLE_RIGHT);
					this.motor.rotate(ANGLE_RIGHT);
				}else {
					this.motor.rotate(ANGLE_LEFT);
					this.motor.rotate(ANGLE_LEFT);
				}
			}
		}
		
		this.lastTypeGearMotor = TypeGearMotor.ROTATE_FRONT;
	}
	
	private void rotateLeft(){
		if(TypeGearMotor.ROTATE_FRONT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_LEFT);
		}else if(TypeGearMotor.ROTATE_RIGHT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_LEFT);
			this.motor.rotate(ANGLE_LEFT);
		}else if(TypeGearMotor.ROTATE_BACK.equals(this.lastTypeGearMotor)){
			if(this.isRotateLeft != null){
				// swiveled to the right
				if(this.isRotateLeft.equals(true)){
					this.motor.rotate(ANGLE_LEFT);
				}else {
					this.motor.rotate(ANGLE_RIGHT);
					this.motor.rotate(ANGLE_RIGHT);
					this.motor.rotate(ANGLE_RIGHT);
				}
			}
		}	
		
		this.lastTypeGearMotor = TypeGearMotor.ROTATE_LEFT;
	}
	
	private void rotateRigth(){
		if(TypeGearMotor.ROTATE_FRONT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_RIGHT);
		}else if(TypeGearMotor.ROTATE_LEFT.equals(this.lastTypeGearMotor)){
			this.motor.rotate(ANGLE_RIGHT);
			this.motor.rotate(ANGLE_RIGHT);
		}else if(TypeGearMotor.ROTATE_BACK.equals(this.lastTypeGearMotor)){
			if(this.isRotateLeft != null){
				// swiveled to the right
				if(this.isRotateLeft.equals(true)){
					this.motor.rotate(ANGLE_LEFT);
					this.motor.rotate(ANGLE_LEFT);
					this.motor.rotate(ANGLE_LEFT);
				}else {
					this.motor.rotate(ANGLE_RIGHT);
				}
			}
		}	
		
		this.lastTypeGearMotor = TypeGearMotor.ROTATE_RIGHT;
	}
	
	private void rotateBack(){
		if(TypeGearMotor.ROTATE_FRONT.equals(this.lastTypeGearMotor)){
			this.isRotateLeft = new Boolean(false);
			this.motor.rotate(ANGLE_RIGHT);
			this.motor.rotate(ANGLE_RIGHT);
		}else if(TypeGearMotor.ROTATE_RIGHT.equals(this.lastTypeGearMotor)){
			this.isRotateLeft = new Boolean(false);
			this.motor.rotate(ANGLE_RIGHT);
		}else if(TypeGearMotor.ROTATE_LEFT.equals(this.lastTypeGearMotor)){
			this.isRotateLeft = new Boolean(true);
			this.motor.rotate(ANGLE_LEFT);
		}	
		
		this.lastTypeGearMotor = TypeGearMotor.ROTATE_BACK;
	}

	/**
	 * @return
	 */
	public boolean isMoving() {
		return this.motor.isMoving();
	}

	/**
	 * @return
	 */
	public int getTachoCount() {
		return this.motor.getTachoCount();
	}

	
}
