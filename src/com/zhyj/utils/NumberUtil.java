package com.zhyj.utils;

/**
 * 数字处理相关类
 * @author zhang
 *
 */
public class NumberUtil {

	/**
	 * 判断是否为一个电话号码
	 * @param str
	 * @return
	 */
	public static boolean checkMobilePhoneNumber(String str){
		String regex = "^1[3,5,7,8]\\d{9}|15\\d{9}|18\\d{9}$";
		if(str.matches(regex)){
			return true;
		}else{
			return false;
		}
	}
    //###############################################################
	
	 /**
     * 是不是一个数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return str != null ? str.matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$") : false;
    }
    
    //###############################################################

	private static final String[] MyScale = {
		"分","角","元","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","兆","拾","佰","仟"
	};
	private static final String[] MyBaseScale = {
		"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"
	};
	private static String moneyStr = "";
	private static boolean isPoint = false;       //标记变量，用于指定单位并标记为是否有小数
	/**
	 * 钱币数值转换，从阿拉伯数字转成汉字表示
	 * 4、大写金额数字到元或角为止，在“元”或“角”之后写“整”或“正”；大写金额数字有分，分后不写“整”
	 * 5、大写金额前未印货币名称的，应加填名称（如“人民币”），货币名称与金额之间不得留空
	 * 6、阿拉伯金额中间有“0”，大写金额要写“零”，如101.50元，汉字大写金额应写成壹佰零壹元伍角整。
	 * 阿拉伯金额数字中间连续有几“0”时，汉字大写金额中可以只写一个“零”，如¥1004.56，汉字大写金额应写成壹仟零肆元伍角陆分。
	 * 阿拉伯金额数字元位为 “0”，或数字中间连续有几个“0”，元位也是“0”，但角位不是“0”时，汉字大写金额可只写一个“零”字，也可不写。
	 * @param money
	 * 		阿拉伯数字
	 * @return moneyStr
	 * 		转换后的中文数字表示
	 */
	public static String accountInFiguresToWords(Object objMoney){
		/*
		 * 1.判断是否是数字
		 * 2.判断小数点位置
		 * 3.从后开始转换
		 */
		String money = String.valueOf(objMoney);
		if( money.indexOf(".") != -1 ){
			money = money.substring( 0 , money.indexOf(".") ) + money.substring(money.indexOf(".") + 1 );
			isPoint = true;
			System.out.println(isPoint);
		}
		for(int i = money.length() ; i > 0 ; i --){
			int myData = Integer.parseInt(String.valueOf(money.charAt(money.length()-i)));
			moneyStr += MyBaseScale[myData];
			if(isPoint ==  true){
				moneyStr += MyScale[i-1];
			}else{
				moneyStr += MyScale[i+1];
			}
		}
		return moneyStr;
	}
	
	/**
	 * 标准的财政部的转换要求
	 * @param objMoney
	 * @return
	 */
	public static String standardAccountInFiguresToWords(Object objMoney){
		/*
		 * 1.判断是否是数字
		 * 2.判断小数点位置
		 * 3.从后开始转换
		 */
		String money = String.valueOf(objMoney);
		if(!objMoney.toString().matches("^[-+]?(([0-9]+)((([.]{0})([0-9]*))|(([.]{1})([0-9]+))))$")){
			return null;//输入不是数字
		}
		int index = money.indexOf(".");//小数点位置
		int decimalPart = 0;//小数部分
		int decimalPlace = 0;//小数位数
		String hasFull = "";
		int intPart = Integer.parseInt(money.substring( 0 , money.indexOf(".")) );
		if( index != -1 ){
			decimalPlace = money.length() - index -1;
			if( decimalPlace > 2){//小于单位:分
				//如果小数点数大于两位，四舍五入4.216->4.22
				decimalPart = Integer.parseInt(money.substring( index + 1 , index + 3 ));
				char ch = money.charAt(index+3);
				if(ch >= '5'){
					decimalPart += 1;
				}
				money = String.valueOf(intPart * 100 + decimalPart);
			}else {
				decimalPart = Integer.parseInt(money.substring( index + 1 ));
				money = money.substring( 0 , money.indexOf(".") ) + money.substring(money.indexOf(".") +1 );
			}
			isPoint = true;
		}
		if(isPoint ){
			System.out.println(decimalPart);
			if((decimalPlace >= 2 && decimalPart%100==0)||
					(decimalPlace == 1 && decimalPart%10==0)){
				hasFull += "整";
				isPoint = false;
				money = money.substring(0, index);
			}
		}
		System.out.println(money+"  "+decimalPlace);
		//120,102
		for(int i = money.length() ; i > 0 ; i --){
			int myData = Integer.parseInt(String.valueOf(money.charAt(money.length()-i)));
			
			if(myData == 0 ){
				if( !"零".equals(moneyStr.substring(moneyStr.length()-1))){
					moneyStr += MyBaseScale[myData];
				}
			}else if(myData != 0 ){
				moneyStr += MyBaseScale[myData];
			}
			
			if(isPoint ==  true){
				System.out.println(i);
				if(i==1 && myData==0){
					//
					moneyStr += MyScale[i-1];
				}else if(myData!=0){
					moneyStr += MyScale[i-1];
				}
			}else{
				if(i==1 && myData==0){
					//
					moneyStr += MyScale[i+1];
				}else if(myData!=0){
					moneyStr += MyScale[i+1];
				}
			}
		}
		return moneyStr+hasFull;
	}
	
	public static String getMoneyStr() {
		return moneyStr;
	}
	
    //###############################################################
}
