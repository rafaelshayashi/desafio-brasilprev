package com.rafaelhayashi.brasilprev.util.annotation;

import com.rafaelhayashi.brasilprev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCpfValidator implements ConstraintValidator<UniqueCpf, String> {

   @Autowired
   private CustomerRepository customerRepository;

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {
      return !customerRepository.findByCpf(value).isPresent();
   }
}
