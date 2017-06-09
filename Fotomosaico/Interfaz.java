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
import javafx.stage.DirectoryChooser;
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
    BufferedImage blendingImg;
    int cuentaDescargas = 1;
    int ancho = 50;
    int alto = 50;
    //TEmp
    Image image;

    @Override
    public void start(Stage primaryStage) {
      MenuBar menuBar = new MenuBar();
      /*************************Abrir y Descargar******************************/
      Menu archivo = new Menu("Archivo");
      MenuItem abrir = new MenuItem("Abrir Imagen");
      MenuItem descargar = new MenuItem("Descargar");
      MenuItem cargarImagenes = new MenuItem("Cargar Imagenes");
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
      descargar.setAccelerator(KeyCombination.keyCombination("Shortcut+D"));
      descargar.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            f.creaImagen(descargarImg,"IMG-"+cuentaDescargas);
            cuentaDescargas++;
          }
      });
      cargarImagenes.setAccelerator(KeyCombination.keyCombination("Shortcut+L"));
      cargarImagenes.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            DirectoryChooser dc = new DirectoryChooser();
            File archivoSeleccionado = dc.showDialog(primaryStage);
            ruta = archivoSeleccionado.getPath();
            String promedios = f.cargarImagenes(ruta);

            f.archivoPromedio(promedios);

            System.out.println("TERMINADO");
          }
      });
      archivo.getItems().addAll(abrir, descargar,cargarImagenes);
      /*************************Abrir y Descargar******************************/
      /******************************Fotomosaico*********************************/
      Menu filtrosBasicos = new Menu("Fotomosaico");
      MenuItem ft = new MenuItem("Cambiar Tamaño del Cuadro");
      MenuItem f1 = new MenuItem("1 - Lineal con repetición");
      MenuItem f2 = new MenuItem("2 - Euclidiana con repetición");
      MenuItem f3 = new MenuItem("3 - Riemersma con repetición");
      MenuItem f4 = new MenuItem("4 - Restas con repetición");
      MenuItem f5 = new MenuItem("5 - Lineal sin repetición");
      MenuItem f6 = new MenuItem("6 - Euclidiana sin repetición");
      MenuItem f7 = new MenuItem("7 - Riemersma sin repetición");
      MenuItem f8 = new MenuItem("8 - Restas sin repetición");
      filtrosBasicos.getItems().addAll(ft,f1,f2,f3,f4,f5,f6,f7,f8);
      ft.setAccelerator(KeyCombination.keyCombination("Shortcut+T"));
      ft.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            Stage stageMosaico = new Stage();
            int maxMosaico = (int) image.getWidth()/5;
            GridPane gridpaneMosaico = new GridPane();
            ColumnConstraints col1 = new ColumnConstraints();
            ColumnConstraints col2 = new ColumnConstraints();
            col2.setMinWidth(400);
            gridpaneMosaico.getColumnConstraints().addAll(col1,col2);
            Label labelAncho = new Label("Ancho");
            Slider sliderAncho = new Slider(1, maxMosaico, ancho);
            Label sliderAnchoValor = new Label(ancho+"");
            Label labelAlto = new Label("Alto");
            Slider sliderAlto = new Slider(1, maxMosaico, alto);
            Label sliderAltoValor = new Label(alto+"");
            Button buttonMosaico = new Button("Cambiar tamaño");
            gridpaneMosaico.setConstraints(labelAncho, 1,1);
            gridpaneMosaico.setConstraints(sliderAncho, 1,2);
            gridpaneMosaico.setConstraints(sliderAnchoValor, 2,2);
            gridpaneMosaico.setConstraints(labelAlto, 1,3);
            gridpaneMosaico.setConstraints(sliderAlto, 1,4);
            gridpaneMosaico.setConstraints(sliderAltoValor, 2,4);
            gridpaneMosaico.setConstraints(buttonMosaico, 1,5);
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
                ancho = (int)sliderAncho.getValue();
                alto = (int)sliderAlto.getValue();
              }
            });
          }
      });
      f1.setAccelerator(KeyCombination.keyCombination("Shortcut+1"));
      f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),true,"LINEAL");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f2.setAccelerator(KeyCombination.keyCombination("Shortcut+2"));
      f2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),true,"EUCLIDIANA");
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      f3.setAccelerator(KeyCombination.keyCombination("Shortcut+3"));
      f3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),true,"RIEMERSMA");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f4.setAccelerator(KeyCombination.keyCombination("Shortcut+4"));
      f4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),true,"RESTAS");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f5.setAccelerator(KeyCombination.keyCombination("Shortcut+5"));
      f5.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),false,"LINEAL");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f6.setAccelerator(KeyCombination.keyCombination("Shortcut+6"));
      f6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),false,"EUCLIDIANA");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f7.setAccelerator(KeyCombination.keyCombination("Shortcut+7"));
      f7.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),false,"RIEMERSMA");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f8.setAccelerator(KeyCombination.keyCombination("Shortcut+8"));
      f8.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(ancho,alto,f.abreImagen(ruta),false,"RESTAS");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      /******************************Fotomosaico*********************************/
      /********************************Blending**********************************/
      Menu blending = new Menu("Blending");
      MenuItem b1 = new MenuItem("Aplicar");
      blending.getItems().addAll(b1);
      b1.setAccelerator(KeyCombination.keyCombination("Shortcut+B"));
      b1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            Stage stageFusion = new Stage();
            GridPane gridpaneFusion = new GridPane();

            Slider sliderFusion = new Slider(0, 100, 70);
            Label sliderFusionValor = new Label("70");

            Button buttonFusionar = new Button("Fusionar");
            gridpaneFusion.setConstraints(sliderFusion, 1,1);
            gridpaneFusion.setConstraints(sliderFusionValor, 1,4,1,1,HPos.CENTER,VPos.CENTER);
            gridpaneFusion.setConstraints(buttonFusionar, 1,5,1,1,HPos.RIGHT,VPos.CENTER);

            gridpaneFusion.getChildren().addAll(buttonFusionar,sliderFusion,sliderFusionValor);
            Scene sceneTexto = new Scene(gridpaneFusion, 150, 75);
            stageFusion.setTitle("Blending");
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
                blendingImg = f.fusion(descargarImg,f.abreImagen(ruta), (int)sliderFusion.getValue(), 100-(int)sliderFusion.getValue());
                iv2.setImage(SwingFXUtils.toFXImage(blendingImg, null));
              }
            });
          }
      });
      /********************************Blending**********************************/
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

      menuBar.getMenus().addAll(archivo, filtrosBasicos, blending);

      GridPane gridpane = new GridPane();
      GridPane.setConstraints(menuBar, 1,1);
      GridPane.setConstraints(iv1, 1,2);
      GridPane.setConstraints(iv2, 2,2);
      //gridpane.getChildren().addAll(menuBar, descargar, filtrosBtn,filtrosCBtn,filtrosLBtn,filtrosRFMBtn,iv1,iv2);
      gridpane.getChildren().addAll(menuBar, iv1,iv2);
      Scene scene = new Scene(gridpane, 1000, 693);
      primaryStage.setTitle("Fotomosaico RR");
      primaryStage.setScene(scene);
      primaryStage.show();
  }

  public static void run() {
      launch();
  }


}
