package fLink.subDevices;

import fLink.core.FLinkDefinitions;
import fLink.core.FLinkSubDevice;

public class FLinkGPIO implements FLinkDefinitions{
	private static int DIR_ADDRESS = 0;
	private int valAddress;
	public FLinkSubDevice dev;
	
	public FLinkGPIO(FLinkSubDevice dev){
		this.dev = dev;
		if(dev.getNumberOfChanels() == 1){
			this.valAddress = DIR_ADDRESS +REGISTER_WIDTH;
		}else{
			this.valAddress = DIR_ADDRESS + ((dev.getNumberOfChanels()-1)/REGISTER_WIDTH_BIT+1)*REGISTER_WIDTH;
		}
	}
	
	public void setDir(int chanel,boolean input){
		int dirReg = dev.read(DIR_ADDRESS + (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH);
		if(input){
			dirReg = dirReg & ~(1<<(chanel%REGISTER_WIDTH_BIT));
		}else{
			dirReg = dirReg | (1<<(chanel%REGISTER_WIDTH_BIT));
		}
		dev.write(DIR_ADDRESS + (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH, dirReg);
	}
	public boolean isInput(int chanel){
		int dirReg = dev.read(DIR_ADDRESS + (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH);
		if((dirReg & (1<<(chanel%REGISTER_WIDTH_BIT)))>0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getValue(int chanel){
		int valueReg = dev.read(valAddress + (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH);
		if((valueReg & (1<<(chanel%REGISTER_WIDTH_BIT)))!=0){
			return true;
		}else{
			return false;
		}
	}
	
	public void setValue(int chanel, boolean value){
		int valueReg = dev.read(valAddress + (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH);
		if(value){
			valueReg = valueReg | (1<<(chanel%REGISTER_WIDTH_BIT));
		}else{
			valueReg = valueReg & ~(1<<(chanel%REGISTER_WIDTH_BIT));
		}
		dev.write(valAddress+ (chanel/REGISTER_WIDTH_BIT)*REGISTER_WIDTH, valueReg);
	}
	

}
