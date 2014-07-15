package fLink.busInterfaces.MPC5200;

import ch.ntb.inf.deep.runtime.mpc5200.IphyCoreMpc5200tiny;
import ch.ntb.inf.deep.unsafe.US;
import fLink.core.FLinkBusInterface;

public class localPlusBusInterface implements FLinkBusInterface, IphyCoreMpc5200tiny{
	private static final int CS3START = 0xf000001C;
	private static final int CS3STOP =  0xf0000020;
	private static final int CS3STARTADDRESS = 0xe0000000;
	private static final int CS3STOPADDRESSS =  0xe1FF0000;
	
	private int memLength;

	public localPlusBusInterface(int memoryLength){
		this.memLength = memoryLength;
		US.PUT4(CS3START, CS3STARTADDRESS>>16); //set CS3 start address
		US.PUT4(CS3STOP, CS3STOPADDRESSS>>16); 	//set CS3 end address
		US.PUT4(CS3CR, 0x0005FF00);	//configure CS3
		int ipbiReg = US.GET4(IPBICR);
		ipbiReg = ipbiReg | 0x00080000; //enable CS3
		US.PUT4(IPBICR, ipbiReg);
	}

	public int getMemoryLength() {
		return this.memLength;
	}

	public int read(int address) {
		return  US.GET4(CS3STARTADDRESS + address);
	}

	
	public void write(int address, int data) {	
		US.PUT4(CS3STARTADDRESS+address, data);
	}

}
