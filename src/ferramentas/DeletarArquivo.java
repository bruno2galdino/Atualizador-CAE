package ferramentas;

import controller.AtualizadorController;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;

public class DeletarArquivo {
    public static void deletaArquivo(File file, File copyFile, AtualizadorController atualizadorController){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i = 0; i < 5; i++){
                    final double progress = (i+1)*2;
                    Thread.sleep(1000);
                    System.out.println(progress);
                    Platform.runLater(()->{
                        atualizadorController.progressBarCopiaAtualizacao.setProgress(progress);
                        atualizadorController.labelAtualizacao.setText("Removendo versão desatualizada...");
                    });
                }
                if(file.delete()){
                    atualizadorController.progressBarCopiaAtualizacao.setProgress(0);
                    RenomearArquivo.renomearArquivo(copyFile, file, atualizadorController);
                }
                return null;
            }
        };
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}
