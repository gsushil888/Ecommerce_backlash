package com.backlashprogramming.ecommerce.EcommerceByBacklash.api.security;

import com.backlashprogramming.ecommerce.EcommerceByBacklash.dao.LocalUserDao;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.entities.LocalUser;
import com.backlashprogramming.ecommerce.EcommerceByBacklash.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@Component
@Slf4j
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private LocalUserDao localUserDao;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenHeader=request.getHeader("Authorization");
        if (tokenHeader !=null && tokenHeader.startsWith("Bearer ")){
            String token = tokenHeader.substring(7);
            try{
                String username = jwtService.getUsername(token);
                Optional<LocalUser> opUser=localUserDao.findByUserNameIgnoreCase(username);

                if (opUser.isPresent()){
                    LocalUser user=opUser.get();
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }
            catch (Exception e){
                log.info("Something went wrong while decoding token");
            }
        }
        filterChain.doFilter(request,response);
    }
}
