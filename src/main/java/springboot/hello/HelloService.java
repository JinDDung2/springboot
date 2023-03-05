package springboot.hello;

import org.springframework.stereotype.Service;

@Service
public interface HelloService {
    String sayService(String name);
}
