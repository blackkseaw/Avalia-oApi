package laura.ferrari.avaapi.controller;

import laura.ferrari.avaapi.entity.Tarefa;
import laura.ferrari.avaapi.service.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tarefa")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }

    @GetMapping("todas")
    public ResponseEntity<?> buscarTarefas(){
        try{
            List<Tarefa> listaTarefas = tarefaService.buscarTarefas();
            return  new ResponseEntity(listaTarefas, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>("Erro na requisição realizada", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarTarefa(@PathVariable("codigo")Long codigo){
        try {
            Tarefa tarefa = tarefaService.buscarTarefa(codigo);
            return new ResponseEntity(tarefa, HttpStatus.OK);
        }catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("adicionar")
    public ResponseEntity<?> criarTarefa(@RequestBody Tarefa tarefa){
        try{
            tarefa = tarefaService.criarTarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);        }
    }

    @PutMapping("alterar/{codigo}")
    public ResponseEntity<?> alterarTarefa(@RequestBody Tarefa tarefa, @PathVariable("codigo") Long codigo){
        try {
            tarefa = tarefaService.alterarTarefa(codigo, tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("status/{codigo}")
    public ResponseEntity<?> alterarStatus(@RequestBody Tarefa tarefa, @PathVariable("codigo") Long codigo){
        try {
            tarefa = tarefaService.alterarStatus(codigo, tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        }catch (Exception exception){
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("remover/{codigo}")
    public ResponseEntity<?> removerTarefa(@PathVariable("codigo") Long codigo){
        try {
            tarefaService.removerTarefa(codigo);
            return new ResponseEntity("Tarefa removida com Sucesso", HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
