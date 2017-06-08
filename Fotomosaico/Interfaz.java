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
      /******************************Fotomosaico*********************************/
      Menu filtrosBasicos = new Menu("Fotomosaico");
      MenuItem f1 = new MenuItem("1 - Cear con Linenal");
      MenuItem f2 = new MenuItem("2 - Cear con Euclidiana");
      MenuItem f3 = new MenuItem("3 - Cear con Rimersma");
      MenuItem f4 = new MenuItem("4 - Cear con Restas");
      filtrosBasicos.getItems().addAll(f1,f2,f3,f4);
      f1.setAccelerator(KeyCombination.keyCombination("Shortcut+1"));
      f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(f.abreImagen(ruta),"LINEAL");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f2.setAccelerator(KeyCombination.keyCombination("Shortcut+2"));
      f2.setOnAction(new EventHandler<ActionEvent>() {
        public void handle(ActionEvent t) {
          descargarImg = f.fotomosaico(f.abreImagen(ruta),"EUCLIDIANA");
          iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
        }
      });
      f3.setAccelerator(KeyCombination.keyCombination("Shortcut+3"));
      f3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(f.abreImagen(ruta),"RIMERSMA");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      f4.setAccelerator(KeyCombination.keyCombination("Shortcut+4"));
      f4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            descargarImg = f.fotomosaico(f.abreImagen(ruta),"RESTAS");
            iv2.setImage(SwingFXUtils.toFXImage(descargarImg, null));
          }
      });
      /******************************Fotomosaico*********************************/
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
