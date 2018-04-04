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

import javax.swing.JPanel;

import org.reaction.rlm.pc.comm.CommunicationChannelPC;

/**
 * @author Flavio Souza
 * 
 */
public abstract class Map extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4854165711445473627L;

	private CommunicationChannelPC communicationChannel;

	protected MapSimulator simulator;
	
	protected boolean excuteSimulator = false;
	
	public abstract void builder();
	
	/**
	 * @param x
	 * @param y
	 * @param d 
	 */
	public abstract void setPointSimulate(float x, float y, float h); 
	
	
	/**
	 * @param x
	 * @param y
	 */
	public abstract void setPointReal(float x, float y);

	/**
	 * @return
	 */
	public abstract boolean getIsPoint();

	/**
	 * @return
	 */
	public abstract Double[] getDistancesOrigin();

	public abstract float getHOrig();
	
	public abstract double getYOrig();
	
	public abstract double getXOrig();
	
	public abstract void setHOrig(float heading);
	
	/**
	 * @return the communicationChannel
	 */
	public CommunicationChannelPC getCommunicationChannel() {
		return communicationChannel;
	}

	/**
	 * @param communicationChannel
	 *            the communicationChannel to set
	 */
	public void setCommunicationChannel(CommunicationChannelPC communicationChannel) {
		this.communicationChannel = communicationChannel;
	}

	/**
	 * @return the simulator
	 */
	public MapSimulator getSimulator() {
		return simulator;
	}

	/**
	 * @param simulator the simulator to set
	 */
	public void setSimulator(MapSimulator simulator) {
		this.simulator = simulator;
	}

	/**
	 * @param excuteSimulator
	 */
	public void setExcuteSimulator(boolean excuteSimulator) {
		this.excuteSimulator = excuteSimulator;
	}

	/**
	 * @return
	 */
	public boolean getExcuteSimulator() {
		return this.excuteSimulator;
	}


}
