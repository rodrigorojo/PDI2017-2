public class FiltrosMain{
  public static void main(String[] args){
    Filtros f = new Filtros();
    f.creaImagen(f.randomRGB(900,800), "random");
    f.creaImagen(f.unColor(255,0,0,900,800),"rojo");
    f.creaImagen(f.unColor(0,255,0,900,800),"verde");
    f.creaImagen(f.unColor(0,0,255,900,800),"azul");
    f.creaImagen(f.sinUnComponenteRGB(true,false,false,900,800),"rojoAleatorio");
    f.creaImagen(f.sinUnComponenteRGB(false,true,false,900,800),"verdeAleatorio");
    f.creaImagen(f.sinUnComponenteRGB(false,false,true,900,800),"azulAleatorio");
    f.creaImagen(f.sinUnComponente(true,false,false,"V.jpg"),"rojiza");
    f.creaImagen(f.sinUnComponente(false,true,false,"V.jpg"),"verdosa");
    f.creaImagen(f.sinUnComponente(false,false,true,"V.jpg"),"azulada");
    f.creaImagen(f.escalaDeGrisesA("V.jpg"),"grisesA");
    f.creaImagen(f.escalaDeGrisesB(true,false,false,"V.jpg"),"grisesSinRojo");
    f.creaImagen(f.escalaDeGrisesB(false,true,false,"V.jpg"),"grisesSinVerde");
    f.creaImagen(f.escalaDeGrisesB(false,false,true,"V.jpg"),"grisesSinAzul");
    //Falta la formula para escala de grises
    //f.creaImagen(f.escalaDeGrisesC("V.jpg"),"grisesC");
    f.creaImagen(f.altoContraste("V.jpg"),"altoContraste");
    f.creaImagen(f.inverso("V.jpg"),"inverso");
    f.creaImagen(f.brillo(100,"V.jpg"),"brilloAlto");
    f.creaImagen(f.brillo(-100,"V.jpg"),"brilloBajo");
    f.creaImagen(f.mosaico(25,25,"V.jpg"), "mosaico");
  }
}
