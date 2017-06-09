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
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Math;
/** Filtros del 1 -6 **/
public class Filtros{


  /**
   * Imagen con valores al azar RGB.
   */

  String promedios = "";

  public BufferedImage abreImagen(String ruta){
    try{
     File input = new File(ruta);
     BufferedImage img = ImageIO.read(input);
     //System.out.println("se abrio: "+ruta);
     return img;
    }catch (Exception e) {
      System.out.println("No se pudo abrir la imagen: "+ruta);
    }
    return null;
  }

  //ArrayList<ArrayList<String>> writer = new ArrayList<ArrayList<String>>();

  public void archivoPromedio(String promedios){
    File file = new File("promedios1.txt");
    //Write Content
    try{
      FileWriter writer = new FileWriter(file);
      writer.write(promedios);
      writer.close();
    }
    catch (Exception e) {

    }
  }

  public String cargarImagenes(String ruta){
    cargarImagenesAux(ruta);
    return promedios;
  }

  public void cargarImagenesAux(String ruta){
    File padre = new File(ruta);
    String[] hijos = padre.list();
    if(padre.isFile() && !padre.getName().equals(".DS_Store")){
        colorPromedio(padre.getPath());
    }
    if(hijos != null){
      for (int i = 0; i<hijos.length; i++) {
        File x = new File(padre+"/"+hijos[i]);
        if(!x.getName().equals(".DS_Store")){
          if(x.isFile()){
            colorPromedio(x.getPath());
          }else if(x.isDirectory()){
            String[] hijos1 = x.list();
            for(String t : hijos1){
              cargarImagenes(x.getPath()+"/"+t);
            }
          }
        }
      }
    }
  }

  private void colorPromedio(String ruta){
    BufferedImage img = abreImagen(ruta);
    if(img != null){
      int[] prom = promRGB(img);
      ArrayList<String> infoImg = new ArrayList<String>();
      infoImg.add(ruta);
      infoImg.add(prom[0]+"");
      infoImg.add(prom[1]+"");
      infoImg.add(prom[2]+"");
      promedios += ruta+" --> "+prom[0]+" "+prom[1]+" "+prom[2]+"\n";
    }

  }
  private int[] promRGB(BufferedImage img){
    int sumR = 0;
    int sumG = 0;
    int sumB = 0;
    int promR = 0;
    int promG = 0;
    int promB = 0;
    int total = img.getWidth()*img.getHeight();
    for(int i = 0; i<img.getWidth();i++){
      for(int j = 0; j<img.getHeight();j++){
        Color c = new Color(img.getRGB(i, j));
        sumR += c.getRed();
        sumG += c.getGreen();
        sumB += c.getBlue();
      }
    }
    promR = (int) sumR/total;
    promG = (int) sumG/total;
    promB = (int) sumB/total;
    int[] prom = {promR,promG,promB};
    return prom;
  }

  public BufferedImage fotomosaico(int ancho, int alto, BufferedImage img, Boolean repetir, String forma){
    int anchoRealMosaico = rightDivPlus(ancho,img.getWidth());
    int altoRealMosaico = rightDivPlus(alto,img.getHeight());
    BufferedImage mosaicoPromedios = mosaico(anchoRealMosaico, altoRealMosaico, img);
    BufferedImage resultado = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
    System.out.println(forma+" repetir: "+repetir);
    BufferedImage imgMosaico = mosaico(anchoRealMosaico,altoRealMosaico,img);
    ArrayList<String> fotosUsadas = new ArrayList<String>();
    for(int i = 0; i<imgMosaico.getWidth(); i+=anchoRealMosaico){
      for(int j = 0; j<imgMosaico.getHeight(); j+=altoRealMosaico){
        Color c = new Color(imgMosaico.getRGB(i,j));
        BufferedImage imgCorrespondiente = null;
        if(forma.equals("LINEAL"))
          imgCorrespondiente = escogeImagenLineal(c.getRed(),c.getGreen(),c.getBlue(),repetir,fotosUsadas);
        else if(forma.equals("EUCLIDIANA"))
          imgCorrespondiente = escogeImagenEuclidiana(c.getRed(),c.getGreen(),c.getBlue(),repetir,fotosUsadas);
        else if(forma.equals("RIEMERSMA"))
          imgCorrespondiente = escogeImagenRiemersma(c.getRed(),c.getGreen(),c.getBlue(),repetir,fotosUsadas);
        else
          imgCorrespondiente = escogeImagenRestas(c.getRed(),c.getGreen(),c.getBlue(),repetir,fotosUsadas);
        BufferedImage imgReducida = reducirImagen(imgCorrespondiente, anchoRealMosaico, altoRealMosaico);
        for (int k = 0;k< imgReducida.getWidth();k++) {
          for (int l = 0;l<imgReducida.getHeight();l++) {
              Color c1 = new Color(imgReducida.getRGB(k,l));
              resultado.setRGB(i+k,j+l,c1.getRGB());
          }
        }
      }
    }
    return resultado;
  }
  public BufferedImage escogeImagenRestas(int red, int green, int blue, Boolean repetir, ArrayList<String> fotosUsadas){
    BufferedImage img = null;
    BufferedReader br = null;
    String rutaImg = "";
    String linea;
		try {
      br = new BufferedReader(new FileReader("promedios.txt"));
      int restaMinima = 255;
			while ((linea = br.readLine()) != null) {
        String[] pathRGB = linea.split(" --> ");
        String[] coloresRGB = pathRGB[1].split(" ");
        int imgR = Integer.parseInt(coloresRGB[0]);
        int imgG = Integer.parseInt(coloresRGB[1]);
        int imgB = Integer.parseInt(coloresRGB[2]);
        int difR = Math.abs(red-imgR);
        int difG = Math.abs(green-imgG);
        int difB = Math.abs(blue-imgB);
        int sumaRestas = difR+difG+difB;
        if(restaMinima > sumaRestas){
          if(!repetir){
            if(!fotosUsadas.contains(pathRGB[0])){
              rutaImg = pathRGB[0];
              restaMinima = sumaRestas;
            }
          }else{
            rutaImg = pathRGB[0];
            restaMinima = sumaRestas;
          }
        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    if(!repetir)
      fotosUsadas.add(rutaImg);
    return abreImagen(rutaImg);
  }

  public BufferedImage escogeImagenLineal(int r1, int g1, int b1, Boolean repetir, ArrayList<String> fotosUsadas){
    BufferedImage img = null;
    BufferedReader br = null;
    String rutaImg = "";
    String linea;
    double restaminima = 16000000;
		try {
      br = new BufferedReader(new FileReader("promedios.txt"));
			while ((linea = br.readLine()) != null) {
        String[] pathRGB = linea.split(" --> ");
        String[] coloresRGB = pathRGB[1].split(" ");
        int r2 = Integer.parseInt(coloresRGB[0]);
        int g2 = Integer.parseInt(coloresRGB[1]);
        int b2 = Integer.parseInt(coloresRGB[2]);
        double colorImagen = (655335*r1)+(256*g1)+(b1);
        double colorImagenTemp = (655335*r2)+(256*g2)+(b2);
        double resta = Math.abs(colorImagen-colorImagenTemp);
        if(restaminima > resta){
          if(!repetir){
            if(!fotosUsadas.contains(pathRGB[0])){
              rutaImg = pathRGB[0];
              restaminima = resta;
            }
          }else{
            rutaImg = pathRGB[0];
            restaminima = resta;
          }
        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    if(!repetir)
      fotosUsadas.add(rutaImg);
    return abreImagen(rutaImg);
  }

  public BufferedImage escogeImagenEuclidiana(int r1, int g1, int b1, Boolean repetir, ArrayList<String> fotosUsadas){
    BufferedImage img = null;
    BufferedReader br = null;
    String rutaImg = "";
    String linea;
		try {
      br = new BufferedReader(new FileReader("promedios.txt"));
      double euclidianaMinima = 255;
			while ((linea = br.readLine()) != null) {
        String[] pathRGB = linea.split(" --> ");
        String[] coloresRGB = pathRGB[1].split(" ");
        int r2 = Integer.parseInt(coloresRGB[0]);
        int g2 = Integer.parseInt(coloresRGB[1]);
        int b2 = Integer.parseInt(coloresRGB[2]);
        double euclidianaP1 = Math.pow((r1-r2),2);
        double euclidianaP2 = Math.pow((g1-g2),2);
        double euclidianaP3 = Math.pow((b1-b2),2);
        double euclidianaActual = Math.sqrt(euclidianaP1+euclidianaP2+euclidianaP3);
        if(euclidianaMinima > euclidianaActual){
          if(!repetir){
            if(!fotosUsadas.contains(pathRGB[0])){
              rutaImg = pathRGB[0];
              euclidianaMinima = euclidianaActual;
            }
          }else{
            rutaImg = pathRGB[0];
            euclidianaMinima = euclidianaActual;
          }
        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    if(!repetir)
      fotosUsadas.add(rutaImg);
    return abreImagen(rutaImg);
  }

  public BufferedImage escogeImagenRiemersma(int r1, int g1, int b1, Boolean repetir, ArrayList<String> fotosUsadas){
    BufferedImage img = null;
    BufferedReader br = null;
    String rutaImg = "";
    String linea;
		try {
      br = new BufferedReader(new FileReader("promedios.txt"));
      double riemersmaMinima = 255;
			while ((linea = br.readLine()) != null) {
        String[] pathRGB = linea.split(" --> ");
        String[] coloresRGB = pathRGB[1].split(" ");
        int r2 = Integer.parseInt(coloresRGB[0]);
        int g2 = Integer.parseInt(coloresRGB[1]);
        int b2 = Integer.parseInt(coloresRGB[2]);

        double r = (r1+r2)/2;
        double deltaR = r1-r2;
        double deltaG = g1-g2;
        double deltaB = b1-b2;

        double riemersmaP1 = (2+(r/256))*(Math.pow(deltaR,2));
        double riemersmaP2 = 4*Math.pow(deltaG,2);
        double riemersmaP3 = (2+((255-r)/256))*(Math.pow(deltaB,2));

        double riemersmaActual = Math.sqrt(riemersmaP1+riemersmaP2+riemersmaP3);
        if(riemersmaMinima > riemersmaActual){
          if(!repetir){
            if(!fotosUsadas.contains(pathRGB[0])){
              rutaImg = pathRGB[0];
              riemersmaMinima = riemersmaActual;
            }
          }else{
            rutaImg = pathRGB[0];
            riemersmaMinima = riemersmaActual;
          }
        }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    if(!repetir)
      fotosUsadas.add(rutaImg);
    return abreImagen(rutaImg);
  }

  /**
  * Mosaico
  */
  public BufferedImage mosaico(int w, int h, BufferedImage img){
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
		    double p1 = (double) porcentaje1;
		    double p2 = (double) porcentaje2;
		    int redN = (int)Math.floor((redImg1*(p1/100)) + (redImg2*(p2/100)));
		    int greenN = (int)Math.floor((greenImg1*(p1/100)) + (greenImg2*(p2/100)));
		    int blueN = (int)Math.floor((blueImg1*(p1/100)) + (blueImg2*(p2/100)));
		    bufferedImage.setRGB(i,j,new Color(redN,greenN,blueN).getRGB());
			}
		}
		return bufferedImage;
  }


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
  * Crear un archivo .jpg
  */
  public void creaImagen(BufferedImage img, String nombre){
    try{
      File ouptut = new File(nombre+".jpg");
      ImageIO.write(img, "jpg", ouptut);
    }catch(Exception e){
    }
  }

  public BufferedImage abrirImg(String t) {
    BufferedImage image = null;
    try {
      File img = new File(t);
      image = ImageIO.read(img);
      System.out.println(image);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return image;
  }
}
