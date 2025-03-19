package co.edu.uniquindio.project.projectapp.controller;

import co.edu.uniquindio.project.projectapp.model.Company;
import co.edu.uniquindio.project.projectapp.model.Project;

import java.util.Collection;

public class ProjectController {
    Company company;

    public ProjectController(Company company) {
        this.company = company;
    }

    public Collection<Project> obtainProjectsList(){
        return company.getProjectsList();
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
}