/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Asus
 */
public class Coach {
    private int id ,idUser, idPlanning;
    private String specialite;

    public Coach() {
    }

    public Coach(int idUser, int idPlanning, String specialite) {
        this.idUser = idUser;
        this.idPlanning = idPlanning;
        this.specialite = specialite;
    }

    public Coach(int id, int idUser, int idPlanning, String specialite) {
        this.id = id;
        this.idUser = idUser;
        this.idPlanning = idPlanning;
        this.specialite = specialite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(int idPlanning) {
        this.idPlanning = idPlanning;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "Coach{" + "id=" + id + ", idUser=" + idUser + ", idPlanning=" + idPlanning + ", specialite=" + specialite + '}';
    }
    
}
