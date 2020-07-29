package io.github.patriciampp.whaleapi.controller;

        import io.github.patriciampp.whaleapi.persistence.model.FunFact;
        import io.github.patriciampp.whaleapi.persistence.model.Whale;
        import io.github.patriciampp.whaleapi.persistence.repository.FunFactRepository;
        import io.github.patriciampp.whaleapi.persistence.repository.WhaleRepository;
        import io.github.patriciampp.whaleapi.service.FunFactService;

        import io.github.patriciampp.whaleapi.service.WhaleService;
        import org.json.simple.JSONObject;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

        import java.net.URI;
        import java.util.Set;

@RestController
@RequestMapping(path = "/whale-api")
public class FunFactController {

    @Autowired
    private FunFactService funFactService;

    @Autowired
    private WhaleService whaleService;

    @GetMapping(path = "/funfacts")
    public ResponseEntity<Set<FunFact>> find(){
        return ResponseEntity.ok(funFactService.getAll());
    }

    @PostMapping(path = "/funfacts")
    public ResponseEntity<FunFact> create(@RequestBody JSONObject funFactJSON) {
        Integer whaleId = (Integer) funFactJSON.get("whaleId");
        String funFactText = (String) funFactJSON.get("funFact");

        Whale whale = whaleService.getById(whaleId);
        FunFact funFact = new FunFact(funFactText, whale);

        FunFact funFactCreated = funFactService.add(funFact);

        URI funFactUri = ServletUriComponentsBuilder.fromCurrentRequest().path(funFactCreated.toString()).build().toUri();
        return ResponseEntity.created(funFactUri).body(null);
     }
    
}
