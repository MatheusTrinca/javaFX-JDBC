package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem menuItemSeller;
	@FXML
	private MenuItem menuItemDepartment;
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}
	
	public synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
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
			
			// Ativar initializingAction -> executa qualquer função passada no parâmetro
			// Para não precisar carregar outras vezes a mesma funcao
			T controller = loader.getController();
			initializingAction.accept(controller);
			
			
		}catch(IOException e) {
			Alerts.showAlert("IOExeption", "Error", e.getMessage(), AlertType.ERROR);
		}
	}

	
	
}
