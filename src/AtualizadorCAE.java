import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AtualizadorCAE extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = new AnchorPane();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/telas/FXMLAtualizador.fxml"));
        root.getChildren().add(loader.load());

        final Scene scene = new Scene(root, 600, 189);
        primaryStage.setScene(scene);

        primaryStage.setMaximized(false);
        primaryStage.setTitle("Atualizador CAE - Construverso");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(225);
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(225);

        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();
    }
}
