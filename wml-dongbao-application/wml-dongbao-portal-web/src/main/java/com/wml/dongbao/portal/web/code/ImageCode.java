package com.wml.dongbao.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Random;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/18 - 07 - 18 - 12:01
 * @Description: com.wml.dongbao.portal.web.code
 * @version: 1.0
 */
//创建数字验证码

@Data
public class ImageCode {
    //图形中的内容
    private String code;
    //图片
    private ByteArrayInputStream image;

    private int width = 400;

    private int height =100;

    //私有的构造器
    //用getInstance获得ImageCode一个对象
    public static ImageCode getInstance() throws IOException {
        return new ImageCode();
    }

    private ImageCode() throws IOException {
        //1、图形缓存区 给个黑板
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        //2、给一只画笔
        Graphics graphics = image.getGraphics();
        //3、拿笔涂色，画图形
        graphics.setColor(new Color(240,252,243));
        //4、画矩形
        graphics.fillRect(0,0,width,height);
        //5、字体
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        //6、随机数
        Random random = new Random();
        //7、把随机数给code，但是先给code空字符串
        //生成六位数字
        this.code = "";

       /* for (int i=0 ;i<6;i++)
        {
            //10以内的随机数
            String s = String.valueOf(random.nextInt(10));
            //赋值给code
            this.code += s;
            //给数字颜色
            graphics.setColor(new Color(0,0,0));
            //画字
            graphics.drawString(s,(width/6*i),40);

            //给字体划线
            graphics.setColor(new Color(15,20,247));
            graphics.drawLine((width/6*i),40,(width/6*i)+25,40-20);
        }*/

        // 识别中文测试
       //graphics.setColor(new Color(0,0,0));
       //graphics.drawString("我是中国人",(width/6),40);


        //
        int num1 = random.nextInt(20);
        int num2 = random.nextInt(20);
        graphics.setColor(new Color(15,30,204));

        graphics.drawString(num1+"",(width/6)*0+3,60);
        graphics.drawString("+",(width/6)*1+3,60);
        graphics.drawString(num2+"",(width/6)*2+3,60);
        graphics.drawString("=",(width/6)*3+3,60);
        graphics.drawString("?",(width/6)*4+3,60);

        int result = num1+num2;
        this.code = result+"";


        //干扰数字线条
        graphics.setColor(new Color(100,100,100));
        for (int i=0;i<100;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y1 = random.nextInt(20);
            graphics.drawLine(x,y,x+x1,y+y1);

        }

        //8、收笔
        graphics.dispose();

        //给图片赋值
        //定义对象
        ByteArrayInputStream inputStream =null;
        ByteOutputStream outputStream = new ByteOutputStream();


        //1、创建图片的输出
        //赋值给byteArrayInputStream
        ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);

        ImageIO.write(image,"jpeg",imageOutputStream);

        try {
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        }catch (Exception e){
            System.out.println("生成验证码失败");
        }
         this.image = inputStream;

    }

}
