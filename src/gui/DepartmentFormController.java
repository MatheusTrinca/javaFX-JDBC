package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Label labelErrorName;
	@FXML
	private Button btSave;
	@FXML
	private Button btCancel;
	
	private Department entity;
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	@FXML
	private void onBTSaveAction() {
		System.out.println("onBTSaveAction");
	}
	@FXML
	private void onBTCancelAction() {
		System.out.println("onBTCancelAction");
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
		
	}
	
	private void initializeNodes(){
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData() {
		if(entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		// converter de inteiro para String, para passar para a caixa de texto
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}
	

	

}
