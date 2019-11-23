package com.main.sqlcrud.controller;

import java.util.List;

import javax.validation.Valid;

import com.main.sqlcrud.message.request.OperatorForm;
import com.main.sqlcrud.model.Operator;
import com.main.sqlcrud.model.User;
import com.main.sqlcrud.repository.OperatorRepository;
import com.main.sqlcrud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/studentMg/operator")
public class OperatorAPIs{

    @Autowired
    OperatorRepository operatorRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signin")
    public User signinOperator(@Valid @RequestBody User userRequest) {
        System.out.println("signin operator started. "+userRequest.getUserRole().equals(null));

        User recUser = new User();

        if(!userRequest.getUserRole().equals(null) && !userRepository.existsByUserId(userRequest.getUserId()) && operatorRepository.existsByNic(userRequest.getUserId())){
            User newUser = new User(userRequest.getUserId(), userRequest.getPassword(), userRequest.getUserRole());
            recUser = userRepository.save(newUser);
        }
        return recUser;

    }

    @PostMapping("/add") //admin only
    public Operator addNewOperator(@Valid @RequestBody OperatorForm newOperatorRequest) {

        Operator tempOperator = new Operator();

        if(!operatorRepository.existsByNic(newOperatorRequest.getNic())){
            tempOperator.setNic(newOperatorRequest.getNic());
            tempOperator.setFirstName(newOperatorRequest.getFirstName());
            tempOperator.setLastName(newOperatorRequest.getLastName());
            tempOperator.setBday(newOperatorRequest.getBday());
            tempOperator.setAddress(newOperatorRequest.getAddress());
            tempOperator.setEnrolledDate(newOperatorRequest.getEnrolledDate());
            tempOperator.setStatus(newOperatorRequest.getStatus());

            Operator recOp =  operatorRepository.save(tempOperator);

            System.out.println("saved operator : "+recOp);
            return recOp;


        }else{
            return tempOperator;


        }

    }

    @PutMapping("/pwResetOperator") //pw reset by only for admin
    public Boolean pwResetOperator(@Valid @RequestBody User userForm) {
        if(userRepository.existsById(userForm.getUserId())){
            User tempUser = new User(userForm.getUserId(),userForm.getPassword(),userForm.getUserRole());
            userRepository.save(tempUser);

            return true;
        }else{
            return false;
        }
    }


    @DeleteMapping("/remove/{nic}/{status}") //only for admin
    public Boolean removeOperator(@PathVariable(value = "nic") String nic, @PathVariable(value="status") String status) {

        User tempUser = userRepository.findByUserId(nic);
        Operator tempOp = operatorRepository.findByNic(nic);
        
        if (tempOp != null) {
            userRepository.delete(tempUser);
            tempOp.setStatus(status);

            Operator temp = operatorRepository.save(tempOp);

            if(!temp.equals(null)){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }

    }

    //Get operators count
    @GetMapping("/getallcount")
    public Long getOperatorsCount() {
        return operatorRepository.count();
    }

    @GetMapping("/getall")
    public List<Operator> getAllOperators() {
        return operatorRepository.findAll();
    }
    

    


}