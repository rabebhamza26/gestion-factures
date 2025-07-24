/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package controllers;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
    public interface IDAO <T>{
    public ArrayList<T> getAll();
    public boolean  create(T o);
    public boolean  update(T o, int id);
    public boolean  delete(int id);
    
}
    

