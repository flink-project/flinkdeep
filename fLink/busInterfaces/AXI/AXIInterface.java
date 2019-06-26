package fLink.busInterfaces.AXI;
import ch.ntb.inf.deep.unsafe.US;
import fLink.core.*;

public class AXIInterface extends FLinkBusInterface{

	int offset = 0x7aa00000;
	
	@Override
	public int getMemoryLength() {
		return 10;
	}

	@Override
	public int read(int address) {
		return US.GET4(offset + address);
	}

	@Override
	public void write(int address, int data) {
		US.PUT4(offset + address, data);
	}

	@Override
	public boolean hasInfoDev() {
		return true;
	}
	


}