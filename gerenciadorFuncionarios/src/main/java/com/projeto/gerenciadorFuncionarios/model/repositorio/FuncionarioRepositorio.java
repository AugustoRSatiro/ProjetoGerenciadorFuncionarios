package com.projeto.gerenciadorFuncionarios.model.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.gerenciadorFuncionarios.model.FuncionarioSetor;
import com.projeto.gerenciadorFuncionarios.model.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer> {
    List<Funcionario> findBySetor(FuncionarioSetor funcionarioSetor);
}

