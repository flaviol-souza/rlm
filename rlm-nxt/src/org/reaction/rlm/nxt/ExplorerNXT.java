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

package org.reaction.rlm.nxt;

import java.io.IOException;

import lejos.nxt.Button;

import org.reaction.rlm.nxt.comm.CommunicationChannelRobot;
import org.reaction.rlm.nxt.navigator.ControlNavigator;

/**
 * @author Flavio Souza
 * 
 */
public class ExplorerNXT implements Runnable {

	private CommunicationChannelRobot comm;
	private ControlNavigator controlNavigator;

	public static void main(String[] args) {
		ExplorerNXT nxt = new ExplorerNXT();
		nxt.run();
	}

	/**
	 * 
	 */
	public ExplorerNXT() {
		//this.comm = CommunicationChannel.getInstance();
		this.comm = CommunicationChannelRobot.getInstance();
		this.controlNavigator = new ControlNavigator(comm);
	}

	@Override
	public void run() {

			// create communication with server
			try {
				this.comm.connectServer();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.controlNavigator.start();

			while(true){
				if (Button.ESCAPE.isPressed()) {
					System.exit(0);
				}
			}
	}

}
