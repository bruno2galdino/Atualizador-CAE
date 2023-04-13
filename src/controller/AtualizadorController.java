/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import ferramentas.CopiaArquivos;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * FXML Controller class
 *
 * @author brunogaldino
 */
public class AtualizadorController implements Initializable {
    @FXML
    public Label labelAtualizacao;

    @FXML
    public Button buttonFinalizar;

    @FXML
    public ProgressBar progressBarCopiaAtualizacao;

    @FXML
    private Label labelVersao;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonFinalizar.setDisable(true);
        File dir = new File("\\\\192.168.1.51\\caetano\\ARQUIVOS DE REDE\\SOFTWARES\\AtualizacaoCAE\\");
        File arquivo = new File(dir+"\\GetCenter.exe");
        File deprecatedFile = new File("GetCenter.exe");
        CopiaArquivos.copiarArquivo(arquivo, deprecatedFile,AtualizadorController.this);

        buttonFinalizar.setOnAction(eh->{
            System.exit(0);
        });
    }    
    
}
