/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elife-Kef-130
 */
public class Utilisateur {
    private int id,idRole;
    private String nom, prenom , password,email,cin;
    //constructeur

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String password, String email,String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin=cin;
        
    }

    public Utilisateur(int id, String nom, String prenom, String password, String email,String cin , int idRole) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.email = email;
        this.cin=cin;
        this.idRole=idRole;
    }
    //getters & setters

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getId() {
        return id;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", cin=" + cin + '}';
    }

    
  

 
    
    
}
