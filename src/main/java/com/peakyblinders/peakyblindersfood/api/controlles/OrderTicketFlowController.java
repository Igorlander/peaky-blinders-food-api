package com.peakyblinders.peakyblindersfood.api.controlles;

import com.peakyblinders.peakyblindersfood.domain.services.OrderTicketFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets/{code}")
public class OrderTicketFlowController {


    @Autowired
    private OrderTicketFlowService ticketFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmation(@PathVariable String code){
        ticketFlowService.confirmation(code);
    }
    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delivery(@PathVariable String code){
        ticketFlowService.delivery(code);
    }

    @PutMapping("/canceled")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void canceled(@PathVariable String code){
        ticketFlowService.canceled(code);
    }
}
