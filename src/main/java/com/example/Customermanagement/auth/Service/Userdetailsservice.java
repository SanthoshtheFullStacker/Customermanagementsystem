package com.example.Customermanagement.auth.Service;


import com.example.Customermanagement.auth.Model.Userdetails;
import com.example.Customermanagement.auth.Model.Usermodel;
import com.example.Customermanagement.auth.Repository.Userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Userdetailsservice implements UserDetailsService {

    @Autowired
    private Userrepository userrepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username != ""){

            Optional<Usermodel> userdata = userrepository.findByUsername(username);

            System.out.println("calling inside "+userdata.get().getUsername());

            if(userdata.isPresent()){



                return new Userdetails(userdata.get());

            }

        }

        throw new UsernameNotFoundException("username not found");
    }


}
