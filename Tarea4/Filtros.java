import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
/** Filtros del 1 -6 **/
public class Filtros{
  /**
   * Imagen con valores al azar RGB.
   */
  public BufferedImage abreImagen(String ruta){
    try{
     File input = new File(ruta);
     BufferedImage img = ImageIO.read(input);
     return img;
    }catch (Exception e) {
    }
    return null;
  }
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
   public BufferedImage sinUnComponente(Boolean r, Boolean g, Boolean b, BufferedImage img){
     try{
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
    * Transformar imagen dejando solo un componente
    */
    public BufferedImage soloUnComponente(Boolean r, Boolean g, Boolean b, BufferedImage img){
      try{
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
            c = new Color(red,0,0);
            else if(!r&&g&&!b)
            c = new Color(0,green,0);
            else if(!r&&!g&&b)
            c = new Color(0,0,blue);
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
  public BufferedImage escalaDeGrisesA(BufferedImage img){
    try{
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
   public BufferedImage escalaDeGrisesB(Boolean r, Boolean g, Boolean b,BufferedImage img){
     try{
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
   public BufferedImage escalaDeGrisesC(BufferedImage img){
     try{
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
  public BufferedImage altoContraste(BufferedImage img){
    try{
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
  public BufferedImage inverso(BufferedImage img){
   try{
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
  public BufferedImage brillo(int brillo, BufferedImage img){
   try{
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
  public BufferedImage mosaico(int w, int h, BufferedImage img){
   try{
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
             c = new Color(img.getRGB(k, l));
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

  public BufferedImage sepia(int x, BufferedImage img){
    try{
      BufferedImage imgG = escalaDeGrisesC(img);
      int ancho = imgG.getWidth();
      int alto = imgG.getHeight();
      BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
      for(int i = 0; i<ancho; i++){
        for(int j = 0; j<alto; j++){
          Color c = new Color(imgG.getRGB(i, j));
          int red = rangoCorrecto(c.getRed()+(x*2));
          int green = rangoCorrecto(c.getGreen()+x);
          int blue = rangoCorrecto(c.getBlue());
          //int total = (int)(red+green+blue)/3;
          if (red <= (x*2)-1)
            red = 255;
          if (green <= (x-1)){
            green = 255;
            System.out.println("red: "+red+" green: "+green+" blue:"+blue);
          }
          c = new Color(red,green,blue);
          bufferedImage.setRGB(i,j,new Color(red,green,blue).getRGB());
        }
      }
      return bufferedImage;
    }catch (Exception e) {

    }
    return null;
  }
  /**
  * Mosaico
  */
  public BufferedImage blurSuave(BufferedImage img){
    double [][] matriz = {{0.0,0.2,0.0},
                          {0.2,0.2,0.2},
                          {0.0,0.2,0.0}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage blurMedio(BufferedImage img){
    double [][] matriz = {{0,0,1,0,0},
                          {0,1,1,1,0},
                          {1,1,1,1,1},
                          {0,1,1,1,0},
                          {0,0,1,0,0}};
    double factor = 1.0/13.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage motionBlur(BufferedImage img){
    double [][] matriz = {{1,0,0,0,0,0,0,0,0},
                          {0,1,0,0,0,0,0,0,0},
                          {0,0,1,0,0,0,0,0,0},
                          {0,0,0,1,0,0,0,0,0},
                          {0,0,0,0,1,0,0,0,0},
                          {0,0,0,0,0,1,0,0,0},
                          {0,0,0,0,0,0,1,0,0},
                          {0,0,0,0,0,0,0,1,0},
                          {0,0,0,0,0,0,0,0,1}};
    double factor = 1.0/9.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,0.11,0);
  }
  public BufferedImage encuentraBordesA(BufferedImage img){
    double [][] matriz ={{0.0,0.0, -1.0,0.0,0.0},
                         {0.0,0.0, -1.0,0.0,0.0},
                         {0.0,0.0,  2.0,0.0,0.0},
                         {0.0,0.0,  0.0,0.0,0.0},
                         {0.0,0.0,  0.0,0.0,0.0}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage encuentraBordesB(BufferedImage img){
    double [][] matriz ={{0,  0, -1,  0,  0},
                          {0,  0, -1,  0,  0},
                          {0,  0,  4,  0,  0},
                          {0,  0, -1,  0,  0},
                          {0,  0, -1,  0,  0}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage encuentraBordesC(BufferedImage img){
    double [][] matriz ={{-1,  0,  0,  0,  0},
                          {0, -2,  0,  0,  0},
                          {0,  0,  6,  0,  0},
                          {0,  0,  0, -2,  0},
                          {0,  0,  0,  0, -1}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage encuentraBordesD(BufferedImage img){
    double [][] matriz ={{-1, -1, -1},
                          {-1,  8, -1},
                          {-1, -1, -1}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage sharpenA(BufferedImage img){
    double [][] matriz ={{-1, -1, -1},
                          {-1,  9, -1},
                          {-1, -1, -1}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage sharpenB(BufferedImage img){
    double [][] matriz ={{-1, -1, -1, -1, -1},
                          {-1,  2,  2,  2, -1},
                          {-1,  2,  8,  2, -1},
                          {-1,  2,  2,  2, -1},
                          {-1, -1, -1, -1, -1}};
    double factor = 1.0 / 8.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage sharpenC(BufferedImage img){
    double [][] matriz ={{1, 1, 1},
                          {1,  -7, 1},
                          {1, 1, 1}};
    double factor = 1.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage embossA(BufferedImage img){
    double [][] matriz ={ {-1,-1, 0},
                          {-1, 0, 1},
                          { 0, 1, 1}};
    double factor = 1.0;
    double bias = 128.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage embossB(BufferedImage img){
    double [][] matriz ={ {-1, -1, -1, -1,  0},
                          {-1, -1, -1,  0,  1},
                          {-1, -1,  0,  1,  1},
                          {-1,  0,  1,  1,  1},
                          { 0,  1,  1,  1,  1}};

    double factor = 1.0;
    double bias = 128.0;
    return convolucionAux(img, matriz,factor,bias);
  }
  public BufferedImage promedio(BufferedImage img){
    double [][] matriz ={ {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1},
                          {1, 1, 1, 1, 1, 1, 1, 1, 1}};

    double factor = 1.0/81.0;
    double bias = 0.0;
    return convolucionAux(img, matriz,factor,bias);
  }

  public BufferedImage convolucionAux(BufferedImage img, double[][] matriz, double factor, double bias){
    try{
        int ancho = img.getWidth();
        int alto = img.getHeight();
        BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
        for(int i = 0; i<ancho;i++){
          for(int j = 0; j<alto;j++){
            Color c = null;
            double t = 0;
            Color tc = new Color(img.getRGB(i, j));
            double conRed = 0;
            double conGreen = 0;
            double conBlue = 0;
            int mati = 0;
            int matj = 0;
            int matPerPixel = (int)matriz.length/2;
            for(int k = i-matPerPixel;k<=i+matPerPixel;k++){
              for(int l = j-matPerPixel;l<=j+matPerPixel;l++){
                int rgb = 0;
                try{
                  rgb = img.getRGB(k,l);
                }catch (Exception e) {}
                Color ca = new Color(rgb);
                conRed += ca.getRed()*matriz[mati][matj];
                conGreen += ca.getGreen()*matriz[mati][matj];
                conBlue += ca.getBlue()*matriz[mati][matj];
                matj++;
              }
              matj = 0;
              mati++;
            }
            c = new Color(rangoCorrecto((int)(factor*conRed+bias)),
                          rangoCorrecto((int)(factor*conGreen+bias)),
                          rangoCorrecto((int)(factor*conBlue+bias)));
            bufferedImage.setRGB(i,j,c.getRGB());
          }
      }
      return bufferedImage;
    }catch (Exception e) {
      System.out.println("ERROR");
    }
    return null;
    }
    public BufferedImage mediana(int t, BufferedImage img){
      try{
          int ancho = img.getWidth();
          int alto = img.getHeight();
          BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
          for(int i = 0; i<ancho;i++){
            for(int j = 0; j<alto;j++){
              Color c = null;
              Color tc = new Color(img.getRGB(i, j));
              int matPerPixel = (int)t/2;
              ArrayList<Integer> red = new ArrayList<Integer>();
              ArrayList<Integer> green = new ArrayList<Integer>();
              ArrayList<Integer> blue = new ArrayList<Integer>();
              for(int k = i-matPerPixel;k<=i+matPerPixel;k++){
                for(int l = j-matPerPixel;l<=j+matPerPixel;l++){
                  int rgb = 0;
                  try{
                    rgb = img.getRGB(k,l);
                  }catch (Exception e) {}
                  if(rgb != 0){
                    Color ca = new Color(rgb);
                    red.add(ca.getRed());
                    green.add(ca.getGreen());
                    blue.add(ca.getBlue());
                  }
                }
              }
              c = new Color(media(red),media(green),media(blue));
              bufferedImage.setRGB(i,j,c.getRGB());
            }
          }
        return bufferedImage;
    }catch (Exception e) {
      System.out.println("ERROR");
    }
    return null;
  }
  public int media(ArrayList<Integer> lst){
    Collections.sort(lst);
    int s = lst.size();
    return lst.get(lst.size()/2);
  }
  public BufferedImage soloConM(BufferedImage img){
    return conUnaLetra("M", img);
  }
  public BufferedImage soloConArroba(BufferedImage img){
    return conUnaLetra("@", img);
  }
  public BufferedImage grisesConM(BufferedImage img){
    return conUnaLetra("M", escalaDeGrisesC(img));
  }
  public BufferedImage grisesConArroba(BufferedImage img){
    return conUnaLetra("@", escalaDeGrisesC(img));
  }
  public BufferedImage conUnaLetra(String letra,BufferedImage img){
    int tamMosaico = 10;
    BufferedImage imgM = mosaico(tamMosaico, tamMosaico, img);
    int ancho = imgM.getWidth();
    int alto = imgM.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, ancho, alto);
    for(int i = 0; i<ancho; i+=tamMosaico){
      for(int j = 0; j<alto; j+=tamMosaico){
        Color c = new Color(imgM.getRGB(i,j));
        graphics.setColor(c);
        graphics.setFont(new Font("DIALOG", Font.PLAIN, tamMosaico));
        graphics.drawString(letra, i, j);
      }
    }
    return bufferedImage;
  }
  public BufferedImage cadenaSinColor(BufferedImage img){
    return cadenaFija(img, false);
  }
  public BufferedImage cadenaConColor(BufferedImage img){
    return cadenaFija(img, true);
  }
  public BufferedImage cadenaEnGrises(BufferedImage img){
    return cadenaFija(escalaDeGrisesC(img), true);
  }
  public BufferedImage cadenaFija(BufferedImage img, Boolean usarColor){
    int tamMosaico = 10;
    BufferedImage imgM = mosaico(tamMosaico, tamMosaico, img);
    int ancho = imgM.getWidth();
    int alto = imgM.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, ancho, alto);
    for(int i = 0; i<ancho; i+=tamMosaico){
      for(int j = 0; j<alto; j+=tamMosaico){
        Color c = new Color(imgM.getRGB(i,j));
        if(usarColor)
          graphics.setColor(c);
        else
          graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("DIALOG", Font.PLAIN, tamMosaico));
        graphics.drawString(letraCorrespondiente(c.getRed(),c.getGreen(),c.getBlue()), i, j);
      }
    }
    return bufferedImage;
  }
  public String letraCorrespondiente(int r, int g, int b){
    int prom = (r+g+b)/3;
    if (prom >= 0 && prom < 16){
        return "M";
    } else if(prom >= 16 && prom < 32){
        return "N";
    }else if(prom >= 32 && prom < 48){
        return "H";
    }else if(prom >= 48 && prom < 64){
        return "#";
    }else if(prom >= 64 && prom < 80){
        return "Q";
    }else if(prom >= 80 && prom < 96){
        return "U";
    }else if(prom >= 96 && prom < 112){
        return "A";
    }else if(prom >= 112 && prom < 128){
        return "D";
    }else if(prom >= 128 && prom < 144){
        return "O";
    }else if(prom >= 144 && prom < 160){
        return "Y";
    }else if(prom >= 160 && prom < 176){
        return "2";
    }else if(prom >= 176 && prom < 192){
        return "$";
    }else if(prom >= 192 && prom < 208){
        return "%";
    }else if(prom >= 208 && prom < 224){
        return "+";
    }else if(prom >= 224 && prom < 240){
        return "_";
    }else if(prom >= 240 && prom < 256){
        return " ";
    }
    return " ";
  }
  public BufferedImage conTexto(BufferedImage img, String texto){
    int tamMosaico = 10;
    int tamTexto = texto.length();
    BufferedImage imgM = mosaico(tamMosaico, tamMosaico, img);
    int ancho = imgM.getWidth();
    int alto = imgM.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, ancho, alto);
    int cont = 0;
    for(int i = 0; i<ancho; i+=tamMosaico){
      for(int j = 0; j<alto; j+=tamMosaico){
        Color c = new Color(imgM.getRGB(i,j));
        graphics.setColor(c);
        graphics.setFont(new Font("DIALOG", Font.PLAIN, tamMosaico));
        graphics.drawString(texto.charAt(cont % tamTexto)+"", i, j);
        cont++;
      }
      cont = 0;
    }
    return bufferedImage;
  }
  public BufferedImage naipes(BufferedImage img){
    int tamMosaico = 10;
    BufferedImage imgM = mosaico(tamMosaico, tamMosaico, img);
    int ancho = imgM.getWidth();
    int alto = imgM.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, ancho, alto);
    int cont = 0;
    for(int i = 0; i<ancho; i+=tamMosaico){
      for(int j = 0; j<alto; j+=tamMosaico){
        Color c = new Color(imgM.getRGB(i,j));
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("PlayingCards", Font.PLAIN, tamMosaico));
        graphics.drawString(naipeCorrespondiente(c.getRed(),c.getGreen(),c.getBlue()), i, j);
        cont++;

      }
      cont = 0;
    }
    return bufferedImage;
  }
  public String naipeCorrespondiente(int r, int g, int b){
    int prom = (r+g+b)/3;
    if (prom >= 0 && prom < 23){
       return "Z";
    } else if(prom >= 23 && prom < 46){
       return "W";
    }else if(prom >= 46 && prom < 69){
       return "V";
    }else if(prom >= 69 && prom < 92){
       return "U";
    }else if(prom >= 92 && prom < 115){
       return "T";
    }else if(prom >= 115 && prom < 138){
       return "S";
    }else if(prom >= 138 && prom < 161){
       return "R";
    }else if(prom >= 161 && prom < 184){
       return "Q";
    }else if(prom >= 184 && prom < 207){
       return "P";
    }else if(prom >= 207 && prom < 230){
       return "O";
    }else if(prom >= 230 && prom < 256){
       return "N";
    }
    return "";
  }
  public BufferedImage domino(BufferedImage img){
    int tamMosaico = 10;
    BufferedImage imgM = mosaico(tamMosaico, tamMosaico, img);
    int ancho = imgM.getWidth();
    int alto = imgM.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    Graphics graphics = bufferedImage.getGraphics();
    graphics.setColor(Color.WHITE);
    graphics.fillRect(0, 0, ancho, alto);
    int cont = 0;
    for(int i = 0; i<ancho; i+=tamMosaico){
      for(int j = 0; j<alto; j+=tamMosaico){
        Color c = new Color(imgM.getRGB(i,j));
        graphics.setColor(c);
        graphics.setFont(new Font("Las Vegas Black Dominoes", Font.PLAIN, tamMosaico));
        graphics.drawString(dominoCorrespondiente(c.getRed(),c.getGreen(),c.getBlue()), i, j);
        cont++;
      }
      cont = 0;
    }
    return bufferedImage;
  }
  public String dominoCorrespondiente(int r, int g, int b){
    int prom = (r+g+b)/3;
    if (prom >= 0 && prom < 37){
        return "6";
    } else if(prom >= 37 && prom < 74){
        return "5";
    }else if(prom >= 74 && prom < 111){
        return "4";
    }else if(prom >= 111 && prom < 148){
        return "3";
    }else if(prom >= 148 && prom < 185){
        return "2";
    }else if(prom >= 185 && prom < 222){
        return "1";
    }else if(prom >= 222 && prom < 256){
        return "0";
    }
    return "";
  }

  public BufferedImage recursivaGrises(BufferedImage img){
    return recursivaG(escalaDeGrisesC(img));
  }
  private BufferedImage recursivaG(BufferedImage img){
    Map<Integer,BufferedImage> imagenes= new HashMap<Integer,BufferedImage>();
    BufferedImage imgGrisReducida = reducirImagen(img, 0.1);
    BufferedImage mosaicos1 = reducirImagen(imgGrisReducida,0.1);
    int anchoRealMosaico = rightDivPlus(mosaicos1.getWidth(),img.getWidth());
    int altoRealMosaico = rightDivPlus(mosaicos1.getHeight(),img.getHeight());
    BufferedImage mosaicos = reducirImagen(imgGrisReducida,anchoRealMosaico,altoRealMosaico);
    BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
    ArrayList<Integer> valoresRGB = new ArrayList();
    for (int i = -125; i<126; i+=5) {
      distintosGrises(mosaicos, i,imagenes);
    }
    for(Integer k : imagenes.keySet()){
      valoresRGB.add(k);
    }
    Collections.sort(valoresRGB);
    BufferedImage imgMosaico = mosaico(mosaicos.getWidth(),mosaicos.getHeight(),img);
    System.out.println(anchoRealMosaico+" , "+altoRealMosaico);
    for(int i = 0; i<imgMosaico.getWidth(); i+=anchoRealMosaico){
      for(int j = 0; j<imgMosaico.getHeight(); j+=altoRealMosaico){
        Color c = new Color(imgMosaico.getRGB(i,j));
        BufferedImage imagenEnMosaico = imagenes.get(buscaValor(valoresRGB,c.getRed()));
        for (int k = 0;k< imagenEnMosaico.getWidth();k++) {
          for (int l = 0;l<imagenEnMosaico.getHeight();l++) {
              Color c1 = new Color(imagenEnMosaico.getRGB(k,l));
              resultado.setRGB(i+k,j+l,c1.getRGB());
          }
        }
      }
    }
    return resultado;
  }
  private int buscaValor(ArrayList<Integer> valores, int valor){
    int temp = 0;
    if(valor < valores.get(0)){
      return valores.get(0);
    }else if(valor > valores.get(valores.size()-1)){
      return valores.get(valores.size()-1);
    }else{
      for (int i = 0; i <= valores.size(); i++) {
        if(valor == valores.get(i)){
          return valores.get(i);
        }else if(valores.get(i) > valor){
          return temp;
        }
        temp = valores.get(i);
      }
    }
    return 0;
  }
  private BufferedImage reducirImagen(BufferedImage img, double porcentaje){
    double an = img.getWidth()*porcentaje;
    double al = img.getHeight()*porcentaje;
    int ancho = (int) an;
    int alto = (int) al;
    BufferedImage resizedImage = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
  	Graphics2D g = resizedImage.createGraphics();
  	g.drawImage(img, 0, 0, ancho, alto, null);
  	g.dispose();

    return resizedImage;
  }
  private BufferedImage reducirImagen(BufferedImage img, int ancho, int alto){
    BufferedImage resizedImage = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
  	Graphics2D g = resizedImage.createGraphics();
  	g.drawImage(img, 0, 0, ancho, alto, null);
  	g.dispose();
    return resizedImage;
  }
  //-imgReducida hasta 125
  private void distintosGrises(BufferedImage img, int alpha, Map<Integer,BufferedImage> imagenes){
    int ancho = img.getWidth();
    int alto = img.getHeight();
    BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
    int cont = 0;
    int totalR = 0;
    int totalG = 0;
    int totalB = 0;

    for(int i = 0; i<ancho; i++){
      for(int j = 0; j<alto; j++){
        Color c = new Color(img.getRGB(i,j));
        int red = rangoCorrecto(c.getRed()+alpha);
        int green = rangoCorrecto(c.getGreen()+alpha);
        int blue = rangoCorrecto(c.getBlue()+alpha);
        totalR += red;
        totalG += green;
        totalB += blue;
        c = new Color(red,green,blue);
        bufferedImage.setRGB(i,j,c.getRGB());

      }
    }
    int promedio = totalR/(ancho*alto);
    imagenes.put(promedio, bufferedImage);
  }
  public BufferedImage recursivaColor(BufferedImage img){
    return recursivaC(img);
  }
  private BufferedImage recursivaC(BufferedImage img){
    Map<Integer,BufferedImage> imagenes= new HashMap<Integer,BufferedImage>();
    BufferedImage imgReducida = reducirImagen(img, 0.1);
    BufferedImage mosaicos1 = reducirImagen(imgReducida,0.1);
    int anchoRealMosaico = rightDivPlus(mosaicos1.getWidth(),img.getWidth());
    int altoRealMosaico = rightDivPlus(mosaicos1.getHeight(),img.getHeight());
    BufferedImage mosaicos = reducirImagen(imgReducida,anchoRealMosaico,altoRealMosaico);
    BufferedImage resultado = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
    BufferedImage imgReducidaMosaico = mosaico(mosaicos.getWidth(),mosaicos.getHeight(),img);
    System.out.println(anchoRealMosaico+" , "+altoRealMosaico);
    for(int i = 0; i<img.getWidth(); i+=anchoRealMosaico){
      for(int j = 0; j<img.getHeight(); j+=altoRealMosaico){
        Color c = new Color(imgReducidaMosaico.getRGB(i,j));
        BufferedImage imagenEnMosaico = mascaraColorFusion(mosaicos,c.getRed(),c.getGreen(),c.getBlue());
        for (int k = 0;k< imagenEnMosaico.getWidth();k++) {
          for (int l = 0;l<imagenEnMosaico.getHeight();l++) {
              Color c1 = new Color(imagenEnMosaico.getRGB(k,l));
              resultado.setRGB(i+k,j+l,c1.getRGB());
          }
        }
      }
    }
    return resultado;
  }
  private BufferedImage mascaraColor(BufferedImage img, int red, int green, int blue){
    int valor = (blue*255) + (green*16) + red;
    BufferedImage resultado = new  BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
    for(int i = 0; i<img.getWidth(); i++){
      for(int j = 0; j<img.getHeight(); j++){
        Color c = new Color(img.getRGB(i,j));
        int res = c.getRGB() & valor;
        c = new Color(res);
        resultado.setRGB(i,j,c.getRGB());
      }
    }
    return resultado;
  }
  private BufferedImage mascaraColorFusion(BufferedImage img, int red, int green, int blue) {
    int ancho = img.getWidth();
    int alto = img.getHeight();
		BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i<ancho; i++) {
			for(int j = 0; j<alto; j++) {
				Color color1 = new Color(img.getRGB(i, j));
				double r = (double) color1.getRed();
				double g = (double) color1.getGreen();
		    double b = (double) color1.getBlue();
		    double p1 = (double) 35;
		    double p2 = (double) 65;
		    int redN = (int)Math.floor((r*(p1/100)) + (red*(p2/100)));
		    int greenN = (int)Math.floor((g*(p1/100)) + (green*(p2/100)));
		    int blueN = (int)Math.floor((b*(p1/100)) + (blue*(p2/100)));
		    bufferedImage.setRGB(i,j,new Color(redN,greenN,blueN).getRGB());
			}
		}
		return bufferedImage;
	}

  public BufferedImage fusion(BufferedImage img1, BufferedImage img2, int porcentaje1, int porcentaje2){
    int ancho = (img1.getWidth() <= img2.getWidth()) ? img1.getWidth() : img2.getWidth();
    int alto = (img1.getHeight() <= img2.getHeight()) ? img1.getHeight() : img2.getHeight();
		BufferedImage bufferedImage = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB);
		for(int i = 0; i<ancho; i++) {
			for(int j = 0; j<alto; j++) {
				Color color1 = (i < img1.getWidth() && j < img1.getHeight()) ? new Color(img1.getRGB(i, j)) : new Color(255,255,255);
        Color color2 = (i < img2.getWidth() && j < img2.getHeight()) ? new Color(img2.getRGB(i, j)) : new Color(255,255,255);
				double redImg1 = (double) color1.getRed();
				double greenImg1 = (double) color1.getGreen();
		    double blueImg1 = (double) color1.getBlue();
        double redImg2 = (double) color2.getRed();
				double greenImg2 = (double) color2.getGreen();
		    double blueImg2 = (double) color2.getBlue();
		    double p1 = (double) 35;
		    double p2 = (double) 65;
		    int redN = (int)Math.floor((redImg1*(p1/100)) + (redImg2*(p2/100)));
		    int greenN = (int)Math.floor((greenImg1*(p1/100)) + (greenImg2*(p2/100)));
		    int blueN = (int)Math.floor((blueImg1*(p1/100)) + (blueImg2*(p2/100)));
		    bufferedImage.setRGB(i,j,new Color(redN,greenN,blueN).getRGB());
			}
		}
		return bufferedImage;
  }

  public BufferedImage quitaMarcaDeAgua(BufferedImage img){
    
    return null;
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
