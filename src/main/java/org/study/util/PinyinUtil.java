package org.study.util;


import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {


    /**
     * @author lishaopeng
     **/
    public static String handle(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        if (charArray != null && charArray.length > 0) {
            char c = charArray[0];
            //匹配中文,非中文转换会转换成null
            if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        String string = hanyuPinyinStringArray[0];
                        pinyin.append(string);
                    } else {
                        pinyin.append("#");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    pinyin.append("#");
                }
            } else if (Character.toString(c).matches("[a-zA-Z]+")) {
                pinyin.append(String.valueOf(c).toUpperCase());
            }else {
                pinyin.append("#");
            }
        }


        return String.valueOf(pinyin.subSequence(0, 1));
    }

    /**
     * @author lishaopeng
     **/
    public static String handlepinyin(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder pinyin = new StringBuilder();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        //设置大小写格式
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        //设置声调格式：
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < charArray.length; i++) {
            //匹配中文,非中文转换会转换成null
            if (Character.toString(charArray[i]).matches("[\\u4E00-\\u9FA5]+")) {
                try {
                    String[] hanyuPinyinStringArray = PinyinHelper.toHanyuPinyinStringArray(charArray[i], defaultFormat);
                    if (hanyuPinyinStringArray != null && hanyuPinyinStringArray.length > 0) {
                        String string = hanyuPinyinStringArray[0];
                        pinyin.append(string);
                    } else {
                        pinyin.append(charArray[i]);
                    }
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                    pinyin.append(charArray[i]);
                }
            } else {
                pinyin.append(charArray[i]);
            }
        }

        return String.valueOf(pinyin);
    }


    public static void main(String[] args) {
        System.out.println(handlepinyin("我爱中国"));
    }
}
