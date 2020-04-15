package com.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
  

import javax.imageio.ImageIO;

import java.io.File;   

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
  
public class Colorewmxz {
  private static final int IMAGE_WIDTH = 100;
  private static final int IMAGE_HEIGHT = 100;
  private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
  private static final int FRAME_WIDTH = 2;
  private static MultiFormatWriter mutiWriter = new MultiFormatWriter();
  
 
  //ImageIO.write方法存储genBarcode生成的图片，三个参数分别为:图片,图片格式，存储路径
  public static void encode(String content, int width, int height,
      String srcImagePath, String destImagePath) {
    try {
      ImageIO.write(genBarcode(content, width, height, srcImagePath),
          "jpg", new File(destImagePath));
    } catch (IOException e) {
      e.printStackTrace();
    } catch (WriterException e) {
      e.printStackTrace();
    }
  }
  
  /*
   * @content 连接地址
   * @width 二维码宽度
   * @height 二维码高度
   * @srcImagePath Logo地址
   * @destImagePath 储存路径 
   */
      
  private static BufferedImage genBarcode(String content, int width,
      int height, String srcImagePath) throws WriterException,
      IOException {
    BufferedImage scaleImage = scale(srcImagePath, IMAGE_WIDTH,
        IMAGE_HEIGHT, true);
    int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
    for (int i = 0; i < scaleImage.getWidth(); i++) {
      for (int j = 0; j < scaleImage.getHeight(); j++) {
        srcPixels[i][j] = scaleImage.getRGB(i, j);
      }
    }
    Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
    hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
    hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
    // 生成二维码 
    BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
        width, height, hint);
    // 二维矩阵转为一维像素数组
    int halfW = matrix.getWidth() / 2;
    int halfH = matrix.getHeight() / 2;
    int[] pixels = new int[width * height];
    for (int y = 0; y < matrix.getHeight(); y++) {
      for (int x = 0; x < matrix.getWidth(); x++) {
        // 左上角颜色,根据自己需要调整颜色范围和颜色   
        if (x > 0 && x < 170 && y > 0 && y < 170) {
          Color color = new Color(231, 144, 56);
          int colorInt = color.getRGB();
          pixels[y * width + x] = matrix.get(x, y) ? colorInt
              : 16777215;
        }
        // 读取图片
        else if (x > halfW - IMAGE_HALF_WIDTH
            && x < halfW + IMAGE_HALF_WIDTH
            && y > halfH - IMAGE_HALF_WIDTH
            && y < halfH + IMAGE_HALF_WIDTH) {
          pixels[y * width + x] = srcPixels[x - halfW
              + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
        } else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
            && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
            && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
            + IMAGE_HALF_WIDTH + FRAME_WIDTH)
            || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                && y > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                + IMAGE_HALF_WIDTH + FRAME_WIDTH)
            || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                - IMAGE_HALF_WIDTH + FRAME_WIDTH)
            || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
          pixels[y * width + x] = 0xfffffff;
          // 在图片四周形成边框
        } else {
          // 二维码颜色
          int num1 = (int) (50 - (50.0 - 13.0) / matrix.getHeight()
              * (y + 1));
          int num2 = (int) (165 - (165.0 - 72.0) / matrix.getHeight()
              * (y + 1));
          int num3 = (int) (162 - (162.0 - 107.0)
              / matrix.getHeight() * (y + 1));
          Color color = new Color(num1, num2, num3);
          int colorInt = color.getRGB();
          // 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色； 
          pixels[y * width + x] = matrix.get(x, y) ? colorInt
              : 16777215;
          // 0x000000:0xffffff   
        }
      }
    }
    BufferedImage image = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    image.getRaster().setDataElements(0, 0, width, height, pixels);
    return image;
  }
  
  private static BufferedImage scale(String srcImageFile, int height,
      int width, boolean hasFiller) throws IOException {
    double ratio = 0.0; // 缩放比例
    File file = new File(srcImageFile);
    BufferedImage srcImage = ImageIO.read(file);
    Image destImage = srcImage.getScaledInstance(width, height,
        BufferedImage.SCALE_SMOOTH);
    // 计算比例  
    if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
      if (srcImage.getHeight() > srcImage.getWidth()) {
        ratio = (new Integer(height)).doubleValue()
            / srcImage.getHeight();
      } else {
        ratio = (new Integer(width)).doubleValue()
            / srcImage.getWidth();
      }
      AffineTransformOp op = new AffineTransformOp(
          AffineTransform.getScaleInstance(ratio, ratio), null);
      destImage = op.filter(srcImage, null);
    }
    if (hasFiller) {
      // 补白
      BufferedImage image = new BufferedImage(width, height,
          BufferedImage.TYPE_INT_RGB);
      Graphics2D graphic = image.createGraphics();
      graphic.setColor(Color.white);
      graphic.fillRect(0, 0, width, height);
      if (width == destImage.getWidth(null)) {
          graphic.drawImage(destImage, 0,
              (height - destImage.getHeight(null)) / 2,
              destImage.getWidth(null), destImage.getHeight(null),
              Color.white, null);
      } else {
          graphic.drawImage(destImage,
              (width - destImage.getWidth(null)) / 2, 0,
              destImage.getWidth(null), destImage.getHeight(null),
              Color.white, null);
      }
      graphic.dispose();
      destImage = image;
    }
    return (BufferedImage) destImage;
  }
  public static void main(String[] args) throws UnsupportedEncodingException {
    // 依次为内容(不支持中文),宽,长,中间图标路径,储存路径  
    String logopath="F:/code/logo.jpg";
    //保存的路径必须为具体的文件名称，不然会显示拒绝访问
    String savepath="F:/code/erwm/"+"亲到.jpg";
    String url="http://new.51bzy.com/zywy/sign/signindex.jspx?activeId=1817018";
    Colorewmxz.encode(url,512,512,
        logopath, savepath);  
    System.out.print(savepath+"二维码已经生成");
 
  
  String path="F:/code/erwm/";
  File file=new File(path);
  File[] listfile=file.listFiles();
  if(listfile.length>0) {
      //存在文件
      String[] content = file.list();//取得当前目录下所有文件和文件夹  
      for(String name : content){  
          File temp = new File(path, name);
          temp.delete();     
          }  
  }
  
  } 
}
