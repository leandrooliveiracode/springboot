package br.edu.faculdade.vagas;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * O que o cliente MANDA. Sem id: quem inventa o id é a API, e agora isso
 * não é uma regra escrita em lugar nenhum — é a ausência de um campo.
 */
public record VagaEntrada(

    @NotBlank(message = "o título é obrigatório")
    @Size(max = 120, message = "o título passa de 120 caracteres")
    String titulo,

    @NotBlank(message = "a área é obrigatória")
    String area,

    @NotBlank(message = "a senioridade é obrigatória")
    String senioridade,

    @NotBlank(message = "a empresa é obrigatória")
    String empresaSlug,

    // opcionais: sem anotação nenhuma
    String descricao,
    String local,
    boolean aceitaIniciante
) { }
