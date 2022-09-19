/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import model.LignePanier;

/**
 *
 * @author Anis-PC
 */
public interface ILignePanier {
    public void AjoutLignePanier();
   public LignePanier queryByIdProd (int id);
  
}
