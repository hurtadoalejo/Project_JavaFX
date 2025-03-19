package co.edu.uniquindio.project.projectapp.model;

import java.util.LinkedList;

public class Company {
    private String name;
    LinkedList<Project> projectsList = new LinkedList<>();

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Project> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(LinkedList<Project> projectsList) {
        this.projectsList = projectsList;
    }

    public boolean createProject(String name, int code){
        Project project = obtainProject(code);
        if (project == null){
            projectsList.add(new Project(name, code));
            return true;
        }
        return false;
    }

    public Project obtainProject(int code){
        Project project = null;
        for (Project temporalProject : projectsList){
            if (temporalProject.getCode() == code){
                return project = temporalProject;
            }
        }
        return null;
    }

    public boolean deleteProject (int code){
        Project project = obtainProject(code);
        if (project != null){
            projectsList.remove(project);
            return true;
        }
        return false;
    }

    public boolean updateProject(int oldCode, String name, int code){
        Project project = obtainProject(oldCode);
        Project possibleProject = obtainProject(code);
        if (project != null){
            if (oldCode == code || possibleProject == null){
                project.setName(name);
                project.setCode(code);
                return true;
            }
        }
        return false;
    }
}