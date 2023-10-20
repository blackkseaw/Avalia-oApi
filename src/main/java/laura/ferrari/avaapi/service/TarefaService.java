package laura.ferrari.avaapi.service;

import laura.ferrari.avaapi.entity.Tarefa;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private List<Tarefa> tarefas;

    private TarefaService() {
        tarefas = new ArrayList<>();
    }

    public List<Tarefa> buscarTarefas() {
        return tarefas;
    }

    public Tarefa criarTarefa(Tarefa tarefa) throws Exception {
        tarefas.add(tarefa);
        return tarefa;
    }

    public Tarefa buscarTarefa(Long codigo) throws Exception {
        Optional<Tarefa> tarefa = tarefas.stream().filter(e -> e.getCodigo() == codigo).findFirst();
        if (tarefa.isPresent()) {
            return tarefa.get();
        } else {
            throw new Exception("Tarefa não encontrada.");
        }
    }


    public Tarefa alterarTarefa(Long codigo, Tarefa tarefa) throws Exception {
        Optional<Tarefa> tarefaAltera = tarefas.stream().filter(e -> e.getCodigo() == codigo).findFirst();
        if (tarefaAltera.isPresent()) {
            tarefaAltera.get().setNome(tarefa.getNome());
            tarefaAltera.get().setDescricao(tarefa.getDescricao());
            return tarefa;
        } else {
            throw new Exception("Tarefa não encontrada.");
        }
    }

    public Tarefa alterarStatus(Long codigo, Tarefa tarefa) throws Exception {
        Optional<Tarefa> tarefaAltera = tarefas.stream().filter(e -> e.getCodigo() == codigo).findFirst();
        if (tarefaAltera.isPresent()) {
            tarefaAltera.get().setStatus(tarefa.getStatus());
            return tarefa;
        } else {
            throw new Exception("Tarefa não encontrada.");
        }
    }

    public void removerTarefa(Long codigo) throws Exception {
        tarefas.removeIf(tarefa -> tarefa.getCodigo() == codigo);
    }


}
