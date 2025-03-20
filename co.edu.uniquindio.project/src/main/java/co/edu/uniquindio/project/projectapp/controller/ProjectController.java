package co.edu.uniquindio.project.projectapp.controller;

import co.edu.uniquindio.project.projectapp.factory.ModelFactory;
import co.edu.uniquindio.project.projectapp.model.Company;
import co.edu.uniquindio.project.projectapp.model.Project;

import java.util.Collection;

public class ProjectController {
    ModelFactory modelFactory;

    public ProjectController(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
    }

    public Collection<Project> obtainProjectsList(){
        return modelFactory.obtainProjects();
    }

    public Project obtainProject(int code){
        return modelFactory.obtainProject(code);
    }

    public boolean createProject(String name, int code){
        return modelFactory.createProject(name, code);
    }

    public boolean deleteProject(int code){
        return modelFactory.deleteProject(code);
    }

    public boolean updateProject(int oldCode, String name, int code){
        return modelFactory.updateProject(oldCode, name, code);
    }
}