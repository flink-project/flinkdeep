package fLink.core;

public class FLinkSubDevice implements FLinkDefinitions{
	private FLinkSubDevice next;
	private int interfaceType;
	private int subType; 
	private int version; 
	private int memSize;
	private int chanels;
	private int baseAddress;
	private int uniceID;
	private FLinkBusInterface busInterface; 
	
	
	
	public void setNextSubdevice(FLinkSubDevice next){
		this.next = next;
	}
	
	public void setInterfaceType(int type){
		this.interfaceType = type;
	}
	
	public void setSupType(int type){
		this.subType = type;
	}
	
	public void setVersion(int version){
		this.version = version;
	}
	
	public void setMemSize(int size){
		this.memSize = size;
	}
	
	public void setUniceID(int uniceID){
		this.uniceID = uniceID;
	}
	
	public void setChanels(int chanels){
		this.chanels = chanels;
	}

	public void setBaseAddress(int base) {
		this.baseAddress = base;
	}

	public void setBusInterface(FLinkBusInterface busInterface) {
		this.busInterface = busInterface;
	}

	public int getMemSize() {
		return this.memSize;
	}

	public FLinkSubDevice getNext() {
		return this.next;
	}
	
	public int getInterfaceType(){
		return this.interfaceType;
	}

	public int getSubtype() {
		return this.subType;
	}

	public int getVersion() {
		return this.version;
	}

	public int getNumberOfChanels() {
		return this.chanels;
	}
	
	public int getUniceID() {
		return this.uniceID;
	}
	
	public int read(int address){
		return busInterface.read(this.baseAddress + TOTAL_HEADER_SIZE + address);
	}
	
	public void write(int address,int data ){
		busInterface.write(this.baseAddress + TOTAL_HEADER_SIZE + address,data);
	}
	
	public int getModConfReg(){
		return busInterface.read(this.baseAddress + MOD_CONF_OFFSET);
	}
	
	public int getModStatusReg(){
		return busInterface.read(this.baseAddress + MOD_STATUS_OFFSET);
	}

	public void setModConfReg(int confReg) {
		busInterface.write(this.baseAddress + MOD_CONF_OFFSET, confReg);
	}
}
