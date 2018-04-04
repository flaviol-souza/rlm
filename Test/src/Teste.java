import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

/**
 * @author Flavio Souza
 * 
 */
public class Teste extends JPanel {

	static List<Point> points = new ArrayList<Point>();

	public static void main(String[] args) throws InterruptedException {
		points.add(new Point(10, 0));
		points.add(new Point(10, 5));
		points.add(new Point(134, 0));
		points.add(new Point(16, 6));
		points.add(new Point(120, 10));
		points.add(new Point(195, 0));
		points.add(new Point(201, 3));
		points.add(new Point(20, 2));
		points.add(new Point(244, 1));
		
		Teste t = new Teste();
		JFrame frame = new JFrame("Points");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(t);
	    frame.setSize(500, 500);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	    

	    for (Point p : points) {
	    	t.setLocation(p);
	    	Thread.sleep(2000);
		}
	}

	public void paint(Graphics g) {
		g.fillOval(25, 25, 10, 10);
	}
}
/*
 * extends JPanel { public void paint(Graphics g) { g.fillOval(25, 25, 120,
 * 120);
 * 
 * }
 * 
 * public static void main(String[] args) { JFrame frame = new JFrame();
 * frame.getContentPane().add(new Teste());
 * 
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setSize(200,
 * 200); frame.setVisible(true); } }
 */