package com.projeto.gerenciadorFuncionarios.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.projeto.gerenciadorFuncionarios.model.Funcionario;
import com.projeto.gerenciadorFuncionarios.model.repositorio.FuncionarioRepositorio;

@Controller
public class FuncionarioController {

    @Autowired
    private FuncionarioRepositorio funcRepositorio;

    // Acessa o formulario
    @GetMapping("/form")
    public String funcionariosForm(Funcionario funcionario) {

        return "addFuncionario";
    }

    // Adiciona novo funcionario
    @PostMapping("/add")
    public String novo(@Valid Funcionario funcionario, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "redirect:/form";
        }
        funcRepositorio.save(funcionario);

        return "redirect:/home";
    }

    // Acessa o formulario de edição
    @GetMapping("form/{id}")
    public String updateForm(Model model, @PathVariable(name = "id") int id) {

        Funcionario funcionario = funcRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("funcionario", funcionario);
        return "atualizarForm";
    }

    // Atualiza funcionario
    @PostMapping("update/{id}")
    public String alterarProduto(@Valid Funcionario funcionario, BindingResult result, @PathVariable int id) {

        if (result.hasErrors()) {
            return "redirect:/form";
        }

        funcRepositorio.save(funcionario);
        return "redirect:/home";
    }

    @GetMapping("delete/{id}")
    @CacheEvict(value = "funcionarios", allEntries = true)
    public String delete(@PathVariable(name = "id") int id, Model model) {

        Funcionario funcionario = funcRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        funcRepositorio.delete(funcionario);
        return "redirect:/home";
    }

}

