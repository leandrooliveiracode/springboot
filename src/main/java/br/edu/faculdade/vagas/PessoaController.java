
package br.edu.faculdade.vagas;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private final PessoaService servico;

    public PessoaController(PessoaService servico) {
        this.servico = servico;
    }

    @GetMapping
    public List<PessoaResposta> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResposta> porId(
            @PathVariable String id) {

        return servico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PessoaResposta> criar(
            @Valid @RequestBody PessoaEntrada entrada) {

        PessoaResposta salva = servico.criar(entrada);

        URI onde = URI.create("/pessoas/" + salva.id());

        return ResponseEntity.created(onde).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResposta> trocar(
            @PathVariable String id,
            @Valid @RequestBody PessoaEntrada entrada) {

        return servico.trocar(id, entrada)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(
            @PathVariable String id) {

        return servico.apagar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
