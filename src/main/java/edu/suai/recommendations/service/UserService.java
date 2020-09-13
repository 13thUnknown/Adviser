package edu.suai.recommendations.service;

import edu.suai.recommendations.common.RolesEnum;
import edu.suai.recommendations.common.UserFilters;
import edu.suai.recommendations.exception.NotFoundException;
import edu.suai.recommendations.model.Option;
import edu.suai.recommendations.model.Product;
import edu.suai.recommendations.model.Role;
import edu.suai.recommendations.model.User;
import edu.suai.recommendations.repository.RoleRepository;
import edu.suai.recommendations.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static edu.suai.recommendations.common.Constants.MULTI;
import static edu.suai.recommendations.service.specification.UserSpecification.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class));
    }

    @Transactional
    public void recommendNewProduct(Product product){
        List<User> users = userRepository.findAll();
        for (User user: users){
            if (checkOptions(user.getOptions(),product.getOptions())){
                user.getRecommendations().add(product);
            }
        }
    }

    public boolean checkOptions(Set<Option> uOptions, Set<Option> pOptions){
        int vote=0;
        for (Option pOption:pOptions){
            for(Option uOption:uOptions){
                if (uOption.getCriteria().getTitle().equals(pOption.getCriteria().getTitle())){
                    if (uOption.getCriteria().isNumeric()){
                        if (Double.parseDouble(pOption.getValue())*MULTI>Double.parseDouble(uOption.getValue())){
                            vote++;
                        }
                        else{
                            vote--;
                        }
                    }
                }
            }
        }
        return (vote>0);
    }

    @Transactional(readOnly = true)
    public User getUserByUserName(String userName) {
        return userRepository.findByLogin(userName).orElseThrow(() -> new NotFoundException(User.class));
    }

    @Transactional(readOnly = true)
    public User getAuthUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        if (!username.equals("anonymousUser")) {
            return getUserByUserName(username);
        }
            return null;
    }

    @Transactional
    public boolean createUser(User user){
        Role role = roleRepository.findByTitle(RolesEnum.USER.getTitle()).orElseThrow(() -> new NotFoundException(Role.class));
        user.setRoles(Collections.singleton(role));
        role.getUsers().add(user);
        userRepository.save(user);
        return true;
    }

    @Transactional(readOnly = true)
    public Page<User> findAllUsersByKeyWord(UserFilters filters, Pageable pageable) {
        String search = filters.getSearch();
        Specification<User> specification = emailStartingWith(search)
                .and(createdBefore(filters.getCreationDateBefore()))
                .and(createdAfter(filters.getCreationDateAfter()))
                .and(updatedBefore(filters.getUpdateDateBefore()))
                .and(updatedAfter(filters.getUpdateDateAfter()));
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        return userRepository.findByLogin(username).orElseThrow(()->new UsernameNotFoundException("User: " + username + " not found!"));
    }
}
