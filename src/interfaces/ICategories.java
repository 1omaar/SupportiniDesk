/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import model.Categories;

/**
 *
 * @author Anis-PC
 */
public interface ICategories {
  



    void insertcat(Categories st);

    Categories findcatById(int id);

    Categories findcatBynom(String nom);


}

    

