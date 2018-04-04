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

import java.util.ArrayList;
import java.util.List;

import org.reaction.rlm.comm.data.Line;
import org.reaction.rlm.comm.data.Point;
import org.reaction.rlm.pc.location.MCLEngine;

/**
 * @author Flavio Souza
 * 
 */
public class MapSimulator {

	private List<Line> lines;
	private MCLEngine m;

	/**
	 * 
	 */
	public MapSimulator(Map map) {
		this.createLines();
		this.m = new MCLEngine(this.lines, map);
	}

	private void createLines() {
		this.lines = new ArrayList<Line>();
		/*
		this.lines.add(new Line(new Point(-6, 2), new Point(-6, 12)));
		this.lines.add(new Line(new Point(-3, 2), new Point(-3, 4)));
		this.lines.add(new Line(new Point(-3, 4), new Point(1, 4)));
		this.lines.add(new Line(new Point(1, 2), new Point(1, 4)));
		
		this.lines.add(new Line(new Point(-6, 2), new Point(-3, 2)));
		this.lines.add(new Line(new Point(1, 2), new Point(4, 2)));
		this.lines.add(new Line(new Point(4, 2), new Point(4, 8)));
		
		this.lines.add(new Line(new Point(4, 8), new Point(-1, 8)));
		this.lines.add(new Line(new Point(-1, 8), new Point(-1, 12)));
		this.lines.add(new Line(new Point(-6, 12), new Point(-1, 12)));
		*/
		
		this.lines.add(new Line(new Point(-6, 2), new Point(-6, 12)));
		this.lines.add(new Line(new Point(-4, 2), new Point(-4, 3)));
		this.lines.add(new Line(new Point(-4, 3), new Point(-3, 3)));
		this.lines.add(new Line(new Point(-3, 2), new Point(-3, 3)));
		
		this.lines.add(new Line(new Point(-6, 2), new Point(-4, 2)));
		this.lines.add(new Line(new Point(-3, 2), new Point(6, 2)));
		this.lines.add(new Line(new Point(6, 2), new Point(6, 5)));
		
		this.lines.add(new Line(new Point(4, 9), new Point(4, 5)));
		this.lines.add(new Line(new Point(4, 9), new Point(5, 9)));
		this.lines.add(new Line(new Point(4, 10), new Point(5, 10)));
		this.lines.add(new Line(new Point(5, 10), new Point(5, 9)));
		this.lines.add(new Line(new Point(4, 10), new Point(4, 12)));
		
		this.lines.add(new Line(new Point(6, 5), new Point(4, 5)));
		this.lines.add(new Line(new Point(4, 12), new Point(-6, 12)));
	}

	/**
	 * @return the lines
	 */
	public List<Line> getLines() {
		return this.lines;
	}

	/**
	 * @param lines
	 *            the lines to set
	 */
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	/**
	 * @return the m
	 */
	public MCLEngine getM() {
		return this.m;
	}

	/**
	 * @param m
	 *            the m to set
	 */
	public void setM(MCLEngine m) {
		this.m = m;
	}

}
