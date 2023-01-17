/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.nsinova.teste.dao;

import br.com.nsinova.teste.modelo.Professor;


/**
 *
 * @author miqueias.nadaluti
 */
public interface IProfessor {

    public int manter(Professor professor) throws Exception;
    public int alterar(Professor professor) throws Exception;
}
