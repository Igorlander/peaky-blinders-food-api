package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.api.assembler.KitchenModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.KitchenInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.KitchenModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.KitchenInput;
import com.peakyblinders.peakyblindersfood.domain.models.Kitchen;
import com.peakyblinders.peakyblindersfood.domain.repositories.KitchenRepository;
import com.peakyblinders.peakyblindersfood.domain.services.KitchenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenService kitchenService;

    @Autowired
    private KitchenModelAssembler kitchenModelAssembler;

    @Autowired
    private KitchenInputDisassembler kitchenInputDisassembler;

    @GetMapping
    public Page<KitchenModelDTO> list(@PageableDefault(size = 10) Pageable pageable) {
        Page<Kitchen> kitchensPage = kitchenRepository.findAll(pageable);

        List<KitchenModelDTO> kitchenModel = kitchenModelAssembler.toCollectionModel(kitchensPage.getContent());

        Page<KitchenModelDTO> kitchenModelDTOPage = new PageImpl<>(kitchenModel, pageable,
                kitchensPage.getTotalElements());

        return kitchenModelDTOPage;
    }

    @GetMapping("/{kitchenId}")
    public KitchenModelDTO searchById(@PathVariable("kitchenId") Long kitchenId) {
        return kitchenModelAssembler.toModel(kitchenService.seekOrFail(kitchenId));
    }

    @GetMapping("/name/{name}")
    public List<KitchenModelDTO> searchByName(@PathVariable("name") String name) {
        return kitchenModelAssembler.toCollectionModel(kitchenRepository.findByName(name));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public KitchenModelDTO save(@RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchen = kitchenInputDisassembler.toDomainObject(kitchenInput);
        return kitchenModelAssembler.toModel(kitchenService.save(kitchen));

    }


    @PutMapping("/{kitchenId}")
    public KitchenModelDTO update(@PathVariable Long kitchenId, @RequestBody @Valid KitchenInput kitchenInput) {
        Kitchen kitchenCurrent = kitchenService.seekOrFail(kitchenId);
        kitchenInputDisassembler.copyToDomainObjtect(kitchenInput, kitchenCurrent);
        kitchenCurrent = kitchenService.save(kitchenCurrent);
        return kitchenModelAssembler.toModel(kitchenCurrent);
    }


    @DeleteMapping("/{kitchenId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long kitchenId) {
        kitchenService.remove(kitchenId);
    }

    @GetMapping("/kitchen/fisrt")
    public Optional<Kitchen> kitchenFisrt() {
        return kitchenRepository.findFirst();

    }
}

