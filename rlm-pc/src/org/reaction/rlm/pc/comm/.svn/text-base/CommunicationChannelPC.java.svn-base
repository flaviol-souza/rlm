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
package org.reaction.rlm.pc.comm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;
import lejos.robotics.navigation.Pose;

import org.reaction.rlm.comm.CommunicationChannelGeneric;
import org.reaction.rlm.comm.data.DataShared;
import org.reaction.rlm.comm.data.DistanceScanner;
import org.reaction.rlm.comm.data.TypeData;
import org.reaction.rlm.comm.data.TypeOrientation;
import org.reaction.rlm.pc.listener.DataListener;
import org.reaction.rlm.pc.listener.MCLListaner;
import org.reaction.rlm.pc.view.map.Map;

/**
 * @author Flavio Souza
 * 
 */
public class CommunicationChannelPC extends CommunicationChannelGeneric {
	/**
	 * 
	 */
	private static final long serialVersionUID = -287339477038043637L;

	private static CommunicationChannelPC channel;

	private DataListener dataListener;
	
	private MCLListaner mclListaner;
	
	private NXTConnector conn;

	public static CommunicationChannelPC getInstance(Map map) {
		if (channel == null) {
			channel = new CommunicationChannelPC(map);
		}

		return channel;
	}
	
	/**
	 * 
	 */
	private CommunicationChannelPC(Map map) {
		this.mclListaner = new MCLListaner(map, this);
		this.dataListener = new DataListener(map);
	}
	
	
	/**
	 * @throws IOException
	 * 
	 */
	public NXTConnector connectNXT() throws IOException {
		this.conn = new NXTConnector();
		
		this.conn.addLogListener(new NXTCommLogListener(){
				public void logEvent(String message) {
					System.out.println("BTSend Log.listener: "+message);
				}
	
				public void logEvent(Throwable throwable) {
					System.out.println("BTSend Log.listener - stack trace: ");
					 throwable.printStackTrace();
				}
			} 
		);
		// Connect to any NXT over Bluetooth
		boolean connected = this.conn.connectTo(NAME_NXT, ADDRESS_NXT, NXTCommFactory.BLUETOOTH);
	
		if (!connected) {
			System.err.println("Failed to connect to any NXT");
			System.exit(1);
		}
		
		this.setDataOut(new DataOutputStream(this.conn.getOutputStream()));
		this.setDataIn(new DataInputStream(this.conn.getInputStream()));
		
		CommunicationChannelPC.getChannel().setConnected(true);

		this.start();

		return this.conn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (CommunicationChannelPC.getChannel().isConnected()) {
			this.readData();
		}
	}

	
	public void readData(){
		System.out.println("reading ");
		DistanceScanner dScanner;
		
		int t = -1;
		int o = 0;
		int q = 0;
		float x = 0;
		float y = 0;
		float h = 0;
		float d = 0;
		
		try {
			this.writeData(1);
			t = this.getDataIn().readInt();
			
			if(TypeData.OBSTACLE.ordinal() == t){
				x = this.getDataIn().readFloat();
				y = this.getDataIn().readFloat();
				h = this.getDataIn().readFloat();
				d = this.getDataIn().readFloat();
				this.addDataShared(t, x, y, h, d);
				
			}else if(TypeData.MCL.ordinal() == t || TypeData.SCANNER.ordinal() == t){
				dScanner = new DistanceScanner();
				
				if(TypeData.MCL.ordinal() == t){
					dScanner.setType(TypeData.MCL); //this.getDataOut().writeInt(scanner.getType().ordinal());
					dScanner.setDistance(this.getDataIn().readDouble()); //this.getDataOut().writeFloat(Float.valueOf(scanner.getY()));
					dScanner.setX(this.getDataIn().readFloat()); //this.getDataOut().writeFloat(Float.valueOf(scanner.getX()));
					dScanner.setY(this.getDataIn().readFloat()); //this.getDataOut().writeFloat(Float.valueOf(scanner.getY()));
				}else{
					dScanner.setType(TypeData.SCANNER); //this.getDataOut().writeInt(scanner.getType().ordinal());
					dScanner.setX(this.getDataIn().readFloat()); //this.getDataOut().writeFloat(Float.valueOf(scanner.getX()));
					dScanner.setY(this.getDataIn().readFloat()); //this.getDataOut().writeFloat(Float.valueOf(scanner.getY()));
				}
				
				dScanner.setHeading(this.getDataIn().readFloat()); //this.getDataOut().writeDouble(Double.valueOf(scanner.getHeading()));

				q = this.getDataIn().readInt();//this.getDataOut().writeInt(scanner.getDistances().size());
				
				if(q > 0){
					Double[] distances = new Double[q];
					
					for (int i = 0; i < q; i++) {
						distances[i] = this.getDataIn().readDouble();
					}
					dScanner.setDistances(Arrays.asList(distances));
					this.addScanner(dScanner);
				}
				
			}else{
				o = this.getDataIn().readInt();
				d = this.getDataIn().readFloat();
				this.addDataShared(t, o, d);
			}
			
			Thread.sleep(50);
		} catch (IOException e) {
			writeData(0);
			System.out.println("error");
		} catch (InterruptedException e) {
			writeData(0);
			System.out.println("error");
		}
	}
	
	/**
	 * @param dScanner
	 */
	private void addScanner(DistanceScanner dScanner) {
		if(dScanner.getType().equals(TypeData.SCANNER)){
			this.getSharedsScanner().add(dScanner);
			this.dataListener.actionPerformed(null);
		}else{
			this.getSharedsMCL().add(dScanner);
			this.mclListaner.actionPerformed(null);
		}
	}

	/**
	 * @param t
	 * @param x
	 * @param y
	 * @param h
	 * @param d
	 */
	private void addDataShared(int t, float x, float y, float h, float d) {
		DataShared dShared = new DataShared();
		
		dShared.setTypeData(TypeData.values()[t].ordinal());
		dShared.setPose(new Pose(x, y, h));
		dShared.setData(d);
		
		this.addDataShared(dShared);		
	}

	/**
	 * @param t
	 * @param x
	 * @param y
	 * @param h
	 */
	public void addDataShared(int t, int o, float d) {
		DataShared dShared = new DataShared();
		
		dShared.setTypeData(TypeData.values()[t].ordinal());
		dShared.setOrientation(TypeOrientation.values()[o].ordinal());
		dShared.setData(d);
		
		this.addDataShared(dShared);
	}

	private void addDataShared(DataShared dShared){
		System.out.println(dShared);
		this.getShareds().add(dShared);
		this.dataListener.actionPerformed(null);
	}
	
	private void writeData(int code){
		 try {
			this.getDataOut().writeInt(code);
			this.getDataOut().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the channel
	 */
	public static CommunicationChannelPC getChannel() {
		return channel;
	}

	/* (non-Javadoc)
	 * @see org.reaction.rlm.comm.CommunicationChannel#writeData(org.reaction.rlm.comm.data.DataShared)
	 */
	@Override
	public void writeData(DataShared dShared) {
		// TODO Auto-generated method stub
		
	}

}
