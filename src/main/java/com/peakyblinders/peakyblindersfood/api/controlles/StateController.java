package com.peakyblinders.peakyblindersfood.api.controlles;


import com.peakyblinders.peakyblindersfood.api.assembler.StateModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.StateInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.StateModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.StateInput;
import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.EntityNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.StateNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.UserNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.State;
import com.peakyblinders.peakyblindersfood.domain.repositories.StateRepository;
import com.peakyblinders.peakyblindersfood.domain.services.StateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateInputDisassembler stateInputDisassembler;
    @Autowired
    private StateModelAssembler stateModelAssembler;
    @Autowired
    private StateService stateService;

    @GetMapping
    public List<StateModelDTO> list() {
        return stateModelAssembler.
                toCollectionModel(stateRepository.findAll());
    }


    @GetMapping("/{stateId}")
    public StateModelDTO searchById(@PathVariable Long stateId) {
        return stateModelAssembler.toModel(stateService.seekOrFail(stateId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StateModelDTO save(@RequestBody @Valid StateInput stateInput) {
        State state = stateInputDisassembler.toDomainObject(stateInput);
        state = stateService.save(state);
        return stateModelAssembler.toModel(state);
    }

    @PutMapping("/{statesId}")
    public StateModelDTO update(@PathVariable Long statesId, @RequestBody @Valid StateInput stateInput) {
        State stateCurrent = stateService.seekOrFail(statesId);
        stateInputDisassembler.copyToDomainObjtect(stateInput, stateCurrent);
        stateCurrent = stateService.save(stateCurrent);

        return stateModelAssembler.toModel(stateCurrent);
    }

    @DeleteMapping("/{statesId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long statesId) {
        try {
            stateService.remove(statesId);
        } catch (StateNotFoundException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
