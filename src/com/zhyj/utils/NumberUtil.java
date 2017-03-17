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
		int index = money.indexOf(".");
		if( index != -1 ){
			if(money.length() - index > 2){
				//如果小数点数大于两位
				//处理
			}
			money = money.substring( 0 , money.indexOf(".") ) + money.substring(money.indexOf(".") + 1 );
			System.out.println(money);
			isPoint = true;
			//System.out.println(isPoint);
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

	public static String getMoneyStr() {
		return moneyStr;
	}
	
    //###############################################################
}
