package com.engagementMusic.engagementMusic.Controllers;

import com.engagementMusic.engagementMusic.DTO.AgentDTO;
import com.engagementMusic.engagementMusic.DTO.BookingDTO;
import com.engagementMusic.engagementMusic.DTO.DancingBarDTO;
import com.engagementMusic.engagementMusic.DTO.MemberDTO;
import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Models.*;
import com.engagementMusic.engagementMusic.Services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
public class AgentController {

    @Autowired
    AgentService agentService;

    @GetMapping("/all-agents")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Agent> getBand() { return agentService.findAll();}

    @PostMapping("/add-agent")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Agent addAgent(@RequestBody @Valid AgentDTO agent){ return agentService.addAgent(agent);}

    @DeleteMapping("/agent/delete")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteByUsername(@AuthenticationPrincipal UserDetails userDetails) { agentService.deleteAgent(userDetails);}

    @PutMapping("/agent/update")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    public Agent modifyAttributes(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestParam Optional<String> password, @RequestParam Optional <FullName> fullName,
                                 @RequestParam Optional<String> email, @RequestParam Optional<String> picture,
                                 @RequestParam Optional<String> dni, @RequestParam Optional<Long> phone,
                                 @RequestParam Optional<String> position) {
        return agentService.modifyAttributes(userDetails, password, fullName,email, picture, dni, phone, position);
    }
    @PostMapping("/agent/add-bar")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DancingBar addBar(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid DancingBarDTO dancingBarDTO){
        return agentService.addDancingBar(userDetails, dancingBarDTO);}

    @GetMapping("/all-bars")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DancingBar> allBars() { return agentService.findAllDancingBar();}

    @GetMapping("/agent/all-bars-list")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<DancingBar> allDancingBarsOfAgent(@AuthenticationPrincipal UserDetails userDetails) {
        return agentService.findAllDancingBarOfAgent(userDetails);
    }
    @GetMapping("/agent/show-band/{name}")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Band findByUsername(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String name) {
        return agentService.findByNameOfGroup(userDetails, name);
    }
    @GetMapping("/agent/list-booking")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Booking> bookingList(@AuthenticationPrincipal UserDetails userDetails) {
        return agentService.listBookingAgent(userDetails);
    }
    @PostMapping("/agent/add-booking")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Booking addBooking(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String name,
                              @RequestBody @Valid BookingDTO bookingDTO){
        return agentService.bookingAgent(userDetails, name, bookingDTO);}

}
