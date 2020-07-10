package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		System.out.println("onMenuItemDepartmentAction");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();
			
			// Pegar toda a cena principal
			Scene mainScene = Main.getMainScene();
					
			// Pegar o elemento da view principal
			// Fazer casting para Scroll Pane -> esse o elemento principal
			// Pegar o content com getContent
			// Por fim fazer o casting de tudo para o VBox
			VBox mainVbox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// Pegar referencia do menu da janela principal -> Usar tipo Node
			// getChildren() pega todos os filhos, depois usar -> get(0) pega o primeiro filho(só tem 1)
			Node mainMenu = mainVbox.getChildren().get(0); 
			// Pegar e Apagar todos os filhos da VBox principal
			mainVbox.getChildren().clear();
			// Incluir novamente o menu
			mainVbox.getChildren().add(mainMenu);
			// Incluir os filhos da VBox do About
			mainVbox.getChildren().addAll(newVbox.getChildren());
			
			
		}catch(IOException e) {
			Alerts.showAlert("IOExeption", "Error", e.getMessage(), AlertType.ERROR);
		}
	}
	
	
}
