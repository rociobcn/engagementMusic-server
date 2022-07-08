package com.engagementMusic.engagementMusic.Services;

import com.engagementMusic.engagementMusic.DTO.BandDTO;
import com.engagementMusic.engagementMusic.DTO.MemberDTO;
import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Genre;
import com.engagementMusic.engagementMusic.Models.Agent;
import com.engagementMusic.engagementMusic.Models.Band;
import com.engagementMusic.engagementMusic.Models.Booking;
import com.engagementMusic.engagementMusic.Models.Member;
import com.engagementMusic.engagementMusic.Repositories.BandRepository;
import com.engagementMusic.engagementMusic.Repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class BandService {
    @Autowired
    BandRepository  bandRepository;
    @Autowired
    MemberRepository memberRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Band> findAll(){ return bandRepository.findAll();}

    public Band findByUsername(UserDetails userDetails){
        return bandRepository.findByUsername(userDetails.getUsername()).get();
    }

    public Band addBand(BandDTO bandDTO){
        if (!bandRepository.findByUsername(bandDTO.getUsername()).isPresent()) {
            Band band1 = new Band(bandDTO.getFullName(), bandDTO.getEmail(), bandDTO.getUsername(), passwordEncoder.encode (bandDTO.getPassword()),
                        bandDTO.getPicture(), bandDTO.getDni(), bandDTO.getPhone(), bandDTO.getNameOfGroup(), bandDTO.getGenre(),
                        bandDTO.getPriceHour());

            if (bandDTO.getPicture() != null || bandDTO.getPicture().isEmpty()) band1.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/User_font_awesome.svg/2048px-User_font_awesome.svg.png");
            band1.setRole("BAND");
            return bandRepository.save(band1);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username already exists");
        }
    }
    public void deleteBand(UserDetails userDetails){
        if(bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band = bandRepository.findByUsername(userDetails.getUsername()).get();
            bandRepository.delete(band);
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Band not found");
        }
    }
    public Band modifyAttributes(UserDetails userDetails, BandDTO bandDTO){

        if(bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band1 = new Band(bandDTO.getFullName(), bandDTO.getEmail(), bandDTO.getUsername(), passwordEncoder.encode (bandDTO.getPassword()),
                    bandDTO.getPicture(), bandDTO.getDni(), bandDTO.getPhone(), bandDTO.getNameOfGroup(), bandDTO.getGenre(),
                    bandDTO.getPriceHour());
            System.out.println(bandDTO.getPicture().toString());
            band1.setPicture(bandDTO.getPicture());
            if (bandDTO.getPicture() == null || bandDTO.getPicture().isEmpty()) band1.setPicture("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7c/User_font_awesome.svg/2048px-User_font_awesome.svg.png");
            band1.setRole("BAND");
            return bandRepository.save(band1);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Band doesn't exist");
        }
    }

    public Member addMember(UserDetails userDetails, MemberDTO memberDTO){
        if (bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band1 = bandRepository.findByUsername(userDetails.getUsername()).get();
            Member member = new Member(memberDTO.getFullName(), memberDTO.isSingle(), memberDTO.getInstrument(), band1);
            return memberRepository.save(member);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username doesn't exist");
        }
    }
    public List<Member> findAllMembers(){ return memberRepository.findAll();}

    public List<Member> findAllMembersOfBand(UserDetails userDetails){
        if (bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band1 = bandRepository.findByUsername(userDetails.getUsername()).get();
            return band1.getMembersList();
        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "The username doesn't exist");
        }
    }
    public void deleteMember(UserDetails userDetails, long id){
        if(bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band = bandRepository.findByUsername(userDetails.getUsername()).get();
            System.err.println(id);
            for (Member x : band.getMembersList()) {
                System.err.println(x.getId());
                if (x.getId() == id){
                    //Member member = new Member(x.getFullName(), x.isSingle(), x.getInstrument(),band);
                    //memberRepository.delete(member);
                    memberRepository.deleteById(x.getId());
                    break;
                } else{
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "Member not found");
                }
            }

        } else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Band not found");
        }
    }
    public List<Booking> listBookingBand(UserDetails userDetails) {
        if (bandRepository.findByUsername(userDetails.getUsername()).isPresent()) {
            Band band = bandRepository.findByUsername(userDetails.getUsername()).get();
            return band.getBookingList();
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Agent not found");
        }
    }



}
