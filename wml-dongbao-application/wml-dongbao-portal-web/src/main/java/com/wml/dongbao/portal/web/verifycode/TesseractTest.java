package com.wml.dongbao.portal.web.verifycode;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 14:38
 * @Description: com.wml.dongbao.portal.web.verifycode
 * @version: 1.0
 */
public class TesseractTest {
    //这是一个main方法，是程序的入口：
    public static void main(String[] args) throws TesseractException {

        ITesseract iTesseract = new Tesseract();
        // 语言包 加进来
        iTesseract.setDatapath("F:\\Soft-Setup\\project\\dongbao-mall\\msb-dongbao-mall\\note\\The resources\\language\\tessdata-master");
        //用中文去读取它
        //iTesseract.setLanguage("chi_sim");
       iTesseract.setLanguage("eng");

        File fileDir = new File("D:\\Download\\Photo\\photo");
        for (File file: fileDir.listFiles()){
            String s = iTesseract.doOCR(file);
            System.out.println(file.getName()+ "识别后数字是："+s);

        }

    }
}
