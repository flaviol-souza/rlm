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
package org.reaction.rlm.pc.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.reaction.rlm.comm.CommunicationChannel;
import org.reaction.rlm.comm.CommunicationChannelGeneric;
import org.reaction.rlm.comm.data.DistanceScanner;
import org.reaction.rlm.pc.view.map.Map;

/**
 * @author Flavio Souza
 *
 */
public class MCLListaner implements ActionListener{
	
	private Map map;
	private CommunicationChannelGeneric comm;
	/**
	 * 
	 */
	public MCLListaner(Map map, CommunicationChannel comm) {
		this.map = map;
		this.comm = (CommunicationChannelGeneric) comm;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		DistanceScanner scanner = this.comm.getSharedsMCL().get(0);
		this.comm.getSharedsMCL().remove(0);
		
		//this.map.setPointSimulate(scanner.getX(), scanner.getY(), scanner.getHeading());
		this.map.setPointReal(scanner.getX(), scanner.getY());
		this.map.setHOrig(scanner.getHeading());
		
		Double distances[] = scanner.getDistances().toArray(new Double[scanner.getDistances().size()]);
		this.map.getSimulator().getM().startMCL(scanner.getDistance(), distances);
		this.map.repaint();
		
	}

}
