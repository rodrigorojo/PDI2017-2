import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.imageio.ImageIO;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

/**
 *
 */
public class Interfaz extends Application {

    ImageView iv1;
    ImageView iv2;
    Filtros f = new Filtros();
    String ruta;
    //TEmp
    Image image;

    @Override
    public void start(Stage primaryStage) {

        Button abrirImg = new Button("Abrir Imagen");
        abrirImg.setOnAction(btnLoadEventListener);

        SplitMenuButton filtrosBtn = new SplitMenuButton();
        MenuItem f1   = new MenuItem("1 - Aleatorio");
        MenuItem f2_1 = new MenuItem("2.1 - Solo Rojo");
        MenuItem f2_2 = new MenuItem("2.2 - Solo Verde");
        MenuItem f2_3 = new MenuItem("2.3 - Solo Azul");
        MenuItem f3_1 = new MenuItem("3.1 - Aleatorio Sin Rojo");
        MenuItem f3_2 = new MenuItem("3.2 - Aleatorio Sin Verde");
        MenuItem f3_3 = new MenuItem("3.3 - Aleatorio Sin Azul");
        MenuItem f3_4 = new MenuItem("3.4 - Imagen Sin Rojo");
        MenuItem f3_5 = new MenuItem("3.5 - Imagen Sin Verde");
        MenuItem f3_6 = new MenuItem("3.6 - Imagen Sin Azul");
        MenuItem f4_1 = new MenuItem("4.1 - Tonos de gris (Suma/3)");
        MenuItem f4_2 = new MenuItem("4.2 - Tonos de gris (Formula FALTA)");
        MenuItem f5_1 = new MenuItem("5.1 - Tonos de gris (Sin Rojo)");
        MenuItem f5_2 = new MenuItem("5.2 - Tonos de gris (Sin Verde)");
        MenuItem f5_3 = new MenuItem("5.3 - Tonos de gris (Sin Azul)");
        MenuItem f6   = new MenuItem("6 - Alto Contraste");
        MenuItem f7   = new MenuItem("7 - Inverso");
        //Falta Barra para brillo y mosaico
        MenuItem f8   = new MenuItem("8 - Brillo");
        MenuItem f9   = new MenuItem("9 - Mosaico");
        filtrosBtn.setText("Filtros");
        filtrosBtn.getItems().addAll(f1,f2_1,f2_2,f2_3,f3_1,f3_2,f3_3,f3_4,f3_5,f3_6,f4_1,f4_2,
                                    f5_1,f5_2,f5_3,f6,f7,f8,f9);
        f1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.randomRGB(900,800), null));
          }
        });
        f2_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.unColor(255,0,0,900,800), null));
          }
        });
        f2_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.unColor(0,255,0,900,800), null));
          }
        });
        f2_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.unColor(0,0,255,900,800), null));
          }
        });
        f3_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponenteRGB(true,false,false,900,800), null));
          }
        });
        f3_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponenteRGB(false,true,false,900,800), null));
          }
        });
        f3_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponenteRGB(false,false,true,900,800), null));
          }
        });
        f3_4.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponente(true,false,false,ruta), null));
          }
        });
        f3_5.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponente(false,true,false,ruta), null));
          }
        });
        f3_6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.sinUnComponente(false,false,true,ruta), null));
          }
        });
        f4_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.escalaDeGrisesA(ruta), null));
          }
        });
        f4_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            System.out.println("LOL");
            //iv2.setImage(SwingFXUtils.toFXImage(, null));
          }
        });
        f5_1.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.escalaDeGrisesB(true,false,false,ruta), null));
          }
        });
        f5_2.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.escalaDeGrisesB(false,true,false,ruta), null));
          }
        });
        f5_3.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.escalaDeGrisesB(false,false,true,ruta), null));
          }
        });
        f6.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.altoContraste(ruta), null));
          }
        });
        f7.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.inverso(ruta), null));
          }
        });
        f8.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.brillo(100,ruta), null));
          }
        });
        f9.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent t) {
            iv2.setImage(SwingFXUtils.toFXImage(f.mosaico(25,25,ruta), null));
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

        VBox rootBox = new VBox();
        rootBox.getChildren().addAll(abrirImg, filtrosBtn, iv1, iv2);

        Scene scene = new Scene(rootBox, 500, 800);

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
            //Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    };
}
