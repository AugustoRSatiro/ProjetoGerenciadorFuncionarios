package com.projeto.gerenciadorFuncionarios.model;
public enum FuncionarioSetor {
    SUPORTEN1("suporteN1"),
    SUPORTEN2("suporteN2"),
    DIRETORIA("diretoria"),
    RH("rh");

    private String value;

    private FuncionarioSetor(String value) {
        this.value = value;
    }
    public String getSetor() {
        return value;
    }

}

