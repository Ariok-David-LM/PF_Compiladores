/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package fes.aragon.ui.controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

import fes.aragon.compilador.Principal;

/**
 * FXML Controller class
 *
 * @author ariok
 */
public class EditorController implements Initializable {

    @FXML
    private TextArea codFuente;
    @FXML
    private MenuItem codIntermedio;
    @FXML
    private Pane VentanaEditor;
    @FXML
    private Pane ventanaInter;
    @FXML
    private TextArea codInter;
    @FXML
    private Menu insert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void codInter(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "1. Si observa un 'null' o algo diferente puede que\n   no compilo antes o su codigo contiene errores.");
        VentanaEditor.setVisible(false);
        ventanaInter.setVisible(true);
        codIntermedio.setDisable(true);
        insert.setDisable(true);
        String texto = "";
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + "/salida.fes");
            BufferedReader buff = new BufferedReader(fr);
            String cad;
            while ((cad = buff.readLine()) != null) {
                texto += cad + "\n";
            }
            buff.close();
            fr.close();
            codInter.setText(texto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void instrucciones(ActionEvent event) {
        JOptionPane.showMessageDialog(null, "Reglas:\n1.Las instrucciones como:\n    a.coloca numero numero\n    b.mover numero\n    c.arriba\n    d.abajo\n    e.izquierda\n    f.derecha\n  **llevan al final un ';'\n  **'numero' debera sustituirse por un numero decimal como '10' por ejemplo.\n2. Existen dos ciclos:\n    a.repetir numero{ instrucciones }\n    b.ver fruta{ instrucciones }\n    **'numero' debera sustituirse por un numero decimal como '10' por ejemplo.\n3. No puedes meter en las intrucciones del ciclo repetir un ciclo ver;\n   de igual forma no puedes meter dentro de un ciclo ver un cliclo repetir.");
    }

    @FXML
    private void limpiarFuente(ActionEvent event) {
        codFuente.setText("");
    }

    @FXML
    private void compilarFuente(ActionEvent event) {
        try {
            FileWriter fw = new FileWriter(new File(System.getProperty("user.dir") + "/src/fes/aragon/compilador/Fuente.txt"));
            fw.write(codFuente.getText());
            fw.close();
            Principal.main(null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void regresarAFuente(ActionEvent event) {
        VentanaEditor.setVisible(true);
        ventanaInter.setVisible(false);
        codIntermedio.setDisable(false);
        insert.setDisable(false);
    }

    @FXML
    private void arriba(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\narriba;";
        codFuente.setText(codigo);
    }

    @FXML
    private void abajo(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nabajo;";
        codFuente.setText(codigo);
    }

    @FXML
    private void izquierda(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nizquierda;";
        codFuente.setText(codigo);
    }

    @FXML
    private void derecha(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nderecha;";
        codFuente.setText(codigo);
    }

    @FXML
    private void mover(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nmover n;";
        codFuente.setText(codigo);
    }

    @FXML
    private void coloca(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\ncoloca n n;";
        codFuente.setText(codigo);
    }

    @FXML
    private void repetir(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nrepetir n{\n\n}";
        codFuente.setText(codigo);
    }

    @FXML
    private void verFruta(ActionEvent event) {
        String codigo = codFuente.getText();
        codigo += "\nver fruta{\n\n}";
        codFuente.setText(codigo);
    }

}
