/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GIGABYTE
 */
public class Demande_Suivi {
    private int id;
    private String demande_suivi;
    private int id_suivi;
    private int id_user;
    private int id_entrainee;
    private int id_coach;

    public Demande_Suivi(String demande_suivi, int id_suivi, int id_user, int id_entrainee, int id_coach) {
        this.demande_suivi = demande_suivi;
        this.id_suivi = id_suivi;
        this.id_user = id_user;
        this.id_entrainee = id_entrainee;
        this.id_coach = id_coach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDemande_suivi() {
        return demande_suivi;
    }

    public void setDemande_suivi(String demande_suivi) {
        this.demande_suivi = demande_suivi;
    }

    public int getId_suivi() {
        return id_suivi;
    }

    public void setId_suivi(int id_suivi) {
        this.id_suivi = id_suivi;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_entrainee() {
        return id_entrainee;
    }

    public void setId_entrainee(int id_entrainee) {
        this.id_entrainee = id_entrainee;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    @Override
    public String toString() {
        return "Demande_Suivi{" + "demande_suivi=" + demande_suivi + '}';
    }
    
    
    
}
