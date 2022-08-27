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
public class Entrainee {

    private int id, fk_idUser,age, taille, poids;
    private String sexe;

    public Entrainee() {
    }

    public Entrainee(int fk_idUser, int age, int taille, int poids,  String sexe) {
        this.fk_idUser = fk_idUser;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
       
        this.sexe = sexe;
    }

    public Entrainee(int id, int fk_idUser, int age, int taille, int poids,  String sexe) {
        this.id = id;
        this.fk_idUser = fk_idUser;
        this.age = age;
        this.taille = taille;
        this.poids = poids;
       
        this.sexe = sexe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFk_idUser() {
        return fk_idUser;
    }

    public void setFk_idUser(int fk_idUser) {
        this.fk_idUser = fk_idUser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }



    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

   

    

    

   

  

    @Override
    public String toString() {
        return "Entrainee{" + "id=" + id + ", fk_idUser=" + fk_idUser + ", age=" + age + ", taille=" + taille + ", poids=" + poids + ", sexe=" + sexe + '}';
    }

}
