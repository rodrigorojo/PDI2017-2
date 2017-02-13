import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import java.lang.Math;
/** Filtros del 1 -6 **/
public class Filtros{
  /**
   * Imagen con valores al azar RGB.
   */
   public BufferedImage randomRGB(int ancho, int alto){
     Random rndR = new Random();
     Random rndG = new Random();
     Random rndB = new Random();
     BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
     for(int i = 0; i<ancho; i++){
       for(int j = 0; j<alto; j++){
         Color c = new Color(rndR.nextInt(255),rndG.nextInt(255),rndB.nextInt(255));
         bufferedImage.setRGB(i,j,c.getRGB());
       }
     }
     return bufferedImage;
   }
   /**
    * Imagen con un solo color RBG.
    */
  public BufferedImage unColor(int r, int g, int b, int ancho, int alto){
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Color c = new Color(r,g,b);
    for(int i = 0; i<ancho; i++){
      for(int j = 0; j<alto; j++){
        bufferedImage.setRGB(i,j,c.getRGB());
      }
    }
    return bufferedImage;
  }
  /**
   * Imagen nueva con un componente en 0
   */
  public BufferedImage sinUnComponenteRGB(Boolean r, Boolean g, Boolean b, int ancho, int alto){
    Random rndR = new Random();
    Random rndG = new Random();
    Random rndB = new Random();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    for(int i = 0; i<ancho; i++){
      for(int j = 0; j<alto; j++){
        Color c = null;
        if(r&&!g&&!b)
          c = new Color(0,rndG.nextInt(255),rndB.nextInt(255));
        else if(!r&&g&&!b)
          c = new Color(rndR.nextInt(255),0,rndB.nextInt(255));
        else if(!r&&!g&&b)
          c = new Color(rndR.nextInt(255),rndG.nextInt(255),0);
        else
          c = new Color(rndR.nextInt(255),rndG.nextInt(255),rndB.nextInt(255));
        bufferedImage.setRGB(i,j,c.getRGB());
      }
    }
    return bufferedImage;
  }
  /**
   * Transformar imagen dejando un componente en cero
   */
   public BufferedImage sinUnComponente(Boolean r, Boolean g, Boolean b, String ruta){
     try{
       File input = new File(ruta);
       BufferedImage img = ImageIO.read(input);
       int ancho = img.getWidth();
       int alto = img.getHeight();
       BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
       for(int i = 0; i<ancho; i++){
         for(int j = 0; j<alto; j++){
           Color c = new Color(img.getRGB(i, j));
           int red = c.getRed();
           int green = c.getGreen();
           int blue = c.getBlue();
           if(r&&!g&&!b)
           c = new Color(0,green,blue);
           else if(!r&&g&&!b)
           c = new Color(red,0,blue);
           else if(!r&&!g&&b)
           c = new Color(red,green,0);
           else
           c = new Color(red,green,blue);
           bufferedImage.setRGB(i,j,c.getRGB());
         }
       }
       return bufferedImage;
     }catch (Exception e) {

     }
     return null;
   }
  /**
  * Transformar imagen a tonos de gris dividiendo entre tres
  */
  public BufferedImage escalaDeGrisesA(String ruta){
    try{
      File input = new File(ruta);
      BufferedImage img = ImageIO.read(input);
      int ancho = img.getWidth();
      int alto = img.getHeight();
      BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i<ancho; i++){
        for(int j = 0; j<alto; j++){
          Color c = new Color(img.getRGB(i, j));
          int red = c.getRed();
          int green = c.getGreen();
          int blue = c.getBlue();
          int total = (int)(red+green+blue)/3;
          c = new Color(total,total,total);
          bufferedImage.setRGB(i,j,c.getRGB());
        }
      }
      return bufferedImage;
    }catch (Exception e) {

    }
    return null;
  }
  /**
   * Transformar imagen a tonos de gris con (r,r,r),(g,g,g) o (b,b,b)
   */
   public BufferedImage escalaDeGrisesB(Boolean r, Boolean g, Boolean b,String ruta){
     try{
       File input = new File(ruta);
       BufferedImage img = ImageIO.read(input);
       int ancho = img.getWidth();
       int alto = img.getHeight();
       BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
       for(int i = 0; i<ancho; i++){
         for(int j = 0; j<alto; j++){
           Color c = new Color(img.getRGB(i, j));
           int red = c.getRed();
           int green = c.getGreen();
           int blue = c.getBlue();
           if(r&&!g&&!b)
           c = new Color(red,red,red);
           else if(!r&&g&&!b)
           c = new Color(green,green,green);
           else if(!r&&!g&&b)
           c = new Color(blue,blue,blue);
           else
           c = new Color(red,green,blue);
           bufferedImage.setRGB(i,j,c.getRGB());
         }
       }
       return bufferedImage;
     }catch (Exception e) {

     }
     return null;
   }
   /**
   * Transformar imagen a tonos de gris con la formula 0.21 R + 0.72 G + 0.07 B.
   */
   public BufferedImage escalaDeGrisesC(String ruta){
     try{
       File input = new File(ruta);
       BufferedImage img = ImageIO.read(input);
       int ancho = img.getWidth();
       int alto = img.getHeight();
       BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
       for(int i = 0; i<ancho; i++){
         for(int j = 0; j<alto; j++){
           Color c = new Color(img.getRGB(i, j));
           int red = (int)(0.21 * c.getRed());
           int green = (int)(0.72  * c.getGreen());
           int blue = (int)(0.07 * c.getBlue());
           int total = red+green+blue;
           c = new Color(total,total,total);
           bufferedImage.setRGB(i,j,c.getRGB());
         }
       }
       return bufferedImage;
     }catch (Exception e) {

     }
     return null;
   }
  /**
  * Alto contraste
  */
  public BufferedImage altoContraste(String ruta){
    try{
      File input = new File(ruta);
      BufferedImage img = ImageIO.read(input);
      int ancho = img.getWidth();
      int alto = img.getHeight();
      BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i<ancho; i++){
        for(int j = 0; j<alto; j++){
          Color c = new Color(img.getRGB(i, j));
          int red = c.getRed();
          int green = c.getGreen();
          int blue = c.getBlue();
          int total = (int)(red+green+blue)/3;
          if(total>=127)
            c = new Color(255,255,255);
          else
            c = new Color(0,0,0);
          bufferedImage.setRGB(i,j,c.getRGB());
        }
      }
      return bufferedImage;
    }catch (Exception e) {

    }
    return null;
  }
  /**
  * Inverso
  */
  public BufferedImage inverso(String ruta){
   try{
     File input = new File(ruta);
     BufferedImage img = ImageIO.read(input);
     int ancho = img.getWidth();
     int alto = img.getHeight();
     BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
     for(int i = 0; i<ancho; i++){
       for(int j = 0; j<alto; j++){
         Color c = new Color(img.getRGB(i, j));
         int red = c.getRed();
         int green = c.getGreen();
         int blue = c.getBlue();
         int total = (int)(red+green+blue)/3;
         if(total>=127)
          c = new Color(0,0,0);
         else
           c = new Color(255,255,255);
         bufferedImage.setRGB(i,j,c.getRGB());
       }
     }
     return bufferedImage;
   }catch (Exception e) {

   }
   return null;
  }
  /**
  * Brillo
  */
  public BufferedImage brillo(int brillo, String ruta){
   try{
     File input = new File(ruta);
     BufferedImage img = ImageIO.read(input);
     int ancho = img.getWidth();
     int alto = img.getHeight();
     BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
     for(int i = 0; i<ancho; i++){
       for(int j = 0; j<alto; j++){
         Color c = new Color(img.getRGB(i, j));
         int red = rangoCorrecto(c.getRed()+brillo);
         int green = rangoCorrecto(c.getGreen()+brillo);
         int blue = rangoCorrecto(c.getBlue()+brillo);
         int total = (int)(red+green+blue)/3;
         c = new Color(red,green,blue);
         bufferedImage.setRGB(i,j,c.getRGB());
       }
     }
     return bufferedImage;
   }catch (Exception e) {

   }
   return null;
  }
  private int rangoCorrecto(int val){
    if(val > 255)
      return 255;
    else if(val < 0)
      return 0;
    else
      return val;
  }
  /**
  * Mosaico
  */
  public BufferedImage mosaico(int w, int h, String ruta){
   try{
     File input = new File(ruta);
     BufferedImage img = ImageIO.read(input);
     int ancho = img.getWidth();
     int alto = img.getHeight();
     BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
     w = rightDivPlus(w,ancho);
     h = rightDivPlus(h,alto);
     int total = w*h;
     for(int i = 0; i<ancho;){
       for(int j = 0; j<alto;){
         int sumR = 0;
         int sumG = 0;
         int sumB = 0;
         int promR = 0;
         int promG = 0;
         int promB = 0;
         Color c = null;
         for(int k = i; k < i+w; k++){
           for(int l = j; l < j+h; l++){
             c = new Color(img.getRGB(i, j));
             sumR += c.getRed();
             sumG += c.getGreen();
             sumB += c.getBlue();
           }
         }
         promR = (int) sumR/total;
         promG = (int) sumG/total;
         promB = (int) sumB/total;
         c = new Color(promR,promG,promB);

         for(int n = i; n < i+w; n++){
           for(int m = j; m < j+h; m++){
             bufferedImage.setRGB(n,m,c.getRGB());
           }
         }
         j+=h;
       }
       i+=w;
      }
     return bufferedImage;
   }catch (Exception e) {

   }
   return null;
  }
  public int rightDivPlus(int tamCuad, int tamImg){
      if(tamImg%tamCuad != 0){
        for(int i = tamCuad; i<tamImg; i++){
          if(tamImg%tamCuad != 0)
            tamCuad += 1;
          else
            i = tamImg;
        }
      }else{
        return tamCuad;
      }
      return tamCuad;
  }
  /**
  * Crear un archivo .jpg
  */
  public void creaImagen(BufferedImage img, String nombre){
    try{
      File ouptut = new File(nombre+".jpg");
      ImageIO.write(img, "jpg", ouptut);
    }catch(Exception e){

    }
  }

}
