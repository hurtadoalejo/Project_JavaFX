package co.edu.uniquindio.project.projectapp.viewController;

import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.project.projectapp.App;
import co.edu.uniquindio.project.projectapp.controller.ProjectController;
import co.edu.uniquindio.project.projectapp.model.Project;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javax.swing.*;

public class ProjectViewController {

    ProjectController projectController;
    ObservableList<Project> projectsList = FXCollections.observableArrayList();
    Project selectedProject;

    @FXML
    private App app;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Project> tbl_1;

    @FXML
    private TableColumn<Project, String> cl_name;

    @FXML
    private TextField txt_id;

    @FXML
    private Label lbl_projectMenu;

    @FXML
    private Button bt_deleteProject;

    @FXML
    private TextField txt_name;

    @FXML
    private TableColumn<Project, Integer> cl_id;

    @FXML
    private Button bt_clean;

    @FXML
    private Button bt_updateProject;

    @FXML
    private Button bt_addProject;

    @FXML
    private Label lb_id;

    @FXML
    private Pane pane;

    @FXML
    private Label lb_name;

    @FXML
    void onAddProject() {
        createProject();
    }

    @FXML
    void onUpdateProject() {
        updateProject();
    }

    @FXML
    void onDeleteProject() {
        deleteProject();
    }

    @FXML
    void onClean() {
        cleanSelection();
    }

    private Project buildProject(){
        return new Project(txt_name.getText(), Integer.parseInt(txt_id.getText()));
    }

    private void createProject(){
        if (verifyFilledFields() && verifyValidFields()){
            if (projectController.createProject(txt_name.getText(), Integer.parseInt(txt_id.getText()))){
                Project project = projectController.obtainProject(Integer.parseInt(txt_id.getText()));
                projectsList.add(project);
                cleanProjectFields();
                JOptionPane.showMessageDialog(null, "The project has been created successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "The project hasn't been created successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void deleteProject(){
        if (!txt_id.getText().isEmpty() && isInteger(txt_id.getText())){
            Project project = projectController.obtainProject(Integer.parseInt(txt_id.getText()));
            if (projectController.deleteProject(Integer.parseInt(txt_id.getText()))){
                projectsList.remove(project);
                cleanProjectFields();
                JOptionPane.showMessageDialog(null, "The project has been deleted successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "The project hasn't been deleted successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private void updateProject(){
        if (verifyValidFields() && verifyFilledFields()){
            if (selectedProject != null && projectController.updateProject(selectedProject.getCode(),
                    txt_name.getText(), Integer.parseInt(txt_id.getText()))){
                tbl_1.refresh();
                cleanSelection();
                JOptionPane.showMessageDialog(null, "The project has been updated successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(null, "The project hasn't been updated successfully", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    private boolean verifyFilledFields(){
        boolean filled = false;
        if (!txt_name.getText().isEmpty() && !txt_id.getText().isEmpty()){
            filled = true;
        }
        return  filled;
    }

    private boolean verifyValidFields(){
        boolean valid = false;
        if (isInteger(txt_id.getText())){
            valid = true;
        }
        return  valid;
    }

    private boolean isInteger(String text){
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showProjectInformation(Project project){
        if (project != null) {
            txt_name.setText(project.getName());
            txt_id.setText(String.valueOf(project.getCode()));
        }
    }

    private void obtainProjects(){
        projectsList.addAll(projectController.obtainProjectsList());
    }

    private void initView() {
        initDataBinding();
        obtainProjects();
        tbl_1.getItems().clear();
        tbl_1.setItems(projectsList);
        listenerSelection();
    }

    private void initDataBinding() {
        cl_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        cl_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCode()).asObject());
    }

    private void listenerSelection(){
        tbl_1.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            selectedProject = newSelection;
            showProjectInformation(selectedProject);
        });
    }

    private void cleanSelection(){
        tbl_1.getSelectionModel().clearSelection();
        cleanProjectFields();
    }

    private void cleanProjectFields(){
        txt_id.clear();
        txt_name.clear();
    }

    public void setApp(App app){
        this.app = app;
    }

    @FXML
    void initialize() {
        projectController = new ProjectController(App.modelFactory);
        initView();
        assert tbl_1 != null : "fx:id=\"tbl_1\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert cl_name != null : "fx:id=\"cl_name\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert txt_id != null : "fx:id=\"txt_id\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert lbl_projectMenu != null : "fx:id=\"lbl_projectMenu\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert bt_deleteProject != null : "fx:id=\"bt_deleteProject\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert txt_name != null : "fx:id=\"txt_name\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert cl_id != null : "fx:id=\"cl_id\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert bt_clean != null : "fx:id=\"bt_clean\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert bt_updateProject != null : "fx:id=\"bt_updateProject\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert bt_addProject != null : "fx:id=\"bt_addProject\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert lb_id != null : "fx:id=\"lb_id\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'projectMenu.fxml'.";
        assert lb_name != null : "fx:id=\"lb_name\" was not injected: check your FXML file 'projectMenu.fxml'.";

    }
}