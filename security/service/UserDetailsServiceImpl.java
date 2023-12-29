/*
package com.amex.sms.school.security.service;

import com.amex.sms.school.security.User;
import com.amex.sms.school.security.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

*/
/**
 * @author sateesh.gullipalli
 * @project school
 * @created on 09 Nov, 2023
 *//*

//@Service
public class UserDetailsServiceImpl  implements UserDetailsService {
    //@Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
}
*/
