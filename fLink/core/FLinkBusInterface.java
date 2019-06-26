package fLink.core;

public abstract class FLinkBusInterface {
	
	/**
	 * 
	 * @return Memory size in byte
	 */
	public abstract int getMemoryLength();
	
	/**
	 * 
	 * @param address of the register which should be read
	 * @return content of the register 
	 */
	public abstract int read(int address);
	
	/**
	 * 
	 * @param address of the register which should be written
	 * @param data to write
	 */
	public abstract void write (int address, int data);
	
	/**
	 * 
	 * @return True if device has Info device
	 */
	public abstract boolean hasInfoDev();

}
