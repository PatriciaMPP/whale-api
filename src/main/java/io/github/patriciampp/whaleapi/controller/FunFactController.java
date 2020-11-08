package io.github.patriciampp.whaleapi.controller;

        import io.github.patriciampp.whaleapi.persistence.model.FunFact;
        import io.github.patriciampp.whaleapi.persistence.model.Whale;
        import io.github.patriciampp.whaleapi.persistence.repository.FunFactRepository;
        import io.github.patriciampp.whaleapi.service.FunFactService;

        import io.github.patriciampp.whaleapi.service.WhaleService;
        import org.json.simple.JSONObject;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Iterable<FunFact>> findAll(){
        return ResponseEntity.ok(funFactService.getAll());
    }

    @GetMapping(path = "/funfacts/{id}")
    public ResponseEntity<FunFact> findById(@PathVariable ("id") int id){
        return ResponseEntity.ok(funFactService.findById(id));
    }

    @DeleteMapping(path = "/funfacts")
    public ResponseEntity<Void> deleteAll(){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/funfacts/{id}")
    public  ResponseEntity<Boolean> deleteById(@PathVariable("id") int id) {
    return ResponseEntity.ok(funFactService.deleteById(id));
    }

    @PostMapping(path = "/funfacts")
    public ResponseEntity<FunFact> create(@RequestBody JSONObject funFactJSON) {
        int whaleId = (Integer) funFactJSON.get("whaleId");
        String funFactText = (String) funFactJSON.get("funFact");

        Whale whale = whaleService.findById(whaleId);
        FunFact funFact = new FunFact(funFactText, whale);

        FunFact funFactCreated = funFactService.add(funFact);

        URI funFactUri = ServletUriComponentsBuilder.fromCurrentRequest().path(funFactCreated.toString()).build().toUri();
        return ResponseEntity.created(funFactUri).body(null);
     }

    @PutMapping(path = "/funfacts/{id}", produces = { "application/json" })
    public ResponseEntity<FunFact> update(@PathVariable("id") int id, @RequestBody JSONObject funFactJSON) {

        FunFact funFactToUpdate = funFactService.findById(id);

        FunFact funFactUpdated = funFactService.update(funFactToUpdate, funFactJSON);
        return ResponseEntity.ok(funFactUpdated);

    }
}
