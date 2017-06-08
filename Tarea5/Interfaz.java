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
import javafx.scene.layout.RowConstraints;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCombination;
/**
 *
 */
 @SuppressWarnings("unchecked")
public class Interfaz extends Application {

    ImageView iv1;
    ImageView iv2;
    Filtros f = new Filtros();
    String ruta;
    String ruta2 = "";
    BufferedImage descargarImg;
    int cuentaDescargas = 1;
    //TEmp
    Image image;

    @Override
    public void start(Stage primaryStage) {
      MenuBar menuBar = new MenuBar();
      /*************************Abrir y Descargar******************************/
      Menu archivo = new Menu("Archivo");
      MenuItem abrir = new MenuItem("Abrir Imagen");
      abrir.setAccelerator(KeyCombination.keyCombination("Shortcut+O"));
      abrir.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
              FileChooser fileChooser = new FileChooser();
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
      });
      MenuItem descargar = new MenuItem("Descargar");
      descargar.setAccelerator(KeyCombination.keyCombination("Shortcut+D"));
      descargar.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            f.creaImagen(descargarImg,"IMG-"+cuentaDescargas);
            cuentaDescargas++;
          }
      });
      archivo.getItems().addAll(abrir, descargar);
      /*************************Abrir y Descargar******************************/
      /******************************Basicos*********************************/
      Menu filtrosBasicos = new Menu("Basicos");
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
      filtrosBasicos.getItems().addAll(f1,f2_1,f2_2,f2_3,f2_4,f2_5,f2_6,f3_1,f3_2,f3_3,f3_4,f3_5,f3_6,f4_1,f4_2,
                                  f5_1,f5_2,f5_3,f6,f7,f8,f9);
      f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.randomRGB(900,800);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
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
      Menu filtrosConvolucion = new Menu("Convolución");
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
      filtrosConvolucion.getItems().addAll(fc1_1,fc1_2,fc2,fc3_1,fc3_2,fc3_3,fc3_4,fc4_1,fc4_2,fc4_3,fc5_1,fc5_2,fc6,fc7,fc8);
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
      Menu filtrosLetras = new Menu("Letras");
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
      filtrosLetras.getItems().addAll(fl1_1,fl1_2,fl2_1,fl2_2,fl3,fl4,fl5,fl6,fl7,fl8);
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
      /**************Recursivas, Fusion, Quitar Marca de Agua****************/
      Menu filtrosRFMBtn = new Menu("Tarea4");
      MenuItem frfm1 = new MenuItem("1 - Imágenes recursivas en tonos de gris");
      MenuItem frfm2 = new MenuItem("2 - Imágenes recursivas en color real");
      MenuItem frfm3 = new MenuItem("3 - Fusion de dos imagenes");
      MenuItem frfm4 = new MenuItem("4 - Quitar la marca de agua");
      filtrosRFMBtn.getItems().addAll(frfm1,frfm2,frfm3,frfm4);
      frfm1.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.recursivaGrises(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      frfm2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.recursivaColor(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      frfm3.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          Stage stageFusion = new Stage();
          GridPane gridpaneFusion = new GridPane();
          stageFusion.setMaxWidth(500);
          stageFusion.setMinWidth(500);

          ImageView ivimg2;
          ivimg2 = new ImageView();
          ivimg2.setFitWidth(500);
          ivimg2.setPreserveRatio(true);
          ivimg2.setSmooth(true);
          ivimg2.setCache(true);

          Slider sliderFusion = new Slider(0, 100, 50);
          Label sliderFusionValor = new Label("50");

          Button buttonFusionar = new Button("Fusionar");
          Button buttonAbrirImg2 = new Button("Abrir imagen");
          gridpaneFusion.setConstraints(buttonAbrirImg2, 1,1);
          gridpaneFusion.setConstraints(ivimg2, 1,2);
          gridpaneFusion.setConstraints(sliderFusion, 1,3);
          gridpaneFusion.setConstraints(sliderFusionValor, 1,4,1,1,HPos.CENTER,VPos.CENTER);
          gridpaneFusion.setConstraints(buttonFusionar, 1,4,1,1,HPos.RIGHT,VPos.CENTER);

          gridpaneFusion.getChildren().addAll(buttonAbrirImg2,ivimg2,buttonFusionar,sliderFusion,sliderFusionValor);
          Scene sceneTexto = new Scene(gridpaneFusion, 500, 740);
          stageFusion.setTitle("Abir Segunda Imagen");
          stageFusion.setScene(sceneTexto);
          stageFusion.show();

          sliderFusion.valueProperty().addListener(new ChangeListener() {
          @Override
            public void changed(ObservableValue arg0, Object arg1, Object arg2) {
                sliderFusionValor.textProperty().setValue(String.valueOf((int) sliderFusion.getValue()));
                sliderFusion.setValue(sliderFusion.getValue());
            }
          });
          buttonFusionar.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              descargarImg = f.fusion(f.abreImagen(ruta),f.abreImagen(ruta2), (int)sliderFusion.getValue(), 100-(int)sliderFusion.getValue());
              iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            }
          });
          buttonAbrirImg2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              FileChooser fileChooser2 = new FileChooser();
              FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
              FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
              fileChooser2.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              File file2 = fileChooser2.showOpenDialog(null);
              ruta2 = file2.getPath();
              Image image2;
              try {
                  BufferedImage bufferedImage = ImageIO.read(file2);
                  image2 = SwingFXUtils.toFXImage(bufferedImage, null);
                  ivimg2.setImage(image2);
              } catch (IOException ex) {
              }

            }
          });

        }
      });
      frfm4.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.quitaMarcaDeAgua(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });

      /**************Recursivas, Fusion, Quitar Marca de Agua*****************/
      /****************Oleo, AT&T, MaxMin, Semitonos, Favicon*****************/
      Menu filtrosOAMSF = new Menu("Tarea5");
      MenuItem fOleo       = new MenuItem("1 - Óleo (blanco y negro)");
      MenuItem fatt        = new MenuItem("2 - AT&T ");
      MenuItem fMax        = new MenuItem("3 - Filtro Máximo");
      MenuItem fMin        = new MenuItem("4 - Filtro Mínimo");
      MenuItem fSemitonos1 = new MenuItem("5.1 - Semitonos1");
      MenuItem fSemitonos2 = new MenuItem("5.2 - Semitonos2");
      MenuItem fSemitonos3 = new MenuItem("5.3 - Semitonos3");
      MenuItem fFavicon    = new MenuItem("6 - Favicon");
      filtrosOAMSF.getItems().addAll(fOleo,fatt,fMax,fMin,fSemitonos1,fSemitonos2
                                      ,fSemitonos3,fFavicon);
      fOleo.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.oleo(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fatt.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.att(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fMax.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.maximo(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fMin.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.minimo(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fSemitonos1.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.semitonos1(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fSemitonos2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.semitonos2(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fSemitonos3.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.semitonos3(f.abreImagen(ruta));
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      fFavicon.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          //descargarImg = f.favicon(f.abreImagen(ruta),f.abreImagen("/Users/rodrigorojo/Desktop/ps.png"),0);
          //iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          Stage stageFusion = new Stage();
          GridPane gridpaneFusion = new GridPane();
          stageFusion.setMaxWidth(500);
          stageFusion.setMinWidth(500);

          ImageView ivimg2;
          ivimg2 = new ImageView();
          ivimg2.setFitWidth(250);
          ivimg2.setPreserveRatio(true);
          ivimg2.setSmooth(true);
          ivimg2.setCache(true);

          Button buttonFavicon1 = new Button("Favicon 1");
          Button buttonFavicon2 = new Button("Favicon 2");
          Button buttonFavicon3 = new Button("Favicon 3");
          Button buttonFavicon4 = new Button("Favicon 4");
          Button buttonAbrirImg2 = new Button("Abrir imagen");
          gridpaneFusion.setConstraints(buttonAbrirImg2, 1,1);
          gridpaneFusion.setConstraints(ivimg2, 1,2);
          gridpaneFusion.setConstraints(buttonFavicon1, 1,4,1,1,HPos.LEFT,VPos.CENTER);
          gridpaneFusion.setConstraints(buttonFavicon2, 1,4,1,1,HPos.RIGHT,VPos.CENTER);
          gridpaneFusion.setConstraints(buttonFavicon3, 1,5,1,1,HPos.LEFT,VPos.CENTER);
          gridpaneFusion.setConstraints(buttonFavicon4, 1,5,1,1,HPos.RIGHT,VPos.CENTER);

          gridpaneFusion.getChildren().addAll(buttonAbrirImg2,ivimg2,buttonFavicon1,buttonFavicon2,buttonFavicon3,buttonFavicon4);
          Scene sceneTexto = new Scene(gridpaneFusion, 500, 740);
          stageFusion.setTitle("Abir Segunda Imagen");
          stageFusion.setScene(sceneTexto);
          stageFusion.show();

          buttonFavicon1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              descargarImg = f.favicon(f.abreImagen(ruta),f.abreImagen(ruta2),0);
              iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            }
          });
          buttonFavicon2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              descargarImg = f.favicon(f.abreImagen(ruta),f.abreImagen(ruta2),1);
              iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            }
          });
          buttonFavicon3.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              descargarImg = f.favicon(f.abreImagen(ruta),f.abreImagen(ruta2),2);
              iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            }
          });
          buttonFavicon4.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              descargarImg = f.favicon(f.abreImagen(ruta),f.abreImagen(ruta2),3);
              iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
            }
          });
          buttonAbrirImg2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
              FileChooser fileChooser2 = new FileChooser();
              FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
              FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
              fileChooser2.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
              File file2 = fileChooser2.showOpenDialog(null);
              ruta2 = file2.getPath();
              Image image2;
              try {
                  BufferedImage bufferedImage = ImageIO.read(file2);
                  image2 = SwingFXUtils.toFXImage(bufferedImage, null);
                  ivimg2.setImage(image2);
              } catch (IOException ex) {
              }

            }
          });
        }
      });

      /****************Oleo, AT&T, MaxMin, Semitonos, Favicon*****************/
      /*****************************Descargar********************************/
      descargar.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          f.creaImagen(descargarImg,"IMG-"+cuentaDescargas);
          cuentaDescargas++;
        }
      });
      /*****************************Descargar********************************/
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

      menuBar.getMenus().addAll(archivo, filtrosBasicos, filtrosConvolucion
                                  ,filtrosLetras,filtrosRFMBtn,filtrosOAMSF);

      GridPane gridpane = new GridPane();
      GridPane.setConstraints(menuBar, 1,1);
      GridPane.setConstraints(iv1, 1,2);
      GridPane.setConstraints(iv2, 2,2);
      //gridpane.getChildren().addAll(menuBar, descargar, filtrosBtn,filtrosCBtn,filtrosLBtn,filtrosRFMBtn,iv1,iv2);
      gridpane.getChildren().addAll(menuBar, iv1,iv2);
      Scene scene = new Scene(gridpane, 1000, 693);
      primaryStage.setTitle("Filtros RR");
      primaryStage.setScene(scene);
      primaryStage.show();
  }

  public static void run() {
      launch();
  }


}
