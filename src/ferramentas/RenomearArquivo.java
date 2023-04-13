package ferramentas;

import controller.AtualizadorController;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;

public class RenomearArquivo {
    static boolean renomeado = false;
    public static void renomearArquivo(File file, File oldFile, AtualizadorController atualizadorController){

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i = 0; i < 5; i++){
                    final double progress = i+1;
                    Thread.sleep(1000);
                    Platform.runLater(()->{
                        atualizadorController.progressBarCopiaAtualizacao.setProgress(progress);
                        atualizadorController.labelAtualizacao.setText("Finalizando Atualização...");
                    });
                }

                File newFile = new File(oldFile.getName());
                renomeado = file.renameTo(newFile);

                Platform.runLater(()-> {
                    atualizadorController.buttonFinalizar.setDisable(false);
                    atualizadorController.labelAtualizacao.setText("Atualização finalizada com sucesso!");
                });

                Thread.sleep(1000);

                if(renomeado) {
                    String exec = newFile.getName();
                    Runtime.getRuntime().exec(exec);
                }

                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }
}
