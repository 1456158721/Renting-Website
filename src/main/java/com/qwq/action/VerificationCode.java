package com.qwq.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.IOException;
import java.util.Random;

/**
 * 生成验证码
 * @Author: QWQ
 * @Date: 2019.11.06 16:49:30
 * @Version: 1.0
 */
@Controller("generateVerificationCode")
@Scope("prototype")
@ParentPackage(value = "struts-default")
public class VerificationCode extends ActionSupport {
    public static final String VERIFY_CODES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    @Action(value = "generateVerificationCode")
    public void generateVerificationCode(){
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder codes = new StringBuilder();
        for(int i = 0; i < 4; i++){
            int index = rand.nextInt(VERIFY_CODES.length() - 1);
            char c = VERIFY_CODES.charAt(index);
            codes.append(c);
        }
        BufferedImage img = new BufferedImage(70, 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        g.setColor(getRandColor(200, 250));
        //画一个矩形
        g.fillRect(0, 0, 70, 25);
        g.setFont(new Font("微软雅黑", Font.ITALIC, 15));
        char[] chars = codes.toString().toCharArray();
        //保存验证码
        ServletActionContext.getRequest().getSession().setAttribute("realVerificationCode", codes.toString().trim());
        for(int i = 0; i < codes.length(); i++){
            g.setColor(getRandColor(1, 255));
            g.drawChars(chars, i, 1, i * 15 + 5, 20);
        }
        g.dispose();
        try {
            ImageIO.write(img, "jpg", ServletActionContext.getResponse().getOutputStream());
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }
    //生成颜色
    private Color getRandColor(int fc, int bc){
        if(fc > 255){
            fc = 255;
        }
        if(bc > 255){
            bc = 255;
        }
        Random rand = new Random();
        int r = fc + rand.nextInt(bc - fc);
        int g = fc + rand.nextInt(bc - fc);
        int b = fc + rand.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
