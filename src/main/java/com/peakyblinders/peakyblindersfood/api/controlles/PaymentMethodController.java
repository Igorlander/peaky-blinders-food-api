package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.api.assembler.PaymentMethodModelAssembler;
import com.peakyblinders.peakyblindersfood.api.assembler.PaymentMethodInputDisassembler;
import com.peakyblinders.peakyblindersfood.api.model.dto.PaymentMethodModelDTO;
import com.peakyblinders.peakyblindersfood.api.model.input.PaymentMethodInput;
import com.peakyblinders.peakyblindersfood.domain.models.PaymentMethod;
import com.peakyblinders.peakyblindersfood.domain.repositories.PaymentMethodRepository;
import com.peakyblinders.peakyblindersfood.domain.services.PaymentMethodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/methods")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodRepository methodRepository;

    @Autowired
    private PaymentMethodModelAssembler methodAssembler;

    @Autowired
    private PaymentMethodInputDisassembler methodDisassembler;
    @Autowired
    private PaymentMethodService methodService;

    @GetMapping
    public List<PaymentMethodModelDTO> list(){
        return methodAssembler.toCollectionModel(methodRepository.findAll());
    }


    @GetMapping("/{paymentMethodId}")
    public PaymentMethodModelDTO searchById(@PathVariable Long paymentMethodId){
        PaymentMethod paymentMethod = methodService.seekOrFail(paymentMethodId);
          return methodAssembler.toModel(paymentMethod);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModelDTO save(@RequestBody @Valid PaymentMethodInput paymentMethodInput) {
        PaymentMethod method = methodDisassembler.toDomainObject(paymentMethodInput);
        method = methodService.save(method);
        return methodAssembler.toModel(method);
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModelDTO update(@PathVariable Long paymentMethodId,
                                                @RequestBody PaymentMethodInput paymentMethodInput){
        PaymentMethod paymentMethodCurrent = methodService.seekOrFail(paymentMethodId);
        methodDisassembler.copyToDomainObjtect(paymentMethodInput ,paymentMethodCurrent);
            paymentMethodCurrent = methodService.save(paymentMethodCurrent);
            return methodAssembler.toModel(paymentMethodCurrent);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable Long paymentMethodId){
                    methodService.remove(paymentMethodId);
        }
}
