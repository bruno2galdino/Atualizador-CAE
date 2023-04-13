package ferramentas;

import controller.AtualizadorController;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopiaArquivos {
    static long position = 0;
    public static void copiarArquivo(File sourceFile, File deprecatedFile,AtualizadorController atualizador){
        File destinationFile = new File("copia_" + sourceFile.getName());
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() {
                try(FileChannel source = new FileInputStream(sourceFile).getChannel();
                    FileChannel destination = new FileOutputStream(destinationFile).getChannel();){

                    long size = source.size();

                    long count = 0;

                    while((count = destination.transferFrom(source, position, 1024 * 1024)) > 0){
                        position += count;
                        final double progress = (double) position / size;
                        Platform.runLater(()-> {
                            atualizador.progressBarCopiaAtualizacao.setProgress(progress);
                            atualizador.labelAtualizacao.setText(String.format("Copiando atualização.: %.0f%%", progress * 100));
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    Platform.runLater(()-> {
                        atualizador.labelAtualizacao.setText("Atualização copiada 100%");
                        atualizador.progressBarCopiaAtualizacao.setProgress(0);
                    });

                    Thread.sleep(800);

                    DeletarArquivo.deletaArquivo(deprecatedFile, destinationFile, atualizador);

                } catch (IOException ex){
                    ex.printStackTrace();
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        };

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }
}
