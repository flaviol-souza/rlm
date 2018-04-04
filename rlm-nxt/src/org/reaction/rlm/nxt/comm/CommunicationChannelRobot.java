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
package org.reaction.rlm.nxt.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.robotics.navigation.Pose;

import org.reaction.rlm.comm.CommunicationChannelGeneric;
import org.reaction.rlm.comm.data.DataShared;
import org.reaction.rlm.comm.data.DistanceScanner;
import org.reaction.rlm.comm.data.TypeData;
import org.reaction.rlm.nxt.motor.observer.TypeGearMotor;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannelRobot extends CommunicationChannelGeneric{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3489165594225270817L;

	private static final int LIMIT_HISTORY_DATA = 100;
	
	private static CommunicationChannelRobot channel;

	private boolean isSendingPermission = true;
	
	private NXTConnection connection;

	private List<DataShared> shareds;
	private List<DistanceScanner> scanners;

	public static CommunicationChannelRobot getInstance() {
		if (channel == null) {
			channel = new CommunicationChannelRobot();
		}

		return channel;
	}

	/**
	 * 
	 */
	private CommunicationChannelRobot() {
		this.setConnected(false);
		this.setSendingPermission(false);
		this.shareds = new ArrayList<DataShared>();
		this.scanners = new ArrayList<DistanceScanner>();
	}
	
	/**
	 * @throws IOException
	 * 
	 */
	public NXTConnection connectServer() throws IOException {
		System.out.println("Found on server");
		this.connection = Bluetooth.waitForConnection();
		System.out.println("Connected on server");
		this.setDataOut((DataOutputStream) connection.openDataOutputStream());
		this.setDataIn((DataInputStream) connection.openDataInputStream());
		
		this.getChannel().setConnected(true);
		this.start();
		
		return connection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		 while (true)readData();
	}
	
	
	public void readData(){
		int code = -1;
		try {
			code = this.getDataIn().readInt();
			System.out.println("code " + code);

			boolean haveSend = true;
			while (haveSend) {
				if(this.shareds.size() > 0){
					writeData(this.shareds.get(0));
					this.shareds.remove(this.shareds.get(0));
					haveSend = false;
				}
				if(this.scanners.size() > 0){
					writeData(this.scanners.get(0));
					this.scanners.remove(this.scanners.get(0));
					haveSend = false;
				}
			}
			
		} catch (IOException e) {
			System.out.println("Read exception " + e);
		}
		
	}
	
	public void writeData(DataShared dShared){
		try {
			
			this.getDataOut().writeInt(dShared.getTypeData());

			if(TypeData.OBSTACLE.ordinal() == dShared.getTypeData()){
				this.getDataOut().writeFloat(Float.valueOf(dShared.getPose().getX()));
				this.getDataOut().writeFloat(Float.valueOf(dShared.getPose().getY()));
				this.getDataOut().writeFloat(Float.valueOf(dShared.getPose().getHeading()));
				this.getDataOut().writeFloat(Float.valueOf(dShared.getData()));
			}
			
			this.getDataOut().flush();
		} catch (IOException e) {
		}
	}
	
	public void writeData(DistanceScanner scanner){
		try {
			
			this.getDataOut().writeInt(scanner.getType().ordinal());

			if(TypeData.MCL.equals(scanner.getType())){
				this.getDataOut().writeDouble(scanner.getDistance());
				this.getDataOut().writeFloat(Float.valueOf(scanner.getX()));
				this.getDataOut().writeFloat(Float.valueOf(scanner.getY()));
			} else if(TypeData.SCANNER.equals(scanner.getType())){
				this.getDataOut().writeFloat(Float.valueOf(scanner.getX()));
				this.getDataOut().writeFloat(Float.valueOf(scanner.getY()));
			}
			
			this.getDataOut().writeFloat(Float.valueOf(scanner.getHeading()));
			this.getDataOut().writeInt(scanner.getDistances().size());
			
			for (Double distance : scanner.getDistances()) {
				this.getDataOut().writeDouble(distance);
			}
			
			this.getDataOut().flush();
		} catch (IOException e) {
		}
	}


	/**
	 * @param dScanner
	 */
	public void addScanner(DistanceScanner dScanner) {
		this.scanners.add(dScanner);
	}
	
	/**
	 * @param ordinal
	 * @param position
	 */
	public void addPoint(int type, Pose pose){
		DataShared ds = new DataShared(pose, type);
		this.shareds.add(ds);
		
		//if(this.historyShared.size() >= LIMIT_HISTORY_DATA)
			//this.historyShared.remove(0);
		
		//this.historyShared.add(ds);
	}
	
	/**
	 * @param type
	 * @param pose
	 * @param data
	 */
	public void addPoint(int type, Pose pose, int data) {
		DataShared ds = new DataShared(pose, type, data);
		this.shareds.add(ds);
		
		//if(this.historyShared.size() >= LIMIT_HISTORY_DATA)
			//this.historyShared.remove(0);
		
		//this.historyShared.add(ds);
	}
	
	/**
	 * @param type
	 * @param data
	 * @param orientation
	 */
	public void addPoint(int type, int data, int orientation) {
		DataShared ds = new DataShared(type, data, orientation);
		this.shareds.add(ds);
	}
	
	/**
	 * @param ordinal
	 * @param position
	 * @param data
	 * @param rotateBack
	 */
	public void addPoint(int ordinal, Pose position, int data, TypeGearMotor rotate) {
		DataShared ds = new DataShared(position, ordinal, data);
		ds.setRotate(rotate.ordinal());
		this.shareds.add(ds);		
	}
	
	/**
	 * @return the channel
	 */
	public CommunicationChannelRobot getChannel() {
		return channel;
	}

	/**
	 * @return the isSendingPermission
	 */
	public boolean isSendingPermission() {
		return isSendingPermission;
	}

	/**
	 * @param isSendingPermission the isSendingPermission to set
	 */
	public void setSendingPermission(boolean isSendingPermission) {
		this.isSendingPermission = isSendingPermission;
		
	}

}
