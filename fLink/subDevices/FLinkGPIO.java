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
			this.valAddress = DIR_ADDRESS +REGISTER_WITH;
		}else{
			this.valAddress = DIR_ADDRESS + ((dev.getNumberOfChanels()-1)/REGISTER_WITH_BIT+1)*REGISTER_WITH;
		}
	}
	
	public void setDir(int chanel,boolean input){
		int dirReg = dev.read(DIR_ADDRESS + (chanel/REGISTER_WITH_BIT)*REGISTER_WITH);
		if(input){
			dirReg = dirReg & ~(1<<(chanel%REGISTER_WITH_BIT));
		}else{
			dirReg = dirReg | (1<<(chanel%REGISTER_WITH_BIT));
		}
		dev.write(DIR_ADDRESS + (chanel/REGISTER_WITH_BIT)*REGISTER_WITH, dirReg);
	}
	public boolean isInput(int chanel){
		int dirReg = dev.read(DIR_ADDRESS + (chanel/REGISTER_WITH_BIT)*REGISTER_WITH);
		if((dirReg & (1<<(chanel%REGISTER_WITH_BIT)))>0){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean getValue(int chanel){
		int valueReg = dev.read(valAddress + (chanel/REGISTER_WITH_BIT)*REGISTER_WITH);
		if((valueReg & (1<<(chanel%REGISTER_WITH_BIT)))!=0){
			return true;
		}else{
			return false;
		}
	}
	
	public void setValue(int chanel, boolean value){
		int valueReg = dev.read(valAddress + (chanel/REGISTER_WITH_BIT)*REGISTER_WITH);
		if(value){
			valueReg = valueReg | (1<<(chanel%REGISTER_WITH_BIT));
		}else{
			valueReg = valueReg & ~(1<<(chanel%REGISTER_WITH_BIT));
		}
		dev.write(valAddress+ (chanel/REGISTER_WITH_BIT)*REGISTER_WITH, valueReg);
	}
	

}
