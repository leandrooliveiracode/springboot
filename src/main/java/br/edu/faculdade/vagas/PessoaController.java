
package br.edu.faculdade.vagas;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Pessoa> listar() {
        return servico.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> porId(@PathVariable String id) {
        return servico.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa nova) {
        Pessoa salva = servico.criar(nova);
        URI onde = URI.create("/pessoas/" + salva.id());
        return ResponseEntity.created(onde).body(salva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> trocar(
            @PathVariable String id,
            @RequestBody Pessoa nova) {

        return servico.trocar(id, nova)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable String id) {
        return servico.apagar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
