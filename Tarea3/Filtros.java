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
        graphics.setColor(c);
        Font font;
        try{
          font = Font.createFont(Font.PLAIN, new File("Playcrds.TTF"));
          font.deriveFont(10f);
          graphics.setFont(font);
          graphics.drawString(dominoCorrespondiente(c.getRed(),c.getGreen(),c.getBlue()), i, j);
          cont++;
        }catch(Exception e){
          System.out.println("Fuente no encontrada");
        }
      }
      cont = 0;
    }
    return bufferedImage;
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
        Font font;
        try{
          font = Font.createFont(Font.PLAIN, new File("lasvbld_.TTF"));
          font.deriveFont(10f);
          graphics.setFont(font);
          graphics.drawString(dominoCorrespondiente(c.getRed(),c.getGreen(),c.getBlue()), i, j);
          cont++;
        }catch(Exception e){
          System.out.println("Fuente no encontrada");
        }
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
