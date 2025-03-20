package co.edu.uniquindio.project.projectapp.factory;

import co.edu.uniquindio.project.projectapp.model.Company;
import co.edu.uniquindio.project.projectapp.model.Project;

import java.util.Collection;

public class ModelFactory {
    private static ModelFactory instance;
    private Company company;

    private ModelFactory() {
        initializeData();
    }

    private void initializeData(){
        company = new Company("Compañia");
        company.createProject("Primero", 1);
        company.createProject("Segundo", 2);
    }

    public Company getCompany() {
        return company;
    }

    public static ModelFactory getInstance() {
        if (instance == null){
            instance = new ModelFactory();
        }
        return instance;
    }

    public boolean createProject(String name, int code){
        return company.createProject(name, code);
    }

    public boolean deleteProject(int code){
        return company.deleteProject(code);
    }

    public boolean updateProject(int oldCode, String name, int code){
        return company.updateProject(oldCode, name, code);
    }

    public Collection<Project> obtainProjects(){
        return company.getProjectsList();
    }

    public Project obtainProject(int code){
        return company.obtainProject(code);
    }
}