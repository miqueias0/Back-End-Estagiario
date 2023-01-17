/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.nsinova.teste.dao;

import br.com.nsinova.teste.modelo.Aluno;

/**
 *
 * @author miqueias.nadaluti
 */
public interface IAluno {
    
    public int manter(Aluno aluno) throws Exception;
    public int alterar(Aluno aluno) throws Exception;
}
