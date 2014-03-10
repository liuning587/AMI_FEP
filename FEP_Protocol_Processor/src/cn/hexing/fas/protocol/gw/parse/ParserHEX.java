package cn.hexing.fas.protocol.gw.parse;

import cn.hexing.exception.MessageDecodeException;
import cn.hexing.exception.MessageEncodeException;

/**
 * ʮ��������ʮ�������ַ���ת����ʽ
 *
 */
public class ParserHEX {	
	/**
	 * HEX->HEX
	 * @param data(���ֽڵ��õ�HEX) 	���磺1234
	 * @param len(�ַ���)				���磺4
	 * @return String(HEX)			���磺3412
	 */
	public static String parseValue(String data,int len){
		String rt="";
		try{
			rt=DataSwitch.ReverseStringByByte(data.substring(0,len));			
		}catch(Exception e){
			throw new MessageDecodeException(e);
		}
		return rt;
	}
	
	/**
	 * HEX->HEX
	 * @param data(HEX) 				���磺1234
	 * @param len(����ʮ�������ַ�����)		���磺4
	 * @return String(���ֽڵ��õ�HEX)		���磺3412
	 */
	public static String constructor(String data,int len){
		String rt="";
		try{			
	        rt=DataSwitch.StrStuff("0",len,data,"left");//���㳤�Ȳ�0
	        rt=DataSwitch.ReverseStringByByte(rt);//���ֽڵ���
		}catch(Exception e){
			throw new MessageEncodeException(e);
		}
		return rt;
	}
}