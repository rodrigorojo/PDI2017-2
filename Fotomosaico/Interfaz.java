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
    int cuentaDescargas = 1;
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
      /******************************Basicos*********************************/
      Menu filtrosBasicos = new Menu("Fotomosaico");
      MenuItem f1 = new MenuItem("1 - Cear");
      MenuItem f2 = new MenuItem("2 - Mosaico");
      f1.setAccelerator(KeyCombination.keyCombination("Shortcut+P"));
      /*Borrar*/
      MenuItem f3 = new MenuItem("3 - Pruebas");
      f3.setAccelerator(KeyCombination.keyCombination("Shortcut+K"));
      f3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.escogeImagen(106,79,70);
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      /*Borrar*/
      filtrosBasicos.getItems().addAll(f1,f2,f3);
      f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(f.abreImagen(ruta));
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f2.setOnAction(new EventHandler<ActionEvent>() {
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

      menuBar.getMenus().addAll(archivo, filtrosBasicos);

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
