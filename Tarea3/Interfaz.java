import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.ColumnConstraints;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
/**
 *
 */
 @SuppressWarnings("unchecked")
public class Interfaz extends Application {

    ImageView iv1;
    ImageView iv2;
    Filtros f = new Filtros();
    String ruta;
    BufferedImage descargarImg;
    int cuentaDescargas = 1;
    //TEmp
    Image image;
    /*public void descargaImagen(Image img, String nombre){
      //Image  img = new ImageIcon("test.png").getImage();

      BufferedImage bufferedImage = new BufferedImage((int)img.getWidth(), (int)img.getHeight(),
          BufferedImage.TYPE_INT_RGB);

      Graphics g = bufferedImage.createGraphics();
      g.drawImage(img, 0, 0, null);
      g.dispose();
      System.out.println("hola");
      try{
        ImageIO.write(bufferedImage, "jpg", new File(nombre+".jpg"));
      }catch(Exception e){
        System.out.println("No se pudo descargar la imagen");
      }

    }*/

    @Override
    public void start(Stage primaryStage) {

        Button abrirImg = new Button("Abrir Imagen");
        Button descargar = new Button("Descargar");
        abrirImg.setOnAction(btnLoadEventListener);
        /******************************Basicos*********************************/
        SplitMenuButton filtrosBtn = new SplitMenuButton();
        MenuItem f1   = new MenuItem("1 - Aleatorio");
        MenuItem f2_1 = new MenuItem("2.1 - Solo Rojo");
        MenuItem f2_2 = new MenuItem("2.2 - Solo Verde");
        MenuItem f2_3 = new MenuItem("2.3 - Solo Azul");
        MenuItem f2_4 = new MenuItem("2.4 - Imagen Solo Rojo");
        MenuItem f2_5 = new MenuItem("2.5 - Imagen Solo Verde");
        MenuItem f2_6 = new MenuItem("2.6 - Imagen Solo Azul");
        MenuItem f3_1 = new MenuItem("3.1 - Aleatorio Sin Rojo");
        MenuItem f3_2 = new MenuItem("3.2 - Aleatorio Sin Verde");
        MenuItem f3_3 = new MenuItem("3.3 - Aleatorio Sin Azul");
        MenuItem f3_4 = new MenuItem("3.4 - Imagen Sin Rojo");
        MenuItem f3_5 = new MenuItem("3.5 - Imagen Sin Verde");
        MenuItem f3_6 = new MenuItem("3.6 - Imagen Sin Azul");
        MenuItem f4_1 = new MenuItem("4.1 - Tonos de gris (Suma/3)");
        MenuItem f4_2 = new MenuItem("4.2 - Tonos de gris (Fórmula)");
        MenuItem f5_1 = new MenuItem("5.1 - Tonos de gris (Sin Rojo)");
        MenuItem f5_2 = new MenuItem("5.2 - Tonos de gris (Sin Verde)");
        MenuItem f5_3 = new MenuItem("5.3 - Tonos de gris (Sin Azul)");
        MenuItem f6   = new MenuItem("6 - Alto Contraste");
        MenuItem f7   = new MenuItem("7 - Inverso");
        MenuItem f8   = new MenuItem("8 - Brillo");
        MenuItem f9   = new MenuItem("9 - Mosaico");
        filtrosBtn.setText("Basicos");
        filtrosBtn.getItems().addAll(f1,f2_1,f2_2,f2_3,f2_4,f2_5,f2_6,f3_1,f3_2,f3_3,f3_4,f3_5,f3_6,f4_1,f4_2,
                                    f5_1,f5_2,f5_3,f6,f7,f8,f9);

        f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.randomRGB(900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.unColor(255,0,0,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.unColor(0,255,0,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.unColor(0,0,255,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.soloUnComponente(true,false,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_5.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.soloUnComponente(false,true,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f2_6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.soloUnComponente(false,false,true,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponenteRGB(true,false,false,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponenteRGB(false,true,false,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponenteRGB(false,false,true,900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponente(true,false,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_5.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponente(false,true,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f3_6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sinUnComponente(false,false,true,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f4_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escalaDeGrisesA(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f4_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escalaDeGrisesC(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f5_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escalaDeGrisesB(true,false,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f5_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escalaDeGrisesB(false,true,false,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f5_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escalaDeGrisesB(false,false,true,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.altoContraste(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        f7.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.inverso(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });

        f8.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            Stage stageBrillo = new Stage();
            GridPane gridpaneBrillo = new GridPane();
            ColumnConstraints col1 = new ColumnConstraints();
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setMinWidth(400);
            gridpaneBrillo.getColumnConstraints().addAll(col1,col2);
            Label labelBrillo = new Label("Brillo");
            Slider sliderBrillo = new Slider(-180, 180, 0);
            Label sliderBrilloValor = new Label("0");
            Button buttonBrillo = new Button("Aplicar");
            gridpaneBrillo.setConstraints(labelBrillo, 1,1);
            gridpaneBrillo.setConstraints(sliderBrillo, 1,2);
            gridpaneBrillo.setConstraints(sliderBrilloValor, 2,2);
            gridpaneBrillo.setConstraints(buttonBrillo, 2,5);
            gridpaneBrillo.getChildren().addAll(labelBrillo,sliderBrillo,sliderBrilloValor,buttonBrillo);
            Scene sceneBrillo = new Scene(gridpaneBrillo, 470, 70);
            stageBrillo.setTitle("Ajustar Brillo");
            stageBrillo.setScene(sceneBrillo);
            stageBrillo.show();
            sliderBrillo.valueProperty().addListener(new ChangeListener() {
            @Override
              public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                  sliderBrilloValor.textProperty().setValue(
                          String.valueOf((int) sliderBrillo.getValue()));
                          sliderBrillo.setValue(sliderBrillo.getValue());
              }
            });
            buttonBrillo.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent t) {
                descargarImg = f.brillo((int)sliderBrillo.getValue(),f.abreImagen(ruta));
                iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
              }
            });
          }
        });
        f9.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            try{
              Stage stageMosaico = new Stage();

              int defaultValor = (int) image.getWidth()/9;
              int maxMosaico = (int) image.getWidth()/5;

              GridPane gridpaneMosaico = new GridPane();
              ColumnConstraints col1 = new ColumnConstraints();
              ColumnConstraints col2 = new ColumnConstraints();
              col2.setMinWidth(400);
              gridpaneMosaico.getColumnConstraints().addAll(col1,col2);
              Label labelAncho = new Label("Ancho");
              Slider sliderAncho = new Slider(1, maxMosaico, defaultValor);
              Label sliderAnchoValor = new Label(defaultValor+"");
              Label labelAlto = new Label("Alto");
              Slider sliderAlto = new Slider(1, maxMosaico, defaultValor);
              Label sliderAltoValor = new Label(defaultValor+"");
              Button buttonMosaico = new Button("Aplicar");
              gridpaneMosaico.setConstraints(labelAncho, 1,1);
              gridpaneMosaico.setConstraints(sliderAncho, 1,2);
              gridpaneMosaico.setConstraints(sliderAnchoValor, 2,2);
              gridpaneMosaico.setConstraints(labelAlto, 1,3);
              gridpaneMosaico.setConstraints(sliderAlto, 1,4);
              gridpaneMosaico.setConstraints(sliderAltoValor, 2,4);
              gridpaneMosaico.setConstraints(buttonMosaico, 2,5);
              gridpaneMosaico.getChildren().addAll(labelAncho,sliderAncho,sliderAnchoValor,labelAlto,sliderAlto,sliderAltoValor,buttonMosaico);
              Scene sceneMosaico = new Scene(gridpaneMosaico, 470, 100);
              stageMosaico.setTitle("Tamaño de los mosaicos");
              stageMosaico.setScene(sceneMosaico);
              stageMosaico.show();
              sliderAncho.valueProperty().addListener(new ChangeListener() {
                @Override
                  public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                      sliderAnchoValor.textProperty().setValue(
                              String.valueOf((int) sliderAncho.getValue()));
                              sliderAlto.setValue(sliderAncho.getValue());
                  }
              });
              sliderAlto.valueProperty().addListener(new ChangeListener() {
                @Override
                  public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                      sliderAltoValor.textProperty().setValue(
                              String.valueOf((int) sliderAlto.getValue()));
                              sliderAlto.setValue(sliderAlto.getValue());
                  }
              });
              buttonMosaico.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                  descargarImg = f.mosaico((int)sliderAncho.getValue(),(int)sliderAlto.getValue(),f.abreImagen(ruta));
                  iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
                }
              });
            }catch(Exception E){

            }


          }
        });
        /******************************Basicos*********************************/
        /*****************************Convolucion******************************/
        SplitMenuButton filtrosCBtn = new SplitMenuButton();
        MenuItem fc1_1 = new MenuItem("1.1 - Blur Suave");
        MenuItem fc1_2 = new MenuItem("1.2 - Blur Medio");
        MenuItem fc2 = new MenuItem("2 - Motion Blur");
        MenuItem fc3_1 = new MenuItem("3.1 - Encuentra Bordes A");
        MenuItem fc3_2 = new MenuItem("3.2 - Encuentra Bordes B");
        MenuItem fc3_3 = new MenuItem("3.3 - Encuentra Bordes C");
        MenuItem fc3_4 = new MenuItem("3.4 - Encuentra Bordes D");
        MenuItem fc4_1 = new MenuItem("4.1 - Sharpen A");
        MenuItem fc4_2 = new MenuItem("4.2 - Sharpen B");
        MenuItem fc4_3 = new MenuItem("4.3 - Sharpen C");
        MenuItem fc5_1 = new MenuItem("5.1 - Emboss A");
        MenuItem fc5_2 = new MenuItem("5.2 - Emboss B");
        MenuItem fc6 = new MenuItem("6 - Promedio");
        MenuItem fc7 = new MenuItem("7 - Media");
        MenuItem fc8 = new MenuItem("8 - Sepia");
        filtrosCBtn.setText("Convolución");
        filtrosCBtn.getItems().addAll(fc1_1,fc1_2,fc2,fc3_1,fc3_2,fc3_3,fc3_4,fc4_1,fc4_2,fc4_3,fc5_1,fc5_2,fc6,fc7,fc8);
        fc1_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.blurSuave(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc1_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.blurMedio(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.motionBlur(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc3_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.encuentraBordesA(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc3_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.encuentraBordesB(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc3_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.encuentraBordesC(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc3_4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.encuentraBordesD(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc4_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sharpenA(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc4_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sharpenB(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc4_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.sharpenC(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc5_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.embossA(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc5_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.embossB(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.promedio(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc7.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.mediana(5,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fc8.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            int inicial = 55;
            descargarImg = f.sepia(inicial,f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            Stage stageSepia = new Stage();
            GridPane gridpaneSepia = new GridPane();
            ColumnConstraints col1 = new ColumnConstraints();
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setMinWidth(400);
            gridpaneSepia.getColumnConstraints().addAll(col1,col2);
            Label labelSepia = new Label("Nivel de Sepia");
            Slider sliderSepia = new Slider(30, 160, inicial);
            Label sliderSepiaValor = new Label(inicial+"");
            Button buttonSepia = new Button("Aplicar");
            gridpaneSepia.setConstraints(labelSepia, 1,1);
            gridpaneSepia.setConstraints(sliderSepia, 1,2);
            gridpaneSepia.setConstraints(sliderSepiaValor, 2,2);
            gridpaneSepia.setConstraints(buttonSepia, 2,5);
            gridpaneSepia.getChildren().addAll(labelSepia,sliderSepia,sliderSepiaValor,buttonSepia);
            Scene sceneSepia = new Scene(gridpaneSepia, 470, 70);
            stageSepia.setTitle("Ajustar Sepia");
            stageSepia.setScene(sceneSepia);
            stageSepia.show();
            sliderSepia.valueProperty().addListener(new ChangeListener() {
            @Override
              public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                  sliderSepiaValor.textProperty().setValue(
                          String.valueOf((int) sliderSepia.getValue()));
                          sliderSepia.setValue(sliderSepia.getValue());
              }
            });
            buttonSepia.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent t) {
                descargarImg = f.sepia((int)sliderSepia.getValue(),f.abreImagen(ruta));
                iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
              }
            });
          }
        });
        /*****************************Convolucion******************************/
        /*******************************Letras*********************************/
        SplitMenuButton filtrosLBtn = new SplitMenuButton();
        MenuItem fl1_1 = new MenuItem("1.1 - Con letra 'M'");
        MenuItem fl1_2 = new MenuItem("1.2 - Con '@'");
        MenuItem fl2_1 = new MenuItem("2.1 - Con letra 'M' en grises");
        MenuItem fl2_2 = new MenuItem("2.1 - Con '@' en grises");
        MenuItem fl3   = new MenuItem("3 - Letras sin color");
        MenuItem fl4   = new MenuItem("4 - Letras con colores");
        MenuItem fl5   = new MenuItem("5 - Letras en grises");
        MenuItem fl6   = new MenuItem("6 - Con Texto Dado");
        MenuItem fl7   = new MenuItem("7 - Naipes");
        MenuItem fl8   = new MenuItem("8 - Domino");
        filtrosLBtn.setText("Letras");
        filtrosLBtn.getItems().addAll(fl1_1,fl1_2,fl2_1,fl2_2,fl3,fl4,fl5,fl6,fl7,fl8);
        fl1_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.soloConM(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl1_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.soloConArroba(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl2_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.grisesConM(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl2_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.grisesConArroba(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.cadenaSinColor(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.cadenaConColor(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl5.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.cadenaEnGrises(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {

            Stage stageTexto = new Stage();
            GridPane gridpaneTexto = new GridPane();
            ColumnConstraints col1 = new ColumnConstraints();
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setMinWidth(400);
            gridpaneTexto.getColumnConstraints().addAll(col1,col2);
            Label labelTexto = new Label("Ingresa el texto: ");

            TextField txt = new TextField ();

            Button buttonTexto = new Button("Aplicar");
            gridpaneTexto.setConstraints(labelTexto, 1,1);
            gridpaneTexto.setConstraints(txt, 1,2);
            gridpaneTexto.setConstraints(buttonTexto, 2,5);
            gridpaneTexto.getChildren().addAll(labelTexto,txt,buttonTexto);
            Scene sceneTexto = new Scene(gridpaneTexto, 470, 75);
            stageTexto.setTitle("Texto");
            stageTexto.setScene(sceneTexto);
            stageTexto.show();
            buttonTexto.setOnAction(new EventHandler<ActionEvent>() {
              public void handle(ActionEvent t) {
                descargarImg = f.conTexto(f.abreImagen(ruta),txt.getText());
                iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
              }
            });
          }
        });
        fl7.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.naipes(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        fl8.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.domino(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
        });
        /*******************************Letras*********************************/
        descargar.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            f.creaImagen(descargarImg,"IMG-"+cuentaDescargas);
            cuentaDescargas++;
          }
        });

        iv1 = new ImageView();
        iv1.setFitWidth(500);
        iv1.setPreserveRatio(true);
        iv1.setSmooth(true);
        iv1.setCache(true);

        iv2 = new ImageView();
        iv2.setFitWidth(500);
        iv2.setPreserveRatio(true);
        iv2.setSmooth(true);
        iv2.setCache(true);

        GridPane gridpane = new GridPane();
        GridPane.setConstraints(abrirImg, 1,1);
        GridPane.setConstraints(descargar, 1,1,1,1,HPos.CENTER,VPos.CENTER);
        GridPane.setConstraints(filtrosBtn, 2,1);
        GridPane.setConstraints(filtrosCBtn, 2,1,1,1,HPos.CENTER,VPos.CENTER);
        GridPane.setConstraints(filtrosLBtn, 2,1,1,1,HPos.CENTER,VPos.CENTER);
        GridPane.setConstraints(iv1, 1,2);
        GridPane.setConstraints(iv2, 2,2);
        gridpane.getChildren().addAll(abrirImg, descargar, filtrosBtn,filtrosCBtn,filtrosLBtn,iv1,iv2);
        Scene scene = new Scene(gridpane, 1000, 693);
        primaryStage.setTitle("Filtros RR");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void run() {
        launch();
    }

    EventHandler<ActionEvent> btnLoadEventListener = new EventHandler<ActionEvent>(){

    @Override
    public void handle(ActionEvent t) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        ruta = file.getPath();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            iv1.setImage(image);


        } catch (IOException ex) {
        }

    }
    };
}
