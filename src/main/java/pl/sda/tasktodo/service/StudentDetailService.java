package pl.sda.tasktodo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.tasktodo.repository.StudentRepository;

@Service
public class StudentDetailService implements UserDetailsService {
    private final StudentRepository studentRepository;

    public StudentDetailService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return studentRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Brak takiego studenta!"));
    }
}
