package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkDAC implements FLinkDefinitions{
	private static int RESOLUTION_ADDRESS = 0;
	private static int VALUE_0_ADDRESS = RESOLUTION_ADDRESS + REGISTER_WITH;
	public FLinkSubDevice dev;
	private int resolution;
	private int bit_mask;
	
	public FLinkDAC(FLinkSubDevice dev){
		this.dev = dev;
		//cache resolution
		this.resolution = dev.read(RESOLUTION_ADDRESS);
		for(int i = 0;i<resolution;i++){
			bit_mask = bit_mask | (0x1<<i);
		}
		
	}
	
	public int getResolution(){
		return resolution;
	}
	
	public int getValue(int chanel){
		if(chanel<dev.getNumberOfChanels()){
			return dev.read(VALUE_0_ADDRESS+chanel*REGISTER_WITH);
		}else{
			return 0;
		}
	}
	
	public void setValue(int chanel,int value){
		if(chanel<dev.getNumberOfChanels()){
			dev.write(VALUE_0_ADDRESS+chanel*REGISTER_WITH,value&bit_mask);
		}
	}
	
	
}
