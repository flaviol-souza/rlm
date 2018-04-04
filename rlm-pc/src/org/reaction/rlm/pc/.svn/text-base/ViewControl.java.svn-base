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
package org.reaction.rlm.pc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.reaction.rlm.pc.comm.CommunicationChannelPC;
import org.reaction.rlm.pc.util.IconsUtil;
import org.reaction.rlm.pc.view.map.Map;
import org.reaction.rlm.pc.view.map.MapScreen;

/**
 * @author Flavio Souza
 * 
 */
public class ViewControl extends JPanel implements Runnable, IconsUtil, ActionListener{
	
	private static final long serialVersionUID = -8034828485199716108L;
	
	private final static int PANEL_MARGIN = 15;
	private final static int LOGO_MARGIN_BOTTOM = 15;
	
	private final static Color DEFAULT_PANEL_BACKGROUND_COLOR = new Color(197, 210, 220);
	private final static Color LEFT_PANEL_BACKGROUND_COLOR = DEFAULT_PANEL_BACKGROUND_COLOR;
	private final static Color BOTTOM_PANEL_BACKGROUND_COLOR = DEFAULT_PANEL_BACKGROUND_COLOR;

	private CommunicationChannelPC comm;
	
	private Panel controlPanel;
	//private MapPanel map;
	private Map map;
	private JTextField txtX;
	private JTextField txtY;
	private JTextField txtDistance;
	private JTextField txtHeading;
	private JButton btnSimuleMCL;
	private JButton btnConnectNXT;
	
	//MENU
	private JMenuItem exitButton;
	private JMenuItem contactButton;
	private JMenuItem workButton;
	private JMenuItem licenseButton;
	
	//Labels Returns
	private Label lblAboveAverageReturn;
	private Label lblTotalTimeReturn;
	private Label lblCycleTimeReturn;
	private Label lblamountCyclesReturn;
	
	private JSlider sldMapScale;
	private JSlider sldMapRotate;
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				create();
			}
		});
	}
	
	private static void create() {
		JFrame mainFrame = new JFrame(APPLICATION_NAME);
		
		ViewControl viewControl = new ViewControl(mainFrame);
		
		mainFrame.add(viewControl);

		mainFrame.setJMenuBar(viewControl.createMenu());
		mainFrame.setBackground(Color.WHITE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// start full screen
		mainFrame.setUndecorated(true);
		mainFrame.pack();
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		mainFrame.setVisible(true);
	}

	public ViewControl(JFrame frame) {
		this.setLayout(new BorderLayout());
		this.add(getContentPanel(), BorderLayout.CENTER);
		this.comm = CommunicationChannelPC.getInstance(this.map);
		this.map.setCommunicationChannel(this.comm);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	private JMenuBar createMenu(){
		JMenuBar menuBar = new JMenuBar();
		
		// Menus
		JMenu fileMenu = new JMenu("File");
		exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(this);
		fileMenu.add(exitButton);

		// Menus
		JMenu aboutMenu = new JMenu("Sobre");
		contactButton = new JMenuItem("Contatos");
		contactButton.addActionListener(this);
		aboutMenu.add(contactButton);
		workButton = new JMenuItem("Projeto");
		workButton.addActionListener(this);
		aboutMenu.add(workButton);
		licenseButton = new JMenuItem("Licença");
		licenseButton.addActionListener(this);
		aboutMenu.add(licenseButton);
		
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		
		return menuBar;
	}
	
	private Panel getContentPanel() {
		Panel mainPanel = new Panel();

		controlPanel = createControlPanel();
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(this.createLeftPanel(), BorderLayout.WEST);
		mainPanel.add(this.createRightPanel(), BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	private JPanel createLeftPanel(){
		JPanel leftPanel = new JPanel();
		
		leftPanel.setBorder(BorderFactory.createEmptyBorder(PANEL_MARGIN, PANEL_MARGIN, PANEL_MARGIN, 0));
		leftPanel.setBackground(LEFT_PANEL_BACKGROUND_COLOR);
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		leftPanel.add(controlPanel);
		
		return leftPanel;
	}
	
	private JPanel createRigthPanel(){
		JPanel leftPanel = new JPanel();
		
		leftPanel.setBorder(BorderFactory.createEmptyBorder(PANEL_MARGIN, PANEL_MARGIN, PANEL_MARGIN, 0));
		leftPanel.setBackground(LEFT_PANEL_BACKGROUND_COLOR);
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		return leftPanel;
	}
	
	private Panel createControlPanel(){
		controlPanel = new Panel();
		
		// Fonts
		Font fontSectionHeader = new Font("Arial", Font.BOLD, 14);
		Font fontLabelHeader = new Font("Arial", Font.BOLD, 12);
		
		JPanel logoPanel = new JPanel(new BorderLayout());
		JLabel lblLogo = new JLabel("", RLM_LOGO_ICON_16_X_16_ICON, SwingConstants.CENTER);

		logoPanel.setBackground(LEFT_PANEL_BACKGROUND_COLOR);
		logoPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, LOGO_MARGIN_BOTTOM, 0));
		logoPanel.add(lblLogo, BorderLayout.CENTER);
		
		// Connect
		Label lblConnectionHeader = new Label("Dados de simulação");
		lblConnectionHeader.setFont(fontSectionHeader);

		Label lblX = new Label("X:");
		lblX.setFont(fontLabelHeader);

		Label lblY = new Label("Y:");
		lblY.setFont(fontLabelHeader);
		
		Label lblDistance = new Label("Distance:");
		lblDistance.setFont(fontLabelHeader);
		
		Label lblHeading = new Label("Heading:");
		lblHeading.setFont(fontLabelHeader);
		
		Label lblAboveAverage = new Label("Above average:");
		lblAboveAverage.setFont(fontLabelHeader);
		
		Label lblTotalTime = new Label("Total time:");
		lblTotalTime.setFont(fontLabelHeader);
		
		Label lblCycleTime = new Label("Cycle time:");
		lblCycleTime.setFont(fontLabelHeader);
		
		Label lblamountCycles = new Label("Amount of cycles:");
		lblamountCycles.setFont(fontLabelHeader);
		
		Panel pnlConnection = new Panel(new GridBagLayout());

		txtX = new JTextField(3);
		txtY = new JTextField(3);
		txtDistance = new JTextField(3);
		txtHeading = new JTextField(3);
		btnSimuleMCL = new JButton("Start Simule Monte Carlo Localization");
		btnSimuleMCL.addActionListener(this);
		btnSimuleMCL.setEnabled(true);
		
		btnConnectNXT = new JButton("Connect NXT");
		btnConnectNXT.addActionListener(this);
		btnConnectNXT.setEnabled(true);

		GridBagConstraints CBC = new GridBagConstraints();
		CBC.fill = GridBagConstraints.HORIZONTAL;
		CBC.weightx = 0.0;
		CBC.weighty = 0.0;

		CBC.gridx = 0;
		CBC.gridy = 0;
		pnlConnection.add(lblX, CBC);
		CBC.gridx = 1;
		CBC.gridy = 0;
		pnlConnection.add(txtX, CBC);
		CBC.gridx = 0;
		CBC.gridy = 1;
		pnlConnection.add(lblY, CBC);
		CBC.gridx = 1;
		CBC.gridy = 1;
		pnlConnection.add(txtY, CBC);
		CBC.gridx = 0;
		CBC.gridy = 2;
		pnlConnection.add(lblDistance, CBC);
		CBC.gridx = 1;
		CBC.gridy = 2;
		pnlConnection.add(txtDistance, CBC);
		CBC.gridx = 0;
		CBC.gridy = 3;
		pnlConnection.add(lblHeading, CBC);
		CBC.gridx = 1;
		CBC.gridy = 3;
		pnlConnection.add(txtHeading, CBC);
		CBC.gridx = 0;
		CBC.gridy = 4;		
		CBC.gridwidth = 2;
		pnlConnection.add(btnSimuleMCL, CBC);
		CBC.gridx = 0;
		CBC.gridy = 5;		
		CBC.gridwidth = 2;
		pnlConnection.add(btnConnectNXT, CBC);
		
		/*
		CBC.gridx = 0;
		CBC.gridy = 5;
		pnlConnection.add(lblAboveAverage, CBC);
		CBC.gridx = 1;
		CBC.gridy = 5;
		pnlConnection.add(lblAboveAverageReturn, CBC);
		CBC.gridx = 0;
		CBC.gridy = 6;
		pnlConnection.add(lblTotalTime, CBC);
		CBC.gridx = 1;
		CBC.gridy = 6;
		pnlConnection.add(lblTotalTimeReturn, CBC);
		CBC.gridx = 0;
		CBC.gridy = 7;
		pnlConnection.add(lblCycleTime, CBC);
		CBC.gridx = 1;
		CBC.gridy = 7;
		pnlConnection.add(lblCycleTimeReturn, CBC);
		CBC.gridx = 0;
		CBC.gridy = 8;
		pnlConnection.add(lblamountCycles, CBC);
		CBC.gridx = 1;
		CBC.gridy = 8;
		pnlConnection.add(lblamountCyclesReturn, CBC);
		*/
		sldMapScale = new JSlider(SwingConstants.HORIZONTAL, 1, 150, 35);
		sldMapScale.setMajorTickSpacing(10);
		sldMapScale.setMinorTickSpacing(5);
		sldMapScale.setPaintTicks(true);
		sldMapScale.setPaintLabels(true);
		
		sldMapRotate = new JSlider(SwingConstants.HORIZONTAL, 0, 360, 0);
		sldMapRotate.setMajorTickSpacing(36);
		sldMapRotate.setMinorTickSpacing(18);
		sldMapRotate.setPaintTicks(true);
		sldMapRotate.setPaintLabels(true);
		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		controlPanel.add(logoPanel);

		controlPanel.add(lblConnectionHeader);
		controlPanel.add(pnlConnection);

		return controlPanel;
	}
	
	private JPanel createRightPanel(){
		JPanel rightPanel = new JPanel();
		
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(this.createHeadPanel(), BorderLayout.NORTH);
		rightPanel.add(this.createMapPanel(), BorderLayout.CENTER);
		rightPanel.add(this.createRigthPanel(), BorderLayout.EAST);
		rightPanel.add(this.createBottonPanel(), BorderLayout.SOUTH);
		
		return rightPanel;
	}
	
	private JPanel createMapPanel(){
		JPanel mapPanel = new JPanel();
		
		map = new MapScreen();
		map.setExcuteSimulator(true);
		
		mapPanel.setLayout(new BorderLayout());
		mapPanel.add(map , BorderLayout.CENTER);

		return mapPanel;
	}
	
	private JPanel createBottonPanel(){
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, PANEL_MARGIN, PANEL_MARGIN, PANEL_MARGIN));
		bottomPanel.setBackground(BOTTOM_PANEL_BACKGROUND_COLOR);
		bottomPanel.setLayout(new BorderLayout());
		Label lblMapScalesHeader = new Label("TCC-IC: FLAVIO LUIZ S. SOUZA --- 2012");
		lblMapScalesHeader.setFont(new Font("Arial", Font.BOLD, 12));
		bottomPanel.add(lblMapScalesHeader, BorderLayout.CENTER);
		
		return bottomPanel;
	}
	
	private JPanel createHeadPanel(){
		JPanel bottomPanel = new JPanel();
		
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, PANEL_MARGIN, PANEL_MARGIN, PANEL_MARGIN));
		bottomPanel.setBackground(BOTTOM_PANEL_BACKGROUND_COLOR);
		bottomPanel.setLayout(new BorderLayout());
		Label lblMapScalesHeader = new Label("");
		lblMapScalesHeader.setFont(new Font("Arial", Font.BOLD, 12));
		bottomPanel.add(lblMapScalesHeader, BorderLayout.CENTER);
		
		return bottomPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == exitButton) {
			System.exit(0);
		}else if (event.getSource() == contactButton){
			this.showPane("title contact","contact text");
		}else if(event.getSource() == workButton){
			this.showPane("title work","title text");
		}else if(event.getSource() == licenseButton){
			this.showPane("text license","title license");
		}else if(event.getSource() == this.btnConnectNXT){
			this.map.setExcuteSimulator(false);
			this.map.setPointSimulate(0, 6, 0);
			Thread t = new Thread(this);
			t.start();
			this.map.repaint();
		}else if(event.getSource() == this.btnSimuleMCL){

			this.map.setExcuteSimulator(true);
			
			if(!this.map.getIsPoint()){
				this.map.setPointSimulate(Float.valueOf(this.txtX.getText()), Float.valueOf(this.txtY.getText()), Float.valueOf(this.txtHeading.getText()));
			}
			
			Double distances[] = this.map.getDistancesOrigin();
			this.map.getSimulator().getM().startMCL(Double.valueOf(txtDistance.getText()), distances);
		}
			
	}
	
	private void showPane(String title, String text){
		JOptionPane.showMessageDialog(null, text, title,JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void run() {
		try {
			if(!this.map.getExcuteSimulator())
				this.comm.connectNXT();
		} catch (IOException e) {
			this.showPane("Error","Failed to connect to any NXT");
		}
	}

}
