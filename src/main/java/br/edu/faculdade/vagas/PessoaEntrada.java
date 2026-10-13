package br.edu.faculdade.vagas;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PessoaEntrada(
        @NotBlank String nome,
        @NotBlank @Email String email,
        String area,
        String senioridade
) {
}
