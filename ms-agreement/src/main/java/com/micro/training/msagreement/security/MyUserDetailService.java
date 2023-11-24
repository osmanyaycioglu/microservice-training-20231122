package com.micro.training.msagreement.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final BCryptPasswordEncoder passwordEncoder;
//    private final IUserEntityDao userEntityDao;
    private static Map<String, UserDetails> users = new ConcurrentHashMap<>();


    public MyUserDetailService(BCryptPasswordEncoder passwordEncoderParam
                               ) {
        passwordEncoder = passwordEncoderParam;

        users.put("osmany",
                  User.builder()
                      .username("osmany")
                      .password(passwordEncoderParam.encode("123456"))
                      .roles("ADMIN")
                      .build());
        users.put("ahmett",
                  User.builder()
                      .username("ahmett")
                      .password(passwordEncoderParam.encode("123456"))
                      .roles("USER")
                      .build());
    }

    private UserDetails cloneUser(UserDetails userDetailsParam) {
        return User.builder()
                   .username(userDetailsParam.getUsername())
                   .password(userDetailsParam.getPassword())
                   .authorities(userDetailsParam.getAuthorities())
                   .build();
    }

//    private UserDetails convertToUserDetails(UserEntity userDetailsParam) {
//        return User.builder()
//                   .username(userDetailsParam.getUsername())
//                   .password(passwordEncoder.encode(userDetailsParam.getPassword()))
//                   .roles(userDetailsParam.getUserRole().name())
//                   .build();
//    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDetails userDetailsLoc = users.get(username);
        if (userDetailsLoc == null) {
            throw new UsernameNotFoundException("User yok");
        }
        return cloneUser(userDetailsLoc);
        //        UserEntity  userDetailsLoc  = userEntityDao.findByUsername(username);
//        if (userDetailsLoc == null) {
//            throw new UsernameNotFoundException("Böyle bir user yok");
//        }
//
//        return convertToUserDetails(userDetailsLoc);
    }
}
