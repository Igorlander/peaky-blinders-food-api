package com.peakyblinders.peakyblindersfood.domain.services;

import com.peakyblinders.peakyblindersfood.domain.exceptions.BusinessException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.StateNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.exceptions.TicketNotFoundException;
import com.peakyblinders.peakyblindersfood.domain.models.*;
import com.peakyblinders.peakyblindersfood.domain.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TicketService {

   @Autowired
   private TicketRepository ticketRepository;

   @Autowired
   private RestaurantService restaurantService;

   @Autowired
   private CityService cityService;

   @Autowired
   private UserService userService;

   @Autowired
   private ProductService productService;

   @Autowired
   private PaymentMethodService paymentMethodService;


   @Transactional
   public Ticket issue(Ticket ticket){
        validateOrder(ticket);
        validateItems(ticket);

        ticket.setShippingFee(ticket.getRestaurant().getShippingFee());
        ticket.calculateTotalValue();
        return ticketRepository.save(ticket);
   }

   public void validateOrder(Ticket ticket){
       City city = cityService.seekOrFail(ticket.getDeliveryAddress().getCity().getId());
       User client = userService.seekOrFail(ticket.getClient().getId());
       Restaurant restaurant = restaurantService.seekOrFail(ticket.getRestaurant().getId());
       PaymentMethod paymentMethod = paymentMethodService.seekOrFail(ticket.getPaymentMethod().getId());

       ticket.getDeliveryAddress().setCity(city);
       ticket.setClient(client);
       ticket.setRestaurant(restaurant);
       ticket.setPaymentMethod(paymentMethod);

       if(restaurant.doNotAcceptdPaymentMethod(paymentMethod)){
            throw new BusinessException(String.format("Forma de pagamento '%s' não é aceita por esse " +
                            "restaurante.", paymentMethod.getDescription()));
       }

   }
   public void validateItems(Ticket ticket){
       ticket.getItems().forEach(itemOrder -> {
           Product produc = productService.seekOrFail(ticket.getRestaurant().getId(),
                   itemOrder.getProduct().getId());
           itemOrder.setTicket(ticket);
           itemOrder.setProduct(produc);
           itemOrder.setUnitPrice(produc.getPrice());
       });

   }

    public Ticket seekOrFail(String code){
        return ticketRepository.findByCode(code)
                .orElseThrow(() -> new TicketNotFoundException(code));
    }

    /*public Ticket seekOrFail(Long ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException(ticketId));
    }*/
}
