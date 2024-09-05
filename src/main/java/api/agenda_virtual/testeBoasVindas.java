package api.agenda_virtual;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class testeBoasVindas {
    @GetMapping

public String boasVindas(){
    return "Projeto agenda virtual em andamento, \n começando a nserir os primeiros registros pelo thunder clint";
}    
}
