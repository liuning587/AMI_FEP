package cn.hexing.fas.protocol.zj.parse;

import java.text.NumberFormat;

import org.apache.log4j.Logger;

import cn.hexing.exception.MessageEncodeException;
import cn.hexing.fk.utils.StringUtil;

/**
 * HEX����ֵ����
 *
 */
public class Parser03 {
	
	private static final Logger log = Logger.getLogger(Parser03.class);

	
	/**
	 * Hex to decimal
	 * @param data ����֡
	 * @param loc  ������ʼλ��
	 * @param len  �����ֽڳ���
	 * @param fraction ���������ݰ�����С��λ��
	 * @return ��������
	 */
	public static Object parsevalue(byte[] data,int loc,int len,int fraction){
		Object rt=null;
		try{
			boolean ok=true;
			/*if((data[loc] & 0xff)==0xff){
				ok=ParseTool.isValid(data,loc,len);
			}*/
			ok=ParseTool.isAllFF(data,loc,len);
			if(!ok){
				int val=ParseTool.nByteToInt(data,loc,len);
				if(fraction>0){
					NumberFormat snf=NumberFormat.getInstance();
					snf.setMinimumFractionDigits(fraction);
					snf.setMinimumIntegerDigits(1);
					snf.setGroupingUsed(false);
					rt=snf.format((double)val/ParseTool.fraction[fraction]);
				}else{
					rt=new Integer(val);
				}
			}
		}catch(Exception e){
			log.error(StringUtil.getExceptionDetailInfo(e));
		}
		return rt;
	}
	
	/**
	 * decimal to Hex
	 * @param frame �ֽڴ������
	 * @param value ��������
	 * @param loc   ��ſ�ʼλ��
	 * @param len   �������
	 * @param fraction ���ݰ���С��λ��
	 * @return
	 */
	public static int constructor(byte[] frame,String value,int loc,int len,int fraction){
		try{
			NumberFormat nf=NumberFormat.getInstance();
			nf.setMaximumFractionDigits(4);			
			
			double val=nf.parse(value).doubleValue();
			if(fraction>0){
				val*=ParseTool.fraction[fraction];
			}
			
			ParseTool.DecimalToBytes(frame,(int)Math.round(val),loc,len);
		}catch(Exception e){
			throw new MessageEncodeException("�����HEX����֡����:"+value);
		}
		
		return len;
	}
}