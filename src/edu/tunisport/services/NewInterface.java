/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;

import java.util.List;

/**
 *
 * @author kadri younes
 */
public interface NewInterface<T> {
        public T read(int id);
        
    public void update(T t);
    public void ajouter(T t);
    public List<T> afficher();
    public void supprimer(T t);

}
