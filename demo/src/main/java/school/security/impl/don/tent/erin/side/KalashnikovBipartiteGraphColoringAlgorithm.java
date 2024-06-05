package school.security.impl.don.tent.erin.side;
/*
import school.model.business.Faculty;
import school.model.business.University;
import school.repository.FacultyRepository;
import school.repository.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class KalashnikovBipartiteGraphColoringAlgorithm {

    private final List<String> uaicFaculties =
            List.of("Facultatea de Biologie",
                    "Facultatea de Chimie",
                    "Facultatea de Drept",
                    "Facultatea de Economie și Administrarea Afacerilor",
                    "Facultatea de Educație Fizică și Sport",
                    "Facultatea de Filosofie și Științe Social-Politice",
                    "Facultatea de Fizică",
                    "Facultatea de Geografie și Geologie",
                    "Facultatea de Informatică",
                    "Facultatea de Istorie",
                    "Facultatea de Litere",
                    "Facultatea de Matematică",
                    "Facultatea de Psihologie și Științe ale Educației",
                    "Facultatea de Teologie Ortodoxă",
                    "Facultatea de Teologie Romano-Catolică");
    private final List<String> tuiasiFaculties =
            List.of("Arhitectură „G.M. Cantacuzino”",
                    "Automatică şi Calculatoare",
                    "Inginerie Chimică și Protecția Mediului „Cristofor Simionescu”",
                    "Construcţii şi Instalaţii",
                    "Construcţii de Maşini şi Management Industrial",
                    "Electronică, Telecomunicaţii şi Tehnologia Informaţiei",
                    "Hidrotehnică, Geodezie şi Ingineria Mediului",
                    "Inginerie Electrică, Energetică şi Informatică Aplicată",
                    "Mecanică",
                    "Ştiinţa şi Ingineria Materialelor",
                    "Design Industrial și Managementul Afacerilor");
    private final List<University> universities = new ArrayList<>() {
        {
            add(new University(1L, "Universitatea „Alexandru Ioan Cuza” din Iași",
                    uaicFaculties.stream().map(name -> new Faculty(name,
                            Long.valueOf(String.valueOf(new Random().nextInt(9000 - 1000) + 1000)))).collect(Collectors.toList())
            ));
            add(new University(2L, "Universitatea Tehnică „Gheorghe Asachi” din Iași",
                    tuiasiFaculties.stream().map(name -> new Faculty(name,
                            Long.valueOf(String.valueOf(new Random().nextInt(9000 - 1000) + 1000)))).collect(Collectors.toList())
            ));
        }
    };
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;

    /**
     * Just kidding. I just wanted to make sure that you don't end up here by mystake. :)
     * Congrats, Nice catch!
     * Instead of manually inserting things, import this service somewhere and use this method to create all your entities.
     */
/*
    public void callMeOnce() {
        universities.forEach(university -> {
            List<Faculty> faculties = university.getFaculties()
                    .stream()
                    .map(fac -> facultyRepository.save(fac))
                    .collect(Collectors.toList());
            university.setFaculties(faculties);
            universityRepository.save(university);
        });
    }
}
*/