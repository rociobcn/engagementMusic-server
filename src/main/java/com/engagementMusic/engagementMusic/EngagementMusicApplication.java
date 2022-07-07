package com.engagementMusic.engagementMusic;

import com.engagementMusic.engagementMusic.Embeddables.Address;
import com.engagementMusic.engagementMusic.Embeddables.FullName;
import com.engagementMusic.engagementMusic.Enums.Genre;
import com.engagementMusic.engagementMusic.Enums.Instrument;
import com.engagementMusic.engagementMusic.Models.*;
import com.engagementMusic.engagementMusic.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class EngagementMusicApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EngagementMusicApplication.class, args);
	}
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	BandRepository bandRepository;
	@Autowired
	AgentRepository	agentRepository;
	@Autowired
	DancingBarRepository dancingBarRepository;
	@Autowired
	BookingRepository bookingRepository;

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Override
	public void run(String... args) throws Exception {
		Band band1 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "rocio@gmail.com", "rosada", passwordEncoder.encode("1234"), "https://static1.abc.es/media/play/2017/11/06/la-banda-del-patio-kfmC--1240x698@abc.JPG", "46989947g", 604000527, "La banda del patio", Genre.BLUES, new BigDecimal(100)));
		band1.setRole("BAND");
		bandRepository.save(band1);
		Band band2 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "holaa@gmail.com", "rocio", passwordEncoder.encode("1234"), "https://estaticos-cdn.elperiodico.com/clip/462cc093-bed3-4694-aa3b-c71c87527dda_alta-libre-aspect-ratio_default_0.jpg","46989947g", 604000527, "Los fenomenos", Genre.FOLK, new BigDecimal(200)));
		band2.setRole("BAND");
		bandRepository.save(band2);
		Band band3 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "holaaaa@gmail.com", "kaka", passwordEncoder.encode("1234"), "https://cdn0.bodas.net/vendor/18701/3_2/960/jpg/foto-grupo-insieme_1_18701-162016044768190.jpeg", "46989947g", 604000527, "Bualaa", Genre.CLASSICAL, new BigDecimal(700)));
		band3.setRole("BAND");
		bandRepository.save(band3);
		Band band4 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "jesus@gmail.com", "jota", passwordEncoder.encode("1234"), "https://www.latercera.com/resizer/-YCU-ggmbL92f8yVFSsAypQhKg4=/1023x767/smart/cloudfront-us-east-1.images.arcpublishing.com/copesa/LJO5QLSP25CJNJMZRDXMMBE3OU.jpg","46989947g", 604000527, "ChildSoon", Genre.INDEPENDENT_MUSIC, new BigDecimal(1200)));
		band4.setRole("BAND");
		bandRepository.save(band4);
		Band band5 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "maria@gmail.com", "maria", passwordEncoder.encode("1234"),"https://zaragozafieles.es/wp-content/uploads/2018/07/heroes-del-silencio-552x311.jpg", "46989947g", 604000527, "Los Porretas", Genre.SKA, new BigDecimal(600)));
		band5.setRole("BAND");
		bandRepository.save(band5);
		Band band6 = bandRepository.save(new Band(new FullName("Rocio", "Ramos"), "pepe@gmail.com", "pepe", passwordEncoder.encode("1234"), "https://www.elnacional.cat/enblau/uploads/s1/19/97/49/03/shakira-muy-sorprendida-en-la-nfl.jpeg", "46989947g", 604000527, "Shakira", Genre.POP, new BigDecimal(200)));
		band6.setRole("BAND");
		bandRepository.save(band6);
		Member member1 = new Member(new FullName("Motoko", "Kawasaky"), true, Instrument.CLARINET, band1);
		Member member2 = new Member(new FullName("Claudia", "Fernandez"), false, Instrument.PIANO, band1);
		memberRepository.saveAll(List.of(member1, member2));
		Agent agent1 = agentRepository.save(new Agent(new FullName("Jose", "Caro"), "jose@gmail.com", "josito", passwordEncoder.encode("1234"), "46989947g", 658741235, "Encargado"));
		agent1.setRole("AGENT");
		agentRepository.save(agent1);
		DancingBar dancingBar1 = dancingBarRepository.save(new DancingBar(agent1, "Liceo","https://media-cdn.tripadvisor.com/media/photo-s/1a/30/08/d2/ar-barcelona-liceo-ramblas.jpg", new Address("Las Ramblas", "Barcelona", "Spain", "08025")));
		DancingBar dancingBar2 = dancingBarRepository.save(new DancingBar(agent1, "Razzmataz", "https://fastly.4sqi.net/img/general/600x600/26412922_E3sgId350UVEDzJn7R97gGw5XaW9Xaq0sOnAE3GBkDI.jpg", new Address("C/ Sepulveda 25", "Murcia", "Spain", "08003")));
		DancingBar dancingBar3 = dancingBarRepository.save(new DancingBar(agent1, "Apolo", "https://www.sala-apolo.com/uploads/media/default/0001/01/thumb_967_default_wide.jpeg", new Address("C/ Pamplona 2", "Bilbao", "Spain", "08125")));
		DancingBar dancingBar4 = dancingBarRepository.save(new DancingBar(agent1, "Space", "https://www.fiestasnocheviejamadrid.com/imagenes/81_foto4.jpg", new Address("Av. Diagonal 201", "Madrid", "Spain", "08078")));
		DancingBar dancingBar5 = dancingBarRepository.save(new DancingBar(agent1, "Row", "https://www.allfest.es/wp-content/uploads/2021/11/6E42025A-6249-4D73-96C9-F0E0E5A1FCDB.jpeg", new Address("Carretera n/25 km5", "Sevilla", "Spain", "08015")));
		DancingBar dancingBar6 = dancingBarRepository.save(new DancingBar(agent1, "Pachanga Beach", "https://www.elprogreso.es/asset/thumbnail,1280,720,center,center//media/elprogreso/images/2022/06/12/2022061212592017972.jpg", new Address("C/ de la Cendra", "Tarragona", "Spain", "08007")));
		//Booking booking1 = bookingRepository.save(new Booking(band1, agent1, new LocalDate (2022, 5, 25), new BigDecimal(400)));




	}
}
