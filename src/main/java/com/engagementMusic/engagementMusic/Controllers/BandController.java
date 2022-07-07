package com.engagementMusic.engagementMusic.Controllers;

import com.engagementMusic.engagementMusic.DTO.BandDTO;
import com.engagementMusic.engagementMusic.DTO.MemberDTO;
import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Genre;
import com.engagementMusic.engagementMusic.Models.Band;
import com.engagementMusic.engagementMusic.Models.Booking;
import com.engagementMusic.engagementMusic.Models.Member;
import com.engagementMusic.engagementMusic.Services.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class BandController {
    @Autowired
    BandService bandService;

    @GetMapping("/all-bands")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Band> getBand() { return bandService.findAll();}

    @GetMapping("/band/find-username")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Band getBandByUsername(@AuthenticationPrincipal UserDetails userDetails) {
        return bandService.findByUsername(userDetails);
    }

    @PostMapping("/add-band")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Band addBand(@RequestBody @Valid BandDTO band){ return bandService.addBand(band);}


    @DeleteMapping("/band/delete")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteByUsername(@AuthenticationPrincipal UserDetails userDetails) { bandService.deleteBand(userDetails);}

    @PutMapping("/band/update")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.OK)
    public Band modifyAttributes(@AuthenticationPrincipal UserDetails userDetails,
                                 @RequestBody @Valid BandDTO band) {
        return bandService.modifyAttributes(userDetails, band);
    }
    @PostMapping("/band/add-member")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Member addMember(@AuthenticationPrincipal UserDetails userDetails, @RequestBody @Valid MemberDTO member){ return bandService.addMember(userDetails, member);}

    @GetMapping("/all-members")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Member> allMembers() { return bandService.findAllMembers();}

    @GetMapping("/band/all-members-of-list")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Member> allMembersOfBand(@AuthenticationPrincipal UserDetails userDetails) {
        return bandService.findAllMembersOfBand(userDetails);
    }
    @GetMapping("/band/list-booking")
    @CrossOrigin()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Booking> bookingList(@AuthenticationPrincipal UserDetails userDetails) {
        return bandService.listBookingBand(userDetails);
    }

}
