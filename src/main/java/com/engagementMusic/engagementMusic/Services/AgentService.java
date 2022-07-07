package com.engagementMusic.engagementMusic.Services;

import com.engagementMusic.engagementMusic.DTO.AgentDTO;
import com.engagementMusic.engagementMusic.DTO.BookingDTO;
import com.engagementMusic.engagementMusic.DTO.DancingBarDTO;
import com.engagementMusic.engagementMusic.DTO.MemberDTO;
import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Models.*;
import com.engagementMusic.engagementMusic.Repositories.AgentRepository;
import com.engagementMusic.engagementMusic.Repositories.BandRepository;
import com.engagementMusic.engagementMusic.Repositories.BookingRepository;
import com.engagementMusic.engagementMusic.Repositories.DancingBarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    AgentRepository agentRepository;

    @Autowired
    DancingBarRepository dancingBarRepository;

    @Autowired
    BandRepository bandRepository;

    @Autowired
    BookingRepository bookingRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Agent> findAll(){ return agentRepository.findAll();}

    public Agent addAgent(AgentDTO agentDTO){
        if (!agentRepository.findByUsername(agentDTO.getUsername()).isPresent()) {
            Agent agent = new Agent(agentDTO.getFullName(), agentDTO.getEmail(), agentDTO.getUsername(), passwordEncoder.encode (agentDTO.getPassword()),
                    agentDTO.getPicture(), agentDTO.getDni(), agentDTO.getPhone(), agentDTO.getPosition());

            if (agentDTO.getPicture() != null || agentDTO.getPicture().isEmpty()) agent.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/User_font_awesome.svg/2048px-User_font_awesome.svg.png");
            agent.setRole("AGENT");
            return agentRepository.save(agent);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username already exists");
        }
    }
    public void deleteAgent(UserDetails userDetails){
        if(agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            agentRepository.delete(agent);
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agent not found");
        }
    }
    public Agent modifyAttributes(UserDetails userDetails, Optional<String> password, Optional <FullName> fullName, Optional<String> email,
                                 Optional<String> picture, Optional<String> dni, Optional<Long> phone, Optional<String> position){

        if(agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            if(password.isPresent() && !password.isEmpty()){ agent.setPassword(password.get());}
            if(fullName.isPresent() && !fullName.isEmpty()){ agent.setFullName(fullName.get());}
            if(email.isPresent() && !email.isEmpty()){ agent.setEmail(email.get());}
            if(picture.isPresent() && !picture.isEmpty()){ agent.setPicture(picture.get());}
            if(dni.isPresent() && !dni.isEmpty()){ agent.setDni(dni.get());}
            if(phone.isPresent() && !phone.isEmpty()){ agent.setPhone(phone.get());}
            if(position.isPresent() && !position.isEmpty()){ agent.setPosition(position.get());}
            return agentRepository.save(agent);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Agent doesn't exist");
        }
    }
    public DancingBar addDancingBar(UserDetails userDetails, DancingBarDTO  dancingBarDTO){
        if (agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            DancingBar dancingBar = new DancingBar(agent, dancingBarDTO.getName(),
                    dancingBarDTO.getPicture(), dancingBarDTO.getAddress());
            if (dancingBarDTO.getPicture() != null || dancingBarDTO.getPicture().isEmpty()) dancingBar.setPicture("https://cdn-icons-png.flaticon.com/512/335/335954.png");
            return dancingBarRepository.save(dancingBar);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username doesn't exist");
        }
    }
    public List<DancingBar> findAllDancingBar(){ return dancingBarRepository.findAll();}

    public List<DancingBar> findAllDancingBarOfAgent(UserDetails userDetails){
        if (agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            return agent.getDancingBarListAgent();
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username doesn't exist");
        }

    }
    public Band findByNameOfGroup(UserDetails userDetails, String name){
        if (agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            if (bandRepository.findByNameOfGroup(name).isPresent()){
                Band band = bandRepository.findByNameOfGroup(name).get();
                return band;
            } else{
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username not found");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agent not found");
        }
    }
    public Booking bookingAgent(UserDetails userDetails, String name, BookingDTO bookingDTO){
        if (agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            System.err.println(agent);
            if (bandRepository.findByNameOfGroup(name).isPresent()) {
                Band band = bandRepository.findByNameOfGroup(name).get();
                Booking booking = new Booking(band, agent, bookingDTO.getDate(), bookingDTO.getPriceTotal());

                if(band.getBookingList().isEmpty()){
                    return bookingRepository.save(booking);
                } else{
                    for (Booking x: band.getBookingList()) {
                        if (x.getDate() == booking.getDate()){
                            throw new ResponseStatusException(HttpStatus.CONFLICT, "Not available");
                        } else{
                            return bookingRepository.save(booking);
                        }
                    }
                }

            } else{
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Username not found");
            }
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agent not found");
        }
        return null;
    }
    public List<Booking> listBookingAgent(UserDetails userDetails){
        if(agentRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Agent agent = agentRepository.findByUsername(userDetails.getUsername()).get();
            return agent.getBookingListAgent();
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agent not found");
        }
    }


}
